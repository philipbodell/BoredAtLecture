package software_engineering.bal_ui;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class DisplayLoginScreen extends Activity {
	public final static String EXTRA_MESSAGE = "software_engineering.bal_ui.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_loginscreen);
		// Show the Up button in the action bar.
		setupActionBar();
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
		getMenuInflater().inflate(R.menu.display_login_screen, menu);
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
	public void login (View view){
		EditText editUser = (EditText) findViewById(R.id.edit_username);
    	String Username = editUser.getText().toString();
    	EditText editPassword = (EditText) findViewById(R.id.edit_password);
    	String Password = editPassword.getText().toString();
		
    	if(Username != null && Password != null && Username != "" && Password!=""){			//Skicka information till server
        	Intent intent = new Intent(this, DisplayChat.class); //next screen to go to
        	intent.putExtra(EXTRA_MESSAGE, Username);
        	startActivity(intent);
		}else{
			
			
			
		}
		
	}

}
