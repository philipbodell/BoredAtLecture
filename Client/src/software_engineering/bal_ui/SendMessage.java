package software_engineering.bal_ui;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import boredatlecture.ChatPackage;


import android.widget.EditText;

public class SendMessage implements Runnable{

	//Class variables
	private String message;
	private Socket client;
	private String ip;
	private int port;
	//private DisplayChat chat;
	private boolean connected,online;
	
	public SendMessage(String message,String ip, int port,boolean online){
		this.message = message;
		this.ip = ip;
		this.port = port;
		//this.chat = chat;
		this.online = online;
		connected = false;
		//board.setText(board.getText()+"Thread Started");
	}
	
	@Override
	public void run() {
		
		client = null;
		while(true){
			try {
				client = new Socket(ip,port);
				break;
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			

		ObjectOutputStream ob;
		try {
			ob = new ObjectOutputStream(client.getOutputStream());
			ob.writeObject(new ChatPackage(12,message));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	private String configureString(String input){
		return input;
	}
	
}
