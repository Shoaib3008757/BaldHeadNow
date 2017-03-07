package ranglerz.face.fun.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.hardware.Camera;
import android.os.Build;

/**
 * Used to make camera use in the tutorial a bit more obvious
 * in a production environment you wouldn't make these calls static
 * as you have no way to mock them for testing
 * @author paul.blundell
 *
 */
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class CameraHelper {

	public static boolean cameraAvailable(@SuppressWarnings("deprecation") Camera camera) {
		return camera != null;
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public static Camera getCameraInstance(int cameraSwitch) {
		
	
		int cameraCount = 0;
		Camera cam = null;
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
		cameraCount = Camera.getNumberOfCameras();
		
		if(cameraSwitch ==0)
		{
			for ( int camIdx = 0; camIdx < cameraCount; camIdx++ ) {
			    Camera.getCameraInfo( camIdx, cameraInfo );
			    if ( cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK  ) {
			        try {
			        	
			            cam = Camera.open( camIdx );
			            cam.setDisplayOrientation(90);
			        } catch (RuntimeException e) {
			           Log.d("Camera failed to open: Back Camera " + e.getLocalizedMessage());
			        }
			    }
			}
		}
		else if(cameraSwitch ==1)
		{
			for ( int camIdx = 0; camIdx < cameraCount; camIdx++ ) {
			    Camera.getCameraInfo( camIdx, cameraInfo );
			    if ( cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT  ) {
			        try {
			        	
			            cam = Camera.open( camIdx );
			            cam.setDisplayOrientation(90);
			        } catch (RuntimeException e) {
			           Log.d("Camera failed to open: Front Camera" + e.getLocalizedMessage());
			        }
			    }
			}
		}
		
		

		return cam;
	}

}
