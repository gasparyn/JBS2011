package jbs2011.gaspar.pa3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * main game activity
 * @author gaspar
 */
public class MainActivity extends Activity {
    
    /**
     * Method called on application start.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CONTEXT_MENU);
        setContentView(new GameDynamics(this));
    }
}