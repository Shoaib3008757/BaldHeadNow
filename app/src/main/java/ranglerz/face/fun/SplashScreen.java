package ranglerz.face.fun;







import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

	// splash screen timer
		private static int TIME_OUT = 3000;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
		
	setContentView(R.layout.splash_screen);

	
	
	new Handler().postDelayed(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Intent i = new Intent(SplashScreen.this,Guide_Activity.class);
	        startActivity(i);
	        // close this activity
            finish();
		}
	}, TIME_OUT);

		
		}
	
	
}
