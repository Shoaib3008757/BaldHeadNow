package ranglerz.face.fun;

import com.google.android.gms.ads.AdView;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;

public class Guide_Activity extends Activity{
	
	ImageView cam,gallery;
	public static int CAMERA_CODE = 1;	
	public static int GALLERY_CODE = 2;
	MediaPlayer mp ;

	private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 000000;
	private static final int MY_PERMISSIONS_REQUEST_CAMERA = 000001;

	// for banner ads
	AdView mAdView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brows_image);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

				//************************ AD Mob Code ********************
		/*try {
			mAdView = (AdView) findViewById(R.id.adViewBannerC);
			AdRequest adRequest = new AdRequest.Builder()
					.addTestDevice("243F1AAAB8720ECBE59319686B32A7B2").build();
			mAdView.loadAd(adRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
*/


		// camera Button
		cam = (ImageView) findViewById(R.id.imageViewCamera);
		// gallery Button
		gallery = (ImageView) findViewById(R.id.imageViewGallery);
		mp = MediaPlayer.create(this, R.raw.button_music);
		
		cam.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mp.start();
		    	Intent intent = new Intent(Guide_Activity.this, AdjustLandMarks.class);
		    	intent.putExtra("option", CAMERA_CODE);
		    	startActivity(intent);
			}
		});
		
		
		gallery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mp.start();
				Intent intent = new Intent(Guide_Activity.this, AdjustLandMarks.class);
		    	intent.putExtra("option", GALLERY_CODE);
		    	startActivity(intent);
				
				
				
			}
		});
		
		
	}
	
	@Override
	protected void onDestroy()
	{
		mp.stop();
		mp.release();
		super.onDestroy();
	}



	@Override
	protected void onStart() {
		super.onStart();
		checkCameraPermission();
		checkWriteExternalPermission();
	}


	public void checkCameraPermission()
	{
		// Here, thisActivity is the current activity
		if (ContextCompat.checkSelfPermission(Guide_Activity.this,
				android.Manifest.permission.CAMERA)
				!= PackageManager.PERMISSION_GRANTED) {
			// No explanation needed, we can request the permission.

			ActivityCompat.requestPermissions(Guide_Activity.this,
					new String[]{android.Manifest.permission.CAMERA},
					MY_PERMISSIONS_REQUEST_CAMERA);

			// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
			// app-defined int constant. The callback method gets the
			// result of the request.

		}

	}


	public void checkWriteExternalPermission()
	{
		// Here, thisActivity is the current activity
		if (ContextCompat.checkSelfPermission(Guide_Activity.this,
				android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
				!= PackageManager.PERMISSION_GRANTED) {
			// No explanation needed, we can request the permission.

			ActivityCompat.requestPermissions(Guide_Activity.this,
					new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
					MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

			// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
			// app-defined int constant. The callback method gets the
			// result of the request.

		}
	}



	@Override
	public void onRequestPermissionsResult(int requestCode,
										   String permissions[], int[] grantResults) {
		switch (requestCode) {
			case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
				Log.d("tag", "Permission ");
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {

					// permission was granted, yay! Do the
					// contacts-related task you need to do.

					Log.d("tag", "Permission Granted Write External ");

				} else {

					// permission denied, boo! Disable the
					// functionality that depends on this permission.
					Log.d("tag", "Permission Not Granted Write External ");
					checkWriteExternalPermission();
				}
				return;
			}

			case MY_PERMISSIONS_REQUEST_CAMERA: {
				Log.d("tag", "Permission ");
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {

					// permission was granted, yay! Do the
					// contacts-related task you need to do.


					Log.d("tag", "Permission Granted Camera ");

				} else {

					// permission denied, boo! Disable the
					// functionality that depends on this permission.
					Log.d("tag", "Permission Not Granted");
					checkCameraPermission();
				}
				return;
			}


			// other 'case' lines to check for other
			// permissions this app might request
		}
	}


}
