package software_engineering.bal_ui;


//import com.example.boredchat.R;

import java.io.IOException;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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

public class DisplayChat extends Activity {

	//Statics
	private static final String ip = "192.168.1.111";
	private static final int port = 26575;
	
	//Class variables
	public EditText board;
	//private EditText field;
	private String userName;
	
	//Threads
	Thread send;
	Thread reciever;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_chat);
		// Show the Up button in the action bar.
		setupActionBar();
		
	    // Get the message from the intent
	    Intent intent = getIntent();
	    //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	    board = (EditText) findViewById(R.id.messageBoard);
	    //field = (EditText) findViewById(R.id.messageField);
	    
	    //send = new Thread(new sendMessage())
	    
	    reciever = new Thread(new TcpMessageReciever(board,port,isOnline()));
	    reciever.start();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_chat, menu);
		return true;
	}

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
	
	/** Called when the user clicks the Send button 
	 * @throws IOException */
	public void sendMessage(View view) throws IOException{
		EditText field = (EditText) findViewById(R.id.messageField);
		String message = field.getText().toString();
		field.setText("");
		InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        try{
        	SendMessage test = new SendMessage(message,ip,port,board,isOnline());
        	
        	send = new Thread(test);
            send.start();
        }catch(Exception e){
        	board.setText("System: Error: 201");
        }
        
		
	}
	public boolean isOnline() {
        ConnectivityManager cm =
            (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
	
	public void boardSet(String input,boolean clear){
		if(clear){
			board.setText(input);
		}else{
			board.setText(board.getText()+input);
		}
	}

}
