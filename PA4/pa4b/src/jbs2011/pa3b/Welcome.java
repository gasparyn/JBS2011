package jbs2011.pa3b;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * This is the main activity for the Men app.
 * Asks the user to either log in or head into browsing
 * @author gaspar obimba
 *
 */
public class Welcome extends Activity implements  android.view.View.OnClickListener{
	
	
	/** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.intro);
	   
	      // Set up click listeners for the category buttons buttons
	      View playButton = findViewById(R.id.play_label);
	      playButton.setOnClickListener(this);
	      View exitButton = findViewById(R.id.exit_label);
	      exitButton.setOnClickListener(this);

//	     
	   }
	  
	  
/**
 * @author gasparobimba
 * 
 *initial screen that asks you to choose whether you want to login or go straight ahead and browse
 *
 * @param v view 
 */
	    public void onClick(View v) {
	      switch (v.getId()) {
	     
	      case R.id.play_label :
	    	  startActivity( new Intent(this, GameActivity.class));
		        
		         break;
	      // do this for all buttons
	      case R.id.exit_label:
	    	  this.finish();
	    	break; 
	      }
	   }
}
