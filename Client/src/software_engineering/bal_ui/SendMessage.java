package software_engineering.bal_ui;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import android.widget.EditText;

public class SendMessage implements Runnable{

	//Class variables
	private String message;
	private Socket client;
	private String ip;
	private int port;
	private EditText board;
	//private DisplayChat chat;
	private boolean connected,online;
	
	public SendMessage(String message,String ip, int port,EditText board,boolean online){
		this.message = message;
		this.ip = ip;
		this.port = port;
		this.board = board;
		//this.chat = chat;
		this.online = online;
		connected = false;
		
	}
	
	@Override
	public void run() {
		
			//board.setText(board.getText()+"Thread Started");
			//chat.boardSet("testelitest", false);
		
		
		// TODO Auto-generated method stub
		//Here the sending will commence..
		//board.setText(board.getText()+"\n"+message);
		
		if(online){
			try {
				client = new Socket(this.ip, this.port);
				board.setText(board.getText()+"Connected");
				connected = true;
			} catch (Exception e){
				board.setText(board.getText()+"System: Error:001");
			}
			if(message != null){
				message = configureString(message);
			}
			if(connected){
				try {
					OutputStream out = client.getOutputStream();       
		            PrintWriter output = new PrintWriter(out); 
		            
		            output.println(message);
		            output.flush();
		            output.close();
				} catch (IOException e) {
					board.setText("System: Error:002");
				}
			}
		}
		//board.setText(board.getText()+"Thread Stopped");
	}
	
	private String configureString(String input){
		return input;
	}
	
}
