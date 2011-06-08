
package edu.brandeis.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class Sudoku extends Activity implements OnClickListener {
   private static final String TAG = "Sudoku";
   
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);

      // Set up click listeners for all the buttons
      View continueButton = findViewById(R.id.continue_button);
      continueButton.setOnClickListener(this);

      View newButton = findViewById(R.id.new_button);
      newButton.setOnClickListener(this);
      
      View aboutButton = findViewById(R.id.about_button);
      aboutButton.setOnClickListener(this);
      
      View exitButton = findViewById(R.id.exit_button);
      exitButton.setOnClickListener(this);
   }

   //OnClickListener has one method in it called onClick( ), 
   //so we have to add that method to our class as well
   public void onClick(View v) {
      switch (v.getId()) {
      case R.id.about_button:
         Intent i = new Intent(this, About.class);
         startActivity(i);
         break;
      // do this for all buttons
      case R.id.new_button:
         openNewGameDialog();
         break;
         
      case R.id.continue_button:
    	  Intent j=new Intent(this,Login.class);
    	  startActivity(j);
    	  break;
    	  
      case R.id.exit_button:
    	  AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	  builder.setMessage("Are you sure you want to exit?")
    	         .setCancelable(false)
    	         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	             public void onClick(DialogInterface dialog, int id) {
    	                 Sudoku.this.finish();
    	             }
    	         })
    	         .setNegativeButton("No", new DialogInterface.OnClickListener() {
    	             public void onClick(DialogInterface dialog, int id) {
    	                  dialog.cancel();
    	             }
    	         }).show();
    	  //finish();
         break;
      }
   }
   
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      super.onCreateOptionsMenu(menu);
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.menu, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
      case R.id.settings:
         startActivity(new Intent(this, Prefs.class));
         return true;
     
      //do this for all other items
      }
      return false;
   }

   /** Ask the user what difficulty level they want */
   private void openNewGameDialog() {
      new AlertDialog.Builder(this)
           .setTitle(R.string.new_game_title)
           .setItems(R.array.difficulty,
            new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialoginterface,
                     int i) {
                  startGame(i);
               }
            })
           .show();
   }
   
   /** Start a new game with the given difficulty level */
   private void startGame(int i) {
      Log.d(TAG, "clicked on " + i);
      Intent intent = new Intent(this, Game.class);
      intent.putExtra(Game.KEY_DIFFICULTY, i);
      startActivity(intent);

   }
}
