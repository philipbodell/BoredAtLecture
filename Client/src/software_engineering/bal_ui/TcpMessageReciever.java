package software_engineering.bal_ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


import android.widget.EditText;


public class TcpMessageReciever implements Runnable{
	
	//Class variables
	private int port;
	private EditText board;
	private ServerSocket socket;
	private boolean connected;
	private boolean online;
	
	public TcpMessageReciever(EditText board, int port,boolean online){
		this.board = board;
		this.port = port;
		this.online = online;
		connected = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		board.setText(board.getText()+"System: recieveThread Started");
		if(online){
			try {
				socket = new ServerSocket(port);
				connected = true;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				connected = false;
				board.setText("System: Error 101");
			}
			
			Socket inSock = null;
			try {
				inSock = socket.accept();
				connected = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				connected = false;
				//e.printStackTrace();
				board.setText("System: Error 102");
			}
			
			while(connected){
				if(inSock.isConnected()){
					connected = false;
					board.setText("System: Error 103");
					break;
				}
				BufferedReader in = null;
				try {
					in = new BufferedReader(new InputStreamReader(inSock.getInputStream()));
				} catch (IOException e) {
					board.setText("System: Error 104");
				}
				String tempMessage = "";
				try {
					tempMessage = in.readLine();
				} catch (IOException e) {
					board.setText("System: Error: 105");
				}
				board.setText(board.getText()+tempMessage);
			}
		}
		board.setText(board.getText()+"System: recieveThread Ending");
	}
	
	
	
}
