package ranglerz.face.fun.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;

/**
 * Used to make file system use in the tutorial a bit more obvious
 * in a production environment you wouldn't make these calls static
 * as you have no way to mock them for testing
 * @author paul.blundell
 *
 */
public class MediaHelper {

	public static File getOutputMediaFile(){
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.
		
// create a folder with the name 
	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "OldyBaba");
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("failed to create directory");
	            return null;
	        }
	    }

	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile = new File(mediaStorageDir.getPath() + File.separator +"IMG_"+ timeStamp +".jpg");

	    return mediaFile;
	}

	public static boolean saveToFile(byte[] bytes, File file){
		boolean saved = false;
		try {
			FileOutputStream fos = new FileOutputStream(file);


			Bitmap realImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

			ExifInterface exif=new ExifInterface(file.toString());

			try {
				String orientation = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
				android.util.Log.d("tag",orientation);
			}
			catch (Exception e)
			{
				android.util.Log.d("tag",e.getMessage());
			}

			// rotate the image if camera provide the wrong orientation...
			if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("6")){
				realImage= rotate(realImage, 90);
			} else if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("8")){
				realImage= rotate(realImage, 270);
			} else if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("3")){
				realImage= rotate(realImage, 180);
			} else if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("0")){
				realImage= rotate(realImage, 90);
			}

			// flip the image taken..
			realImage = FlipBitmap(realImage);

			boolean bo = realImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			android.util.Log.d("tag", "saveToFile: " + bo);
			fos.close();


			saved = true;
		} catch (FileNotFoundException e) {
			Log.e("FileNotFoundException", e);
		} catch (IOException e) {
			Log.e("IOException", e);
		}
		return saved;
	}


	public static Bitmap rotate(Bitmap bitmap, int degree) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();

		Matrix mtx = new Matrix();
		//       mtx.postRotate(degree);
		mtx.setRotate(degree);

		return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
	}


	// flip image because front camera flip the image (Mirror Effect)
	public static Bitmap FlipBitmap(Bitmap b)
	{
		Matrix matrix = new Matrix();
		// Flip Image From Camera
		matrix.setScale(1, -1);
		matrix.postTranslate(0, b.getHeight());
		//     matrix.postRotate(270);
		return Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, true);
	}




}
