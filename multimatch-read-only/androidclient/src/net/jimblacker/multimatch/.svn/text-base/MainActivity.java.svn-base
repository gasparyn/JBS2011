package net.jimblacker.multimatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    Button button = (Button) findViewById(R.id.LogInButton);
    button.setOnClickListener(new OnClickListener() {

      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
      }
    });
  }
}