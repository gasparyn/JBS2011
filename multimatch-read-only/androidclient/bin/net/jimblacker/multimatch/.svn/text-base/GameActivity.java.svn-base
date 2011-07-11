package net.jimblacker.multimatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class GameActivity extends Activity {

  private static final int REFRESH_INTERVAL = 1000 * 2;

  private static final String HANDSET_ID_KEY = "handsetId";

  private static final int INPUT_STREAM_SIZE = 32 * 1024;
  private static final String SERVER_BASE_URL = "http://10.0.2.2:8888/server";
  private static final int VERSION = 8;
  protected static final String TAG = GameActivity.class.getName();

  protected String card;

  long id;

  /**
   * Get a connection to the specified urlString
   */
  private HttpURLConnection getConnection(String urlString) throws IOException {
    URLConnection connection = new URL(urlString).openConnection();
    connection.setRequestProperty("Content-Type", "application/json");
    return (HttpURLConnection) connection;
  }
  /**
   * Gets JSON data from specified URLs
   * 
   * @param urlText
   *          The URL of the data to fetch
   * @return A parsed JSONObject from the data
   * @throws AccessException
   *           Thrown if a problem was experienced in the fetch
   */
  JSONObject getJsonData(String urlText) throws AccessException {
    try {
      URLConnection connection = getConnection(urlText);

      InputStream inputStream = connection.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream),
          INPUT_STREAM_SIZE);
      StringBuffer builder = new StringBuffer();
      String line;
      while ((line = reader.readLine()) != null) {
        builder.append(line).append("\n");
      }
      return new JSONObject(builder.toString());
    } catch (IOException e) {
      throw new AccessException(e);
    } catch (JSONException e) {
      throw new AccessException(e);
    }
  }

  private void hideProgress() {
    runOnUiThread(new Runnable() {
      public void run() {
        findViewById(R.id.Content).setVisibility(View.VISIBLE);
        findViewById(R.id.ProgressIndicatorContainer).setVisibility(View.GONE);
      }
    });
  }

  private void next(final JSONObject inObject) {
    new Thread(new Runnable() {
      public void run() {
        try {
          inObject.put("id", id);
          inObject.put("version", VERSION);
        } catch (JSONException e1) {
          throw new RuntimeException(e1);
        }
        Log.i(TAG, inObject.toString());
        try {
          JSONObject returnObject = postJsonData(SERVER_BASE_URL, inObject);
          Log.i(TAG, returnObject.toString());
          String error = returnObject.has("error") ? returnObject.getString("error") : "";
          if ("version".equals(error)) {
            runOnUiThread(new Runnable(){
              public void run() {
                Toast.makeText(GameActivity.this, "Wrong version", 1000 * 10).show();
              }});
          } else {

            card = returnObject.getString("showCard");
            final Button cardButton = (Button) findViewById(R.id.Card);
            final boolean solved = returnObject.getBoolean("solved");
            if ("null".equals(card)) {
              runOnUiThread(new Runnable() {
                public void run() {
                  cardButton.setVisibility(View.INVISIBLE);
                }
              });

            } else {
              boolean flipped = returnObject.getBoolean("flipped");

              if (flipped || solved) {
                runOnUiThread(new Runnable() {
                  public void run() {
                    cardButton.setVisibility(View.VISIBLE);
                    cardButton.setText(card);
                  }
                });
              } else {
                runOnUiThread(new Runnable() {
                  public void run() {
                    cardButton.setVisibility(View.VISIBLE);
                    cardButton.setText("?");
                  }
                });
              }
            }
            runOnUiThread(new Runnable() {
              public void run() {
                findViewById(R.id.Solved).setVisibility(solved ? View.VISIBLE : View.INVISIBLE);
              }
            });
          }

         

        } catch (AccessException e) {
          runOnUiThread(new Runnable() {
            public void run() {
              Toast.makeText(GameActivity.this, "Server error", 1000 * 10).show();
            }
          });
          e.printStackTrace();
        } catch (JSONException e) {
          throw new RuntimeException(e);
        }
        hideProgress();
      }
    }).start();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // The same handset ID is always used to reduce the impact of lost connections
    final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    if (!preferences.contains(HANDSET_ID_KEY)) {
      Editor edit = preferences.edit();
      edit.putLong(HANDSET_ID_KEY, new Random().nextLong());
      edit.commit();
    }
    id = preferences.getLong(HANDSET_ID_KEY, 0);

    setContentView(R.layout.game);

    final Button cardButton = (Button) findViewById(R.id.Card);
    cardButton.setOnClickListener(new OnClickListener() {

      public void onClick(View v) {
        cardButton.setText(card);
        try {
          final JSONObject object = new JSONObject();
          object.put("action", "flipped");
          // todo .. put this back
          next(object);
        } catch (JSONException e) {
          throw new RuntimeException(e);
        }
      }
    });

    showProgress();

    refresh();
    
    new Timer().scheduleAtFixedRate(new TimerTask(){

      @Override
      public void run() {
        refresh();
        
      }}, REFRESH_INTERVAL, REFRESH_INTERVAL);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
//    {
//      // 'Refresh' menu item
//      MenuItem refresh = menu.add(R.string.refresh);
//      refresh.setIcon(R.drawable.ic_menu_refresh);
//      refresh.setOnMenuItemClickListener(new OnMenuItemClickListener() {
//
//        public boolean onMenuItemClick(MenuItem item) {
//          refresh();
//          return false;
//        }
//      });
//    }
    {
      // 'Reset' menu item
      MenuItem refresh = menu.add(R.string.reset);
      refresh.setIcon(android.R.drawable.ic_menu_revert);
      refresh.setOnMenuItemClickListener(new OnMenuItemClickListener() {

        public boolean onMenuItemClick(MenuItem item) {
          try {
            final JSONObject object = new JSONObject();
            object.put("action", "reset");
            next(object);
          } catch (JSONException e) {
            throw new RuntimeException(e);
          }
          return false;
        }
      });
    }

    return super.onCreateOptionsMenu(menu);
  }

  private JSONObject postJsonData(String urlText, JSONObject object) throws AccessException {
    try {
      HttpURLConnection connection = getConnection(urlText);
      connection.setDoOutput(true);
      connection.setRequestMethod("POST");

      OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
      writer.write(object.toString());
      writer.close();

      int responseCode = connection.getResponseCode();
      if (responseCode != HttpURLConnection.HTTP_OK) {
        throw new AccessException("Error: " + responseCode);
      }
      InputStream inputStream = connection.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream),
          INPUT_STREAM_SIZE);
      StringBuffer builder = new StringBuffer();
      String line;
      while ((line = reader.readLine()) != null) {
        builder.append(line).append("\n");
      }
      return new JSONObject(builder.toString());
    } catch (IOException e) {
      e.printStackTrace();
      throw new AccessException(e);
    } catch (JSONException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private void refresh() {
    try {
      final JSONObject object = new JSONObject();
      object.put("action", "login");
      next(object);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  private void showProgress() {
    runOnUiThread(new Runnable() {
      public void run() {
        findViewById(R.id.Content).setVisibility(View.GONE);
        findViewById(R.id.ProgressIndicatorContainer).setVisibility(View.VISIBLE);
      }
    });
  }
}
