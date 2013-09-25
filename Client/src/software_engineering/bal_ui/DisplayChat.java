package software_engineering.bal_ui;


//import com.example.boredchat.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import boredatlecture.ChatPackage;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
/**
 * 
 * @author Johan
 *
 */
public class DisplayChat extends Activity {

	//Statics
	private static final String ip = "192.168.1.2";
	private static final int port = 44444;
	
	//For debugging purposes only, to be removed
	private static final String userName = "Doge";
	
	ServerSocket ss = null;
	   String mClientMsg = "";
	   Thread myCommsThread = null;
	   protected static final int MSG_ID = 0x1337;
	
	//Class variables
	public EditText board;
	//private EditText field;
	//private String userName;
	
	//Threads
	Thread send;
	Thread reciever;
	
	
	/**
	 * Starts the thread for updating
	 * 
	 * @param Bundle 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_chat);
		// Show the Up button in the action bar.
		setupActionBar();
		send = null;
	    // Get the message from the intent
	    Intent intent = getIntent();
	    //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	    board = (EditText) findViewById(R.id.messageBoard);
	    //field = (EditText) findViewById(R.id.messageField);
	    
	    //send = new Thread(new sendMessage())
	    if(isOnline()){
		    reciever = new Thread(new TcpMessageReciever(this,ip,port,userName, 4));
		    reciever.start();
	    }
	    
	    
	    
	    //runOnUiThread(new TcpMessageReciever(board,port,isOnline()));
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	/**
	 * Inflate the menu; this adds items to the action bar if it is present.
	 * 
	 * @param Menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.display_chat, menu);
		return true;
	}
	/**
	 * This ID represents the Home or Up button. In the case of this
	 *activity, the Up button is shown. Use NavUtils to allow users
	 *to navigate up one level in the application structure. For
	 *more details, see the Navigation pattern on Android Design:	
	 *http://developer.android.com/design/patterns/navigation.html#up-vs-back		
	 * 
	 * @param MenuItem
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	   protected void onStop() {
	    super.onStop();
	   
	    if(isOnline()){
	    	reciever.interrupt();
	    }
	    
	    
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		if(isOnline()){
	    	reciever.interrupt();
	    }
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		if(isOnline()){
		    reciever = new Thread(new TcpMessageReciever(this,ip,port,userName, 4));
		    reciever.start();
	    }
	}
	
	/** Called when the user clicks the Send button. Starts a new thread and 
	 * creates a SendMessage thread
	 * @param View
	 * @throws IOException 
	 */
	public void sendMessage(View view) throws IOException{
		EditText field = (EditText) findViewById(R.id.messageField);
		String message = field.getText().toString();
		field.setText("");
		InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        //TODO
        /*Socket derp;
        
        try{
        	derp = new Socket(ip, port);
        	//ObjectOutputStream ob;
    		//ob = new ObjectOutputStream(derp.getOutputStream());
    		//ob.writeObject(new ChatPackage(12,message));
    		derp.close();
        }catch(UnknownHostException e){
        	board.setText(board.getText()+e.getMessage());
        }
        
        */
        
        	
		SendMessage test = new SendMessage(message,ip,port,isOnline());
    	//runOnUiThread(test);
    	send = new Thread(test);
        send.start();
	
        	
        
	}
	/**
	 * Checks if the device has Internet access
	 * @return
	 */
	public boolean isOnline() {
        ConnectivityManager cm =
            (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
	Handler handler = new Handler(){
		public void handleMessage(Message m){
			Object o = m.obj;
			if(o == null){
				o="";
			}
			EditText eT = (EditText) findViewById(R.id.messageBoard);
			eT.setText(eT.getText()+o.toString());
		}
	};
}
	
