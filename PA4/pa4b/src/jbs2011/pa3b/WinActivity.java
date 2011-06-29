package jbs2011.pa3b;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class WinActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.level);
}
}