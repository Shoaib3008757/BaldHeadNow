package ranglerz.face.fun;

import static ranglerz.face.fun.util.CameraHelper.cameraAvailable;
import static ranglerz.face.fun.util.CameraHelper.getCameraInstance;
import static ranglerz.face.fun.util.MediaHelper.getOutputMediaFile;
import static ranglerz.face.fun.util.MediaHelper.saveToFile;

import java.io.File;

import ranglerz.face.fun.util.Log;
import ranglerz.face.fun.widget.CameraPreview;
import ranglerz.face.fun.xml.FromXML;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Takes a photo saves it to the SD card and returns the path of this photo to the calling Activity
 * @author paul.blundell
 *
 */
@SuppressWarnings("deprecation")
public class CameraActivity extends Activity implements PictureCallback {

	protected static final String EXTRA_IMAGE_PATH = "com.blundell.tut.cameraoverlay.ui.CameraActivity.EXTRA_IMAGE_PATH";




	int cameraSwitch = 1;
	private Camera camera;
	private CameraPreview cameraPreview;
	ImageButton switchCamera;
    Camera.CameraInfo  currentCamInfo;
	 int camNum = 0;
	 MediaPlayer mp;
	 @SuppressLint("InlinedApi")
	private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;
    
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_preview);
		switchCamera = (ImageButton) findViewById(R.id.buttonSwitchCamera);
		setResult(RESULT_CANCELED);
		
	
		mp = MediaPlayer.create(this, R.raw.camera_music);




		 camNum = Camera.getNumberOfCameras();
		 currentCamInfo = new Camera.CameraInfo();	
		
		Intent i = getIntent();
		cameraSwitch = i.getIntExtra("option", 1);
		 
		 
		if(camNum == 1)
		{
			
		switchCamera.setVisibility(View.GONE);	
	//	cameraSwitch = 1;
		
			
		}else if(camNum >1)
		{
			switchCamera.setVisibility(View.GONE);	
		//	switchCamera.setVisibility(View.GONE);	
	//		cameraSwitch =0;
			
		}
		else if(camNum ==0 )
		{
			switchCamera.setVisibility(View.GONE);	
			Toast.makeText(getApplicationContext(), "You have no Camera", Toast.LENGTH_LONG).show();
//			Intent in = new Intent(CameraActivity.this,Guide_Activity.class);
//			startActivity(in);
			finish();
			
			
		}
			
			
		
		
		// Camera may be in use by another activity or the system or not available at all
		camera = getCameraInstance(cameraSwitch);
		
	
		
		if(cameraAvailable(camera)){
			initCameraPreview();
//			Toast.makeText(this, "Camera Available", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, "Camera not Found", Toast.LENGTH_LONG).show();
			finish();
		}
		
		
		
		
		
		
		
		switchCamera.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				releaseCamera();
				if(camId == Camera.CameraInfo.CAMERA_FACING_BACK)
				{
					camId = Camera.CameraInfo.CAMERA_FACING_FRONT;
				cameraSwitch = 0;
				
//				Intent it = new Intent(CameraActivity.this,CameraActivity.class);
//				it.putExtra("option", cameraSwitch);
//				startActivity(it);
//				finish();
				camera  = getCameraInstance(Camera.CameraInfo.CAMERA_FACING_FRONT);
				cameraPreview.switchCamera(camera);
				}
				else
				{
//					camId = Camera.CameraInfo.CAMERA_FACING_BACK;
//					cameraSwitch = 1;
//					Intent it = new Intent(CameraActivity.this,CameraActivity.class);
//					it.putExtra("option", cameraSwitch);
//					startActivity(it);
//					finish();
					camera  = getCameraInstance(Camera.CameraInfo.CAMERA_FACING_BACK);
					cameraPreview.switchCamera(camera);
				}
					

				
			}
		});
		
		
	}

	// Show the camera view on the activity
	private void initCameraPreview() {
		cameraPreview = (CameraPreview) findViewById(R.id.camera_preview);
		cameraPreview.init(camera);
	}
	
	private void releaseCamera() {
		// stop and release camera
		if (camera != null) {
			camera.release();
			camera = null;
		}
	}
	

	@FromXML
	public void onCaptureClick(View button){
		// Take a picture with a callback when the photo has been created
		// Here you can add callbacks if you want to give feedback when the picture is being taken
		mp.start();

		if(camera!= null)
		camera.takePicture(null, null, this);
		
	}

	
	
	
	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		Log.d("Picture taken");
		
		String path = savePictureToFileSystem(data);
		setResult(path);
		finish();
	}

	private static String savePictureToFileSystem(byte[] data) {
		File file = getOutputMediaFile();
		
	   saveToFile(data, file); // save image to Directory
		return file.getAbsolutePath();
	}

	private void setResult(String path) {
		Intent intent = new Intent();
		intent.putExtra(EXTRA_IMAGE_PATH, path);
		setResult(RESULT_OK, intent);
	}

	// ALWAYS remember to release the camera when you are finished
	@Override
	protected void onPause() {
		super.onPause();
		releaseCamera();
	}


	@Override
	protected void onDestroy()
	{
		mp.stop();
		mp.release();
		
		
		super.onDestroy();
	}
}
