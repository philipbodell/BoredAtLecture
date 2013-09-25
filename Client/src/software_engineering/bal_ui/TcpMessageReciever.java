package software_engineering.bal_ui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Message;
import boredatlecture.ChatPackage;
import boredatlecture.Request;
import boredatlecture.Response;



public class TcpMessageReciever implements Runnable{
//extends AsyncTask<Void,Void,Void>{

	public volatile String message;
	private DisplayChat dispChat;
	private String ip;
	private int port;
	private String userName;
	private int lobbyId;
	
	public TcpMessageReciever(DisplayChat dispChat, String ip, int port, String userName, int lobbyId){
		this.dispChat = dispChat;
		this.ip = ip;
		this.port = port;
		this.userName = userName;
		this.lobbyId = lobbyId;
	}
	
	@Override
	public void run(){
		updateUI("Thread started \n");	
		//updateUI("doge, wow, such grace\n");
		while(true&&!(Thread.interrupted())){
			Socket sock = null;
			
			//Attempt connection to the server
			while(sock == null &&!(Thread.interrupted())){
				sock = connectionAttempt(ip,port);
			}
			
			//Attempt to send a request for updates
			while(!sendRequest(sock)&&!(Thread.interrupted()));
			
			Response resp = null;
			while(resp == null&&!Thread.interrupted()){
				resp = retrieveData(sock);
				if(resp.update()){
					updateUI(resp.getPackage().getMessage());
				}
			}
			
			try {
				sock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
		
	}
	private void updateUI(String s){
		Message m = dispChat.handler.obtainMessage();
		m.obj = s;
		dispChat.handler.sendMessage(m);
	}
	
	private Socket connectionAttempt(String in, int portIn){
		Socket out = null;
		try {
			out = new Socket(in, portIn);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}
	
	private boolean sendRequest(Socket sock){
		ObjectOutputStream output = null;
		try {
			output = new ObjectOutputStream(sock.getOutputStream());
			output.writeObject(new Request(userName,lobbyId));
			output.flush();
			output.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	private Response retrieveData(Socket sock){
		ObjectInputStream input = null;
		try {
			input = new ObjectInputStream(sock.getInputStream());
			Object o = input.readObject();
			input.close();
			if(o instanceof Response){
				return((Response) o);
			}
			return null;
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
