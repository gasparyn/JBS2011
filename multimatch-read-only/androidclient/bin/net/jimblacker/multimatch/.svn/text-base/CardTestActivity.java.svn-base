package net.jimblacker.multimatch;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

public class CardTestActivity extends Activity {

  private GLSurfaceView glSurfaceView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    glSurfaceView = new GLSurfaceView(this);
    glSurfaceView.setEGLConfigChooser(false);
    glSurfaceView.setRenderer(new CardRenderer(this));
    setContentView(glSurfaceView);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    {
      // 'Reset' menu item
      MenuItem refresh = menu.add(R.string.reset);
      refresh.setIcon(android.R.drawable.ic_menu_revert);
      refresh.setOnMenuItemClickListener(new OnMenuItemClickListener() {

        public boolean onMenuItemClick(MenuItem item) {

          return false;
        }
      });
    }

    return super.onCreateOptionsMenu(menu);
  }

  @Override
  protected void onPause() {
    super.onPause();
    glSurfaceView.onPause();
  }

  @Override
  protected void onResume() {
    super.onResume();
    glSurfaceView.onResume();
  }

}
