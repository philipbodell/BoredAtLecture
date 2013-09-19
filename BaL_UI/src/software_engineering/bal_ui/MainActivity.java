package software_engineering.bal_ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "software_engineering.bal_ui.MESSAGE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void mainMenuEnter (View view){
		Intent intent = new Intent(this, DisplayLoginScreen.class); //next screen to go to
    	startActivity(intent);
	}
	public void mainMenuSettings (View view){
		Intent intent = new Intent(this, DisplaySettings.class); //next screen to go to
    	startActivity(intent);
	}
	public void mainMenuInfo (View view){
		Intent intent = new Intent(this, DisplayInfo.class); //next screen to go to
    	startActivity(intent);
	}
	

}
