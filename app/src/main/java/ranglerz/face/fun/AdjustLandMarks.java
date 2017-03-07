package ranglerz.face.fun;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import ranglerz.face.fun.util.IabBroadcastReceiver;
import ranglerz.face.fun.util.IabHelper;
import ranglerz.face.fun.util.IabResult;
import ranglerz.face.fun.util.Inventory;
import ranglerz.face.fun.util.Purchase;

import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;
import ranglerz.face.fun.model.Globals;
import ranglerz.face.fun.util.BitmapHelper;
import ranglerz.face.fun.util.ExifUtil;
import ranglerz.face.fun.util.Log;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.nsd.NsdManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;



public class AdjustLandMarks extends Activity implements IabBroadcastReceiver.IabBroadcastListener {


	//dialog layout variable for glasses
	ImageView forSelGlass1;
	ImageView forSelGlass2;
	ImageView forSelGlass3;
	ImageView forSelGlass4;
	ImageView forSelGlass5;
	ImageView forSelGlass6;
	ImageView forSelGlass7;
	ImageView forSelGlass8;
	ImageView forSelGlass9;
	ImageView forSelGlass10;
	ImageView forSelGlass11;
	ImageView forSelGlass12;
	ImageView forSelGlass13;
	ImageView forSelGlass14;
	ImageView forSelGlass15;
	ImageView forSelGlass16;

	ProgressDialog pDilaog;
	Dialog dialog;

	//dialog layout variable for beard

	ImageView forSaleBear1;
	ImageView forSaleBear2;
	ImageView forSaleBear3;
	ImageView forSaleBear4;
	ImageView forSaleBear5;
	ImageView forSaleBear6;
	ImageView forSaleBear7;
	ImageView forSaleBear8;
	ImageView forSaleBear9;
	ImageView forSaleBear10;
	ImageView forSaleBear11;
	ImageView forSaleBear12;
	ImageView forSaleBear13;
	ImageView forSaleBear14;
	ImageView forSaleBear15;
	ImageView forSaleBear16;
	ImageView forSaleBear17;
	ImageView forSaleBear18;

	Dialog beardDialog;
	ProgressDialog pBeardDialog;

	ImageView forSaleHair1;
	ImageView forSaleHair2;
	ImageView forSaleHair3;
	ImageView forSaleHair4;
	ImageView forSaleHair5;
	ImageView forSaleHair6;
	ImageView forSaleHair7;
	ImageView forSaleHair8;
	ImageView forSaleHair9;
	ImageView forSaleHair10;
	ImageView forSaleHair11;
	ImageView forSaleHair12;
	ImageView forSaleHair13;
	ImageView forSaleHair14;
	ImageView forSaleHair15;
	ImageView forSaleHair16;

	Dialog hairDialog;
	ProgressDialog pHaireDialog;


	String TAG = "AdjustLandMarks";
	ImageView clock20,clock40,clock60,clock80,adjustFace,glassesIcon_imageView,beardIcon_imageView,hairIcon_imageView;
	ImageView glassesIcon_1,glassesIcon_2,glassesIcon_3,glassesIcon_4,glassesIcon_5,glassesIcon_6,glassesIcon_7,glassesIcon_8,glassesIcon_9;
	//image view for bought glass icon
	ImageView starGoggle1, boughtGoggle2, boughtGoggle3, boughtGoggle4, boughtGoggle5, boughtGoggle6, boughtGoggle7, boughtGoggle8, boughtGoggle9, boughtGoggle10, boughtGoggle11, boughtGoggle12, boughtGoggle13, boughtGoggle14, boughtGoggle15;
	ImageView boughtBeard1, boughtBeard2,boughtBeard3,boughtBeard4,boughtBeard5,boughtBeard6,boughtBeard7,boughtBeard8,boughtBeard9,boughtBeard10,boughtBeard11,boughtBeard12,boughtBeard13,boughtBeard14,boughtBeard15,boughtBeard16,boughtBeard17,boughtBeard18;
	ImageView beardIcon_1,beardIcon_2,beardIcon_3,beardIcon_4,beardIcon_5, beard_buy_more;
	ImageView dotsIcon_imageView;
	ImageView baldIcon_imageView;
	ImageView hairIcon_1,hairIcon_2,hairIcon_3,hairIcon_4,hairIcon_5,hairIcon_6,hairIcon_7,hairIcon_8,hairIcon_9,hairIcon_10,hairIcon_11,hairIcon_12;
	ImageView boughtHairIco1,  boughtHairIco2, boughtHairIco3, boughtHairIco4, boughtHairIco5, boughtHairIco6, boughtHairIco7, boughtHairIco8, boughtHairIco9, boughtHairIco10, boughtHairIco11, boughtHairIco12, boughtHairIco13, boughtHairIco14, boughtHairIco15, boughtHairIco16;
	ImageView baldIcon_1,baldIcon_2,baldIcon_3,baldIcon_4;
	ImageView rotateClock_imageView,rotateAntiClock_imageView,increaseSize_imageView,decreaseSize_imageView,done_imageView,undo_imageView,redo_imageView;
	SeekBar saturation_seekbar;
	ImageView adjustSaturation_imageView;
	MediaPlayer mp;
	public static Activity _instance;
   int option;
   int screenWidth,screenHeight;
   boolean isMainOptionsVisible = false;
   int selectedType=0;//1:hair, 2:glasses, 3:beard

	float rectWidth = 0;

   int detectedFaces=0;
   FaceDrawingView faceDrawingView;
   
   // codes for Activity
   public static final int CAMERA_CODE = 1;	
   public static final int GALLERY_CODE = 2;
   public static final int SELECT_PICTURE = 3;
   private static final int REQ_CAMERA_IMAGE = 123;
   private static final int IAPCODE = 10001;
    
   // check for Moving of Adjust Markers , // check for getImages from Gallery , // rotation to apply on camera Image
  boolean distanceMove  = false;
  boolean fromGallery=false;
  private static boolean Rotation = false;
  public float remainingHeight=0,foreHeadx1=0,foreHeady1=0,foreHeadx2=0,foreHeady2=0;;
  public RectF faceRect;

  // Relative Layout For main Layout and option for Clock and AdjustFaces last relative layout for placing touchable icons
   LinearLayout options_linearLayout,options_linearLayout1,options_linearLayout2,options_linearLayout3,options_linearLayout4,options_linearLayout5,options_linearLayout6;
   RelativeLayout options_relativeLayout7;
   RelativeLayout drawing_relativeLayout;
   RelativeLayout upperBar_relativeLayout1,upperBar_relativeLayout2;

   //IabHelper.QueryInventoryFinishedListener mGotInventoryListener ;
     String imgPath;


	// Camera Bitmap
	Bitmap camFace;
	
	 // next Button      
	Button next;

	// bitmaps for using detect faces
	public Bitmap cameraBitmap = null;
	public Bitmap oldBitmap = null;



	// for undo feature
	HashMap<Integer,Bitmap> undoBitmaps = new HashMap<>();
	public int currentPosition = 0;
	// for Bitmap position for undo
	HashMap<Integer,Integer> userPosition = new HashMap<>();


	// for redoBitmaps
	HashMap<Integer,Bitmap> redoBitmaps = new HashMap<>();
	// Bitmap Position for Redo
	HashMap<Integer,Integer> userPositionRedo = new HashMap<>();

	// current Position of User Redo.
	public int cuurentPositionRedo = 0;





	//inApp perchase ID for Goggles
	static final String SKU_GOGGLES_STAR = "buygoggle1";
	static final String SKU_GOGGLES_HEART = "buygoggle2";
	static final String SKU_GOGGLES_SQUAR_DOT = "buygoggle3";
	static final String SKU_GOGGLES_CICULAR_RED = "buygoggle4";
	static final String SKU_GOGGLES_YELLOW = "buygoggle5";
	static final String SKU_GOGGLES_BLACK_TRANSPARENT = "buygoggle6";
	static final String SKU_GOGGLES_BUTTERFLY_SKY_BLUE = "buygoggle7";
	static final String SKU_GOGGLES_BIGH_BROWN = "buygoggle8";
	static final String SKU_GOGGLES_ROUND_PURPLE = "buygoggle9";
	static final String SKU_GOGGLES_ROUND_RED = "buygoggle10";
	static final String SKU_GOGGLES_LIGHT_PIN_BOY = "buygoggle11";
	static final String SKU_GOGGLES_BOLD_BORDER_BLACK = "buygoggle12";
	static final String SKU_GOGGLES_BROWN_BLACK_BOY = "buygoggle13";
	static final String SKU_GOGGLES_RED_BORDER_SMALL = "buygoggle14";
	static final String SKU_GOGGLES_CIRCULAR_SKYBLUE = "buygoggle15";
	static final int RC_REQUEST = 10001;

	//inApp purchase ID for Beard
	static final String SKU_BEARD_MUSTACHE_BRWON_BEARD = "buybeard1";
	static final String SKU_BOLD_LONG_BEARD = "buybeard2";
	static final String SKU_BROWN_HANSOME_MAN_BEARD = "buybeard3";
	static final String SKU_CONE_BALCK_BEARD = "buybeard4";
	static final String SKU_LOCK_BROWN_BOLD_BEARD = "buybeard5";
	static final String SKU_SPREAD_BROWN_BEARD = "buybeard6";
	static final String SKU_BOLD_BROWN_MUSTACHE_THIN_BEARD = "buybeard7";
	static final String SKU_RED_BROWN_HANDSOM_MAN_BEARD = "buybeard8";
	static final String SKU_THIN_MUSTACHE_BOLD_BLACK_BEARD = "buybeard9";
	static final String SKU_THIN_MUSTACHE_LONG_BROWN_BREAD = "buybeard10";
	static final String SKU_THIN_MUSTACHE_BOLD_BEARD = "buybeard11";
	static final String SKU_LONG_BLACK_OLDY_BEARD = "buybeard12";
	static final String SKU_CROWN_WHITE_BEARD = "buybeard13";
	static final String SKU_LONG_MUSTACHE = "buybeard14";
	static final String SKU_BOARD_BEARD = "buybeard15";
	static final String SKU_HALF_EGG_BEARD = "buybeard16";
	static final String SKU_CONE_WITHE_BEARD = "buybeard17";
	static final String SKU_THIN_MUSTACHE_DOT_BEARD = "buybeard18";

	//heard buyiing id
	static final String BYUING_HAIR_1 = "buyhair1";
	static final String BYUING_HAIR_2 = "buyhair2";
	static final String BYUING_HAIR_3 = "buyhair3";
	static final String BYUING_HAIR_4 = "buyhair4";
	static final String BYUING_HAIR_5 = "buyhair5";
	static final String BYUING_HAIR_6 = "buyhair6";
	static final String BYUING_HAIR_7 = "buyhair7";
	static final String BYUING_HAIR_8 = "buyhair8";
	static final String BYUING_HAIR_9 = "buyhair9";
	static final String BYUING_HAIR_10 = "buyhair10";
	static final String BYUING_HAIR_11 = "buyhair11";
	static final String BYUING_HAIR_12 = "buyhair12";
	static final String BYUING_HAIR_13 = "buyhair13";
	static final String BYUING_HAIR_14 = "buyhair14";
	static final String BYUING_HAIR_15 = "buyhair15";
	static final String BYUING_HAIR_16 = "buyhair16";



	int goggleNumber = 0;
	int beardNumber = 0;
	int hairNumber = 0;

	IabHelper m_Helper;
	boolean mIsPremium = false;
	boolean mSubscribedToInfiniteGas = false;
	// Provides purchase notification while this app is running
	IabBroadcastReceiver mBroadcastReceiver;

	SharedPreferences sharedPreferences;


	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_adjust_points);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		_instance = this;

		//inAppBilling_onCreate();
		
		faceDrawingView = (FaceDrawingView) findViewById(R.id.faceDrawingView);
		mp = MediaPlayer.create(this, R.raw.button_music);
		
		// get width and height of Scree
		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();
		
		
		
		drawing_relativeLayout = (RelativeLayout) findViewById(R.id.drawing_relativeLayout);
		
		// Age Clocks
		clock20 = (ImageView) findViewById(R.id.imageViewClock20);
		clock40 = (ImageView) findViewById(R.id.imageViewClock40);
		clock60 = (ImageView) findViewById(R.id.imageViewClock60);
		clock80 = (ImageView) findViewById(R.id.imageViewClock80);
		dotsIcon_imageView = (ImageView) findViewById(R.id.dotsIcon_imageView);
		baldIcon_imageView = (ImageView) findViewById(R.id.baldIcon_imageView);
		glassesIcon_imageView = (ImageView) findViewById(R.id.glassesIcon_imageView);
		beardIcon_imageView = (ImageView) findViewById(R.id.beardIcon_imageView);
		hairIcon_imageView = (ImageView) findViewById(R.id.hairIcon_imageView);

		adjustSaturation_imageView = (ImageView) findViewById(R.id.adjustSaturation_imageView);
		
		saturation_seekbar = (SeekBar) findViewById(R.id.saturation_seekbar);
		saturation_seekbar.setVisibility(View.GONE);
		
		glassesIcon_1 = (ImageView) findViewById(R.id.glassesIcon1_imageView);
		glassesIcon_2 = (ImageView) findViewById(R.id.glassesIcon2_imageView);
		glassesIcon_3 = (ImageView) findViewById(R.id.glassesIcon3_imageView);
		glassesIcon_4 = (ImageView) findViewById(R.id.glassesIcon4_imageView);
		glassesIcon_5 = (ImageView) findViewById(R.id.glassesIcon5_imageView);
		glassesIcon_6 = (ImageView) findViewById(R.id.glassesIcon6_imageView);
		glassesIcon_7 = (ImageView) findViewById(R.id.glassesIcon7_imageView);
		glassesIcon_8 = (ImageView) findViewById(R.id.glassesIcon8_imageView);
		glassesIcon_9 = (ImageView) findViewById(R.id.glassesIcon9_imageView);

		//for sell gogles registering
		starGoggle1 = (ImageView) findViewById(R.id.star_goggle_1);
		starGoggle1.setVisibility(View.GONE);
		boughtGoggle2 = (ImageView) findViewById(R.id.bought_2_icon);
		boughtGoggle2.setVisibility(View.GONE);
		boughtGoggle3 = (ImageView) findViewById(R.id.bought_3_icon);
		boughtGoggle3.setVisibility(View.GONE);
		boughtGoggle4 = (ImageView) findViewById(R.id.bought_4_icon);
		boughtGoggle4.setVisibility(View.GONE);
		boughtGoggle5 = (ImageView) findViewById(R.id.bought_5_icon);
		boughtGoggle5.setVisibility(View.GONE);
		boughtGoggle6 = (ImageView) findViewById(R.id.bought_6_icon);
		boughtGoggle6.setVisibility(View.GONE);
		boughtGoggle7 = (ImageView) findViewById(R.id.bought_7_icon);
		boughtGoggle7.setVisibility(View.GONE);
		boughtGoggle8 = (ImageView) findViewById(R.id.bought_8_icon);
		boughtGoggle8.setVisibility(View.GONE);
		boughtGoggle9 = (ImageView) findViewById(R.id.bought_9_icon);
		boughtGoggle9.setVisibility(View.GONE);
		boughtGoggle10 = (ImageView) findViewById(R.id.bought_10_icon);
		boughtGoggle10.setVisibility(View.GONE);
		boughtGoggle11 = (ImageView) findViewById(R.id.bought_11_icon);
		boughtGoggle11.setVisibility(View.GONE);
		boughtGoggle12 = (ImageView) findViewById(R.id.bought_12_icon);
		boughtGoggle12.setVisibility(View.GONE);
		boughtGoggle13 = (ImageView) findViewById(R.id.bought_13_icon);
		boughtGoggle13.setVisibility(View.GONE);
		boughtGoggle14 = (ImageView) findViewById(R.id.bought_14_icon);
		boughtGoggle14.setVisibility(View.GONE);
		boughtGoggle15 = (ImageView) findViewById(R.id.bought_15_icon);
		boughtGoggle15.setVisibility(View.GONE);

		beardIcon_1 = (ImageView) findViewById(R.id.beardIcon1_imageView);
		beardIcon_2 = (ImageView) findViewById(R.id.beardIcon2_imageView);
		beardIcon_3 = (ImageView) findViewById(R.id.beardIcon3_imageView);
		beardIcon_4 = (ImageView) findViewById(R.id.beardIcon4_imageView);
		beardIcon_5 = (ImageView) findViewById(R.id.beardIcon5_imageView);
		beard_buy_more = (ImageView) findViewById(R.id.beard_buy_more_imageView);


		//************************************ Ranglers Team *******************************
		//for sale beard registring
		boughtBeard1 = (ImageView) findViewById(R.id.for_sale_beard_icon_1);
		boughtBeard1.setVisibility(View.GONE);
		boughtBeard2 = (ImageView) findViewById(R.id.for_sale_beard_icon_2);
		boughtBeard2.setVisibility(View.GONE);
		boughtBeard3 = (ImageView) findViewById(R.id.for_sale_beard_icon_3);
		boughtBeard3.setVisibility(View.GONE);
		boughtBeard4 = (ImageView) findViewById(R.id.for_sale_beard_icon_4);
		boughtBeard4.setVisibility(View.GONE);
		boughtBeard5 = (ImageView) findViewById(R.id.for_sale_beard_icon_5);
		boughtBeard5.setVisibility(View.GONE);
		boughtBeard6 = (ImageView) findViewById(R.id.for_sale_beard_icon_6);
		boughtBeard6.setVisibility(View.GONE);
		boughtBeard7 = (ImageView) findViewById(R.id.for_sale_beard_icon_7);
		boughtBeard7.setVisibility(View.GONE);
		boughtBeard8 = (ImageView) findViewById(R.id.for_sale_beard_icon_8);
		boughtBeard8.setVisibility(View.GONE);
		boughtBeard9 = (ImageView) findViewById(R.id.for_sale_beard_icon_9);
		boughtBeard9.setVisibility(View.GONE);
		boughtBeard10 = (ImageView) findViewById(R.id.for_sale_beard_icon_10);
		boughtBeard10.setVisibility(View.GONE);
		boughtBeard11 = (ImageView) findViewById(R.id.for_sale_beard_icon_11);
		boughtBeard11.setVisibility(View.GONE);
		boughtBeard12 = (ImageView) findViewById(R.id.for_sale_beard_icon_12);
		boughtBeard12.setVisibility(View.GONE);
		boughtBeard13 = (ImageView) findViewById(R.id.for_sale_beard_icon_13);
		boughtBeard13.setVisibility(View.GONE);
		boughtBeard14 = (ImageView) findViewById(R.id.for_sale_beard_icon_14);
		boughtBeard14.setVisibility(View.GONE);
		boughtBeard15 = (ImageView) findViewById(R.id.for_sale_beard_icon_15);
		boughtBeard15.setVisibility(View.GONE);
		boughtBeard16 = (ImageView) findViewById(R.id.for_sale_beard_icon_16);
		boughtBeard16.setVisibility(View.GONE);
		boughtBeard17 = (ImageView) findViewById(R.id.for_sale_beard_icon_17);
		boughtBeard17.setVisibility(View.GONE);
		boughtBeard18 = (ImageView) findViewById(R.id.for_sale_beard_icon_18);
		boughtBeard18.setVisibility(View.GONE);



		hairIcon_1 = (ImageView) findViewById(R.id.hairIcon1_imageView);
		hairIcon_2 = (ImageView) findViewById(R.id.hairIcon2_imageView);
		hairIcon_3 = (ImageView) findViewById(R.id.hairIcon3_imageView);
		hairIcon_4 = (ImageView) findViewById(R.id.hairIcon4_imageView);
		hairIcon_5 = (ImageView) findViewById(R.id.hairIcon5_imageView);
		hairIcon_6 = (ImageView) findViewById(R.id.hairIcon6_imageView);
		hairIcon_7 = (ImageView) findViewById(R.id.hairIcon7_imageView);
		hairIcon_8 = (ImageView) findViewById(R.id.hairIcon8_imageView);
		hairIcon_9 = (ImageView) findViewById(R.id.hairIcon9_imageView);
		hairIcon_10 = (ImageView) findViewById(R.id.hairIcon10_imageView);
		hairIcon_11 = (ImageView) findViewById(R.id.hairIcon11_imageView);
		hairIcon_12 = (ImageView) findViewById(R.id.hairIcon12_imageView);


		//rgistring bought harie icon

		boughtHairIco1 = (ImageView) findViewById(R.id.bought_hire_icon_1);
		boughtHairIco1.setVisibility(View.GONE);
		boughtHairIco2 = (ImageView) findViewById(R.id.bought_hire_icon_2);
		boughtHairIco2.setVisibility(View.GONE);
		boughtHairIco3 = (ImageView) findViewById(R.id.bought_hire_icon_3);
		boughtHairIco3.setVisibility(View.GONE);
		boughtHairIco4 = (ImageView) findViewById(R.id.bought_hire_icon_4);
		boughtHairIco4.setVisibility(View.GONE);
		boughtHairIco5 = (ImageView) findViewById(R.id.bought_hire_icon_5);
		boughtHairIco5.setVisibility(View.GONE);
		boughtHairIco6 = (ImageView) findViewById(R.id.bought_hire_icon_6);
		boughtHairIco6.setVisibility(View.GONE);
		boughtHairIco7 = (ImageView) findViewById(R.id.bought_hire_icon_7);
		boughtHairIco7.setVisibility(View.GONE);
		boughtHairIco8 = (ImageView) findViewById(R.id.bought_hire_icon_8);
		boughtHairIco8.setVisibility(View.GONE);
		boughtHairIco9 = (ImageView) findViewById(R.id.bought_hire_icon_9);
		boughtHairIco9.setVisibility(View.GONE);
		boughtHairIco10 = (ImageView) findViewById(R.id.bought_hire_icon_10);
		boughtHairIco10.setVisibility(View.GONE);
		boughtHairIco11 = (ImageView) findViewById(R.id.bought_hire_icon_11);
		boughtHairIco11.setVisibility(View.GONE);
		boughtHairIco12 = (ImageView) findViewById(R.id.bought_hire_icon_12);
		boughtHairIco12.setVisibility(View.GONE);
		boughtHairIco13 = (ImageView) findViewById(R.id.bought_hire_icon_13);
		boughtHairIco13.setVisibility(View.GONE);
		boughtHairIco14 = (ImageView) findViewById(R.id.bought_hire_icon_14);
		boughtHairIco14.setVisibility(View.GONE);
		boughtHairIco15 = (ImageView) findViewById(R.id.bought_hire_icon_15);
		boughtHairIco15.setVisibility(View.GONE);
		boughtHairIco16 = (ImageView) findViewById(R.id.bought_hire_icon_16);
		boughtHairIco16.setVisibility(View.GONE);



		baldIcon_1 = (ImageView) findViewById(R.id.baldIcon1_imageView);
		baldIcon_2 = (ImageView) findViewById(R.id.baldIcon2_imageView);
		baldIcon_3 = (ImageView) findViewById(R.id.baldIcon3_imageView);
		baldIcon_4 = (ImageView) findViewById(R.id.baldIcon4_imageView);

		
		rotateClock_imageView = (ImageView) findViewById(R.id.rotateClock_imageView);
		rotateAntiClock_imageView = (ImageView) findViewById(R.id.rotateAntiClock_imageView);
		increaseSize_imageView = (ImageView) findViewById(R.id.increaseSize_imageView);
		decreaseSize_imageView = (ImageView) findViewById(R.id.decreaseSize_imageView);
		done_imageView = (ImageView) findViewById(R.id.done_imageView);
		undo_imageView = (ImageView) findViewById(R.id.undo_imageView);
		redo_imageView = (ImageView) findViewById(R.id.redo_imageView);

		// imagevIew for AdjustFace OnClick
		adjustFace = (ImageView) findViewById(R.id.imageViewAdjustFace);
		
		
		next = (Button) findViewById(R.id.buttonNext);
		
		
		
		
		options_linearLayout = (LinearLayout) findViewById(R.id.options_linearLayout);
		options_linearLayout1 = (LinearLayout) findViewById(R.id.options_linearLayout1);
		options_linearLayout2 = (LinearLayout) findViewById(R.id.options_linearLayout2);
		options_linearLayout3 = (LinearLayout) findViewById(R.id.options_linearLayout3);
		options_linearLayout4 = (LinearLayout) findViewById(R.id.options_linearLayout4);
		options_linearLayout5 = (LinearLayout) findViewById(R.id.options_linearLayout5);
		options_linearLayout6 = (LinearLayout) findViewById(R.id.options_linearLayout6);
		options_relativeLayout7 = (RelativeLayout) findViewById(R.id.options_relativeLayout7);
		
		upperBar_relativeLayout1 = (RelativeLayout) findViewById(R.id.upperBar_relativeLayout1);
		upperBar_relativeLayout2 = (RelativeLayout) findViewById(R.id.upperBar_relativeLayout2);
		
		// get user choice whether user press Camera button or Gallery Button	
		Intent i = getIntent();
		option = i.getIntExtra("option", 0);
		

		ShowHideOptionsLayout(1);
		
		ShowHideUpperLayout2(false);

	
	    
	    if(option == CAMERA_CODE )
		{
			// rotation is used for rotating the camera image 
			Rotation = true;
			
			Intent intent = new Intent(AdjustLandMarks.this, CameraActivity.class);
			startActivityForResult(intent, REQ_CAMERA_IMAGE);
			
		}
		else if(option == GALLERY_CODE)
		{
			
			// rotation is not usere in Gallery Image 
			
			Rotation = false;
			fromGallery = true;

			Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			intent.setType("image/*");
			startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_PICTURE);
			
		}
	    
//		
		
		// when user select Adjust Face Option
		adjustFace.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					mp.start();
				
				
				ShowHideOptionsLayout(1);
		        
				SetMasks(0);
		    	
			}
		});
		
		adjustSaturation_imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ShowHideOptionsLayout(7);
				
			}
		});
		
		
// 	when user Ok The captured Image And Want to Apply Detection on it 
	next.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
			mp.start();

	   	     if(detectedFaces <=0)
	   	     {
	   	    	 Toast.makeText(AdjustLandMarks.this, "No Face Detected", Toast.LENGTH_LONG).show();
	   	    	 return;
	   	     }
	   	     
	   	     ShowHideOptionsLayout(2);
				SetMasks(1);
			}
			});

	    
	   clock20.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					mp.start();
				
					SetMasks(1);
			}
		});
	      
	   clock40.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					mp.start();
				
					SetMasks(2);
				
				
			}
		});
	   clock60.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					mp.start();
				
					SetMasks(3);
				
			}
		});
	   clock80.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					mp.start();
				
					SetMasks(4);

				
				
				
			}
		});
	   dotsIcon_imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					mp.start();

					setDots();
				
			}
		});
	   hairIcon_imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					mp.start();
					
					selectedType=1;
					ShowHideOptionsLayout(5);
			}
		   });
	   
	   baldIcon_imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					mp.start();
					isMainOptionsVisible = false;
					
					selectedType=1;
					ShowHideOptionsLayout(6);
				
			}
		});
	   
	   glassesIcon_imageView.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
				mp.start();
			selectedType=2;
			ShowHideOptionsLayout(3);
		}
	   });
	   
	   beardIcon_imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					mp.start();
					
					selectedType=3;
					ShowHideOptionsLayout(4);
				
			}
		   });
	   
	   

	   
	   glassesIcon_1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			setGlasses(1);
		}
	   });
	   glassesIcon_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setGlasses(2);
			}
	   });
	   glassesIcon_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setGlasses(3);
			}
	   });
	   glassesIcon_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setGlasses(4);
			}
	   });
	   glassesIcon_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setGlasses(5);
			}
	   });
	   glassesIcon_6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setGlasses(6);
			}
	   });
	   glassesIcon_7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setGlasses(7);
			}
		   

	   });
	   glassesIcon_8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				/*BuyGlasses buy = new BuyGlasses(getApplicationContext());
				buy.execute();*/

				initforDialog();
				forSellGlassesDialog();


				//showBuySubscriptionDialog();
			}
	   });
	   glassesIcon_9.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				//showBuySubscriptionDialog();
			}
	   });

		//******************** paid goggle click handler **************************
		paidGoggleClickListener();
		//************************ Handled by Ranglerz Team **************************



		beardIcon_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setBeard(1);
			}
	   });
	   
	   beardIcon_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setBeard(2);
			}
	   });
	   beardIcon_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setBeard(3);
			}
	   });
	   beardIcon_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setBeard(4);
			}
	   });
	   beardIcon_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setBeard(5);
			}
	   });

		//more beard icone click lister
		beard_buy_more.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {


				initForBeardDilaog();
				forSaleBeardDilaog();

				/*BuyBeard buyBeard = new BuyBeard(getApplicationContext());
				buyBeard.execute();
				*/
			}
		});

		//******************** paid beard click handler **************************
		paidBeardClickListener();
		//************************ Handled by Ranglerz Team **************************


	   
	   hairIcon_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setHair(1);
			}
	   });
	   hairIcon_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setHair(2);
			}
	   });
	   hairIcon_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setHair(3);
			}
	   });
	   hairIcon_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setHair(4);
			}
	   });
	   hairIcon_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setHair(5);
			}
	   });


		hairIcon_6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setHair(6);
			}
		});

		hairIcon_7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setHair(7);
			}
		});

		hairIcon_8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setHair(8);
			}
		});

		hairIcon_9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setHair(9);
			}
		});


		hairIcon_10.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setHair(10);
			}
		});
		hairIcon_11.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setHair(15);
			}
		});

		hairIcon_12.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				/*BuyHairs buy = new BuyHairs(getApplicationContext());
				buy.execute();
*/
				initForHairDialog();
				forSaleHairDialog();

			}
		});


		//******************** paid hairs click handler **************************
		paidHairClickListener();
		//************************ Handled by Ranglerz Team **************************



	   baldIcon_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setHair(11);
			}
	   });
	   baldIcon_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setHair(12);
			}
	   });
	   baldIcon_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setHair(13);
			}
	   });
	   baldIcon_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setHair(14);
			}
	   });
	   
	   
	   rotateClock_imageView.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			faceDrawingView.Rotate(selectedType, 1);
			
		}
	   });
	   
	   rotateAntiClock_imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				faceDrawingView.Rotate(selectedType, 2);
			}
	});
	   increaseSize_imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				faceDrawingView.Resize(selectedType, 1);
				
			}
		   });
	   decreaseSize_imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				faceDrawingView.Resize(selectedType, 2);
				
			}
		   });
	   done_imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ShowHideUpperLayout2(false);
			}
		   });


		undo_imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if( undoBitmaps.size() > 0 )
				{

					android.util.Log.d(TAG, " onClick: Undo Bitmap ");


					if(userPosition.get(currentPosition)== null) {

						android.util.Log.d(TAG, "onClick: Remove ALL Bitmaps");
						// now remove all bitmaps from canvas...
						// all undo are done...
						faceDrawingView.removeAllUnsedBitmaps();

						return;
					}





					// TODO: For Redo Option in app..

					// getting the Bitmap position
					// get the bitmap and add into redo array.
					//whe added after that remove from the undo array..

					int userPos = userPosition.get(currentPosition);
					android.util.Log.d(TAG, "onClick: Bitmap Position to add in to Redo Bitmap "+userPos);

					// increment the redo Postion to 1 for first time
					cuurentPositionRedo++;


					userPositionRedo.put(cuurentPositionRedo,userPos);


					if(userPos == Globals.SPOTPOSITION)
					{

						// get the Bitmap from the undo Array..
						Bitmap b1 = undoBitmaps.get(currentPosition);
						float dotsScale = (10*rectWidth)/100;
						dotsScale = dotsScale/b1.getWidth();
						android.util.Log.d(TAG, "onClick: Dot Scale"+dotsScale);
						b1 = 	 Bitmap.createScaledBitmap(b1,(int)(b1.getWidth()*dotsScale), (int)(b1.getHeight()*dotsScale),false);

						android.util.Log.d(TAG, "onClick: Adding Redo Bitmap  Spot width is " + b1.getWidth() + "Bitmap Height is " + b1.getHeight());

						// add this bitmap to the RedoBitmap Array..
					 redoBitmaps.put(cuurentPositionRedo, b1);
					}
					else if((userPos == Globals.HAIRPOSITION))
					{
						Bitmap b1 = undoBitmaps.get(currentPosition);

						float hairScale = (110*rectWidth)/100;
						hairScale = hairScale/b1.getWidth();
						android.util.Log.d(TAG, "onClick: Hair Scale"+hairScale);
						b1 = 	 Bitmap.createScaledBitmap(b1, (int) (b1.getWidth() * hairScale), (int) (b1.getHeight() * hairScale), false);

						android.util.Log.d(TAG, "onClick: Adding Redo Bitmap Hair width is " + b1.getWidth() + "Bitmap Height is " + b1.getHeight());

						redoBitmaps.put(cuurentPositionRedo, b1);

					}
					else if((userPos == Globals.GLASSESPOSITION))
					{
						Bitmap b1 = undoBitmaps.get(currentPosition);

						float glassesScale = (80*rectWidth)/100;
						android.util.Log.d(TAG, "onClick: Glasses Scale"+glassesScale);
						glassesScale = glassesScale/b1.getWidth();

						b1 = 	 Bitmap.createScaledBitmap(b1,(int)(b1.getWidth()*glassesScale), (int)(b1.getHeight()*glassesScale),false);

						android.util.Log.d(TAG, "onClick: Adding Redo Bitmap Glasses width is " + b1.getWidth() + "Bitmap Height is " + b1.getHeight());

						redoBitmaps.put(cuurentPositionRedo, b1);

					}
					else if((userPos == Globals.BEARDPOSITION))
					{
						Bitmap b1 = undoBitmaps.get(currentPosition);

						float beardScale = (80*rectWidth)/100;
						android.util.Log.d(TAG, "onClick: Beard Scale"+beardScale);
						beardScale = beardScale/b1.getWidth();

						b1   = 	 Bitmap.createScaledBitmap(b1,(int)(b1.getWidth()*beardScale), (int)(b1.getHeight()*beardScale),false);

						android.util.Log.d(TAG, "onClick: Adding Redo Bitmap Bear width is " + b1.getWidth() + "Bitmap Height is " + b1.getHeight());

						redoBitmaps.put(cuurentPositionRedo, b1);

					}


					// TODO: Now we have done with Redo
					// TODO: Here is the code for Redo Bitmaps...

					// remove bitmaps from array
					undoBitmaps.remove(currentPosition);
					// decrease user position
					userPosition.remove(currentPosition);

					// decrement the position of Bitmap..
					currentPosition--;




					// because here the position is decremented now
					// check if the bitmap is present or not...

					if(userPosition.get(currentPosition)== null) {

						android.util.Log.d(TAG, "onClick: Remove ALL Bitmaps");
						// now remove all bitmaps from canvas...
						// all undo are done...
						faceDrawingView.removeAllUnsedBitmaps();

						return;
					}



					int position = userPosition.get(currentPosition);

					android.util.Log.d(TAG, "onClick: Bitmap Position is "+position);

					if(position == Globals.SPOTPOSITION)
					{

						Bitmap b1 = undoBitmaps.get(currentPosition);
						float dotsScale = (10*rectWidth)/100;
						dotsScale = dotsScale/b1.getWidth();
						android.util.Log.d(TAG, "onClick: Dot Scale"+dotsScale);
						b1 = 	 Bitmap.createScaledBitmap(b1,(int)(b1.getWidth()*dotsScale), (int)(b1.getHeight()*dotsScale),false);

						android.util.Log.d(TAG, "onClick: Removing Bitmap  Spot width is " + b1.getWidth() + "Bitmap Height is " + b1.getHeight());

						// redraw the previous bitmap to convas...
						faceDrawingView.drawUndoBitmap(b1,position);
					}
					else if((position == Globals.HAIRPOSITION))
					{
						Bitmap b1 = undoBitmaps.get(currentPosition);

						float hairScale = (110*rectWidth)/100;
						hairScale = hairScale/b1.getWidth();
						android.util.Log.d(TAG, "onClick: Hair Scale"+hairScale);
						b1 = 	 Bitmap.createScaledBitmap(b1, (int) (b1.getWidth() * hairScale), (int) (b1.getHeight() * hairScale), false);

						android.util.Log.d(TAG, "onClick: Removing Bitmap Hair width is " + b1.getWidth() + "Bitmap Height is " + b1.getHeight());

						// redraw the previous bitmap to convas...
						faceDrawingView.drawUndoBitmap(b1,position);

					}
					else if((position == Globals.GLASSESPOSITION))
					{
						Bitmap b1 = undoBitmaps.get(currentPosition);

						float glassesScale = (80*rectWidth)/100;
						android.util.Log.d(TAG, "onClick: Glasses Scale"+glassesScale);
						glassesScale = glassesScale/b1.getWidth();

						 b1 = 	 Bitmap.createScaledBitmap(b1,(int)(b1.getWidth()*glassesScale), (int)(b1.getHeight()*glassesScale),false);

						android.util.Log.d(TAG, "onClick: Removing Bitmap Glasses width is " + b1.getWidth() + "Bitmap Height is " + b1.getHeight());

						// redraw the previous bitmap to convas...
						faceDrawingView.drawUndoBitmap(b1,position);

					}
					else if((position == Globals.BEARDPOSITION))
					{
						Bitmap b1 = undoBitmaps.get(currentPosition);

						float beardScale = (80*rectWidth)/100;
						android.util.Log.d(TAG, "onClick: Beard Scale"+beardScale);
						beardScale = beardScale/b1.getWidth();

						b1   = 	 Bitmap.createScaledBitmap(b1,(int)(b1.getWidth()*beardScale), (int)(b1.getHeight()*beardScale),false);

						android.util.Log.d(TAG, "onClick: Removing Bitmap Bear width is " + b1.getWidth() + "Bitmap Height is " + b1.getHeight());

						// redraw the previous bitmap to convas...
						faceDrawingView.drawUndoBitmap(b1,position);

					}



				}

				else
				{
					android.util.Log.d(TAG, "onClick: Remove ALL Bitmaps");
					// now remove all bitmaps from canvas...
					// all undo are done...
					faceDrawingView.removeAllUnsedBitmaps();
				}


			}
		});


		redo_imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				android.util.Log.d(TAG, "onClick Redo: Curren Position of undo is " + currentPosition);

				// TODO: get the last inserted bitmap from Redo ArrayList
				// TODO: Add this to the end of Undo ArrayList...
				// TODO: Show this bitmap..to the Canvas..

				if (redoBitmaps != null || redoBitmaps.size() > 0) {

					if (userPositionRedo.get(cuurentPositionRedo) == null || userPositionRedo.size() == 0)
						return;

					int userPos = userPositionRedo.get(cuurentPositionRedo);


					// increment the undo Position..
					currentPosition++;

					// add postion to Bitmap position
					userPosition.put(currentPosition, userPos);

					if (userPos == Globals.SPOTPOSITION) {

						// get the last inserted bitmap from redo ArrayList
						Bitmap b1 = redoBitmaps.get(cuurentPositionRedo);

						float dotsScale = (10 * rectWidth) / 100;
						dotsScale = dotsScale / b1.getWidth();
						android.util.Log.d(TAG, "onClick: Dot Scale" + dotsScale);
						b1 = Bitmap.createScaledBitmap(b1, (int) (b1.getWidth() * dotsScale), (int) (b1.getHeight() * dotsScale), false);

						android.util.Log.d(TAG, "onClick: Removing Bitmap  Spot width is " + b1.getWidth() + "Bitmap Height is " + b1.getHeight());

						// add this bitmap to the RedoBitmap Array..
						undoBitmaps.put(currentPosition, b1);
						faceDrawingView.drawUndoBitmap(b1, userPos);
					} else if ((userPos == Globals.HAIRPOSITION)) {
						// get the last inserted bitmap from redo ArrayList
						Bitmap b1 = redoBitmaps.get(cuurentPositionRedo);

						float hairScale = (110 * rectWidth) / 100;
						hairScale = hairScale / b1.getWidth();
						android.util.Log.d(TAG, "onClick: Hair Scale" + hairScale);
						b1 = Bitmap.createScaledBitmap(b1, (int) (b1.getWidth() * hairScale), (int) (b1.getHeight() * hairScale), false);

						android.util.Log.d(TAG, "onClick: Removing Bitmap Hair width is " + b1.getWidth() + "Bitmap Height is " + b1.getHeight());

						undoBitmaps.put(currentPosition, b1);
						faceDrawingView.drawUndoBitmap(b1, userPos);

					} else if ((userPos == Globals.GLASSESPOSITION)) {
						// get the last inserted bitmap from redo ArrayList
						Bitmap b1 = redoBitmaps.get(cuurentPositionRedo);

						float glassesScale = (80 * rectWidth) / 100;
						android.util.Log.d(TAG, "onClick: Glasses Scale" + glassesScale);
						glassesScale = glassesScale / b1.getWidth();

						b1 = Bitmap.createScaledBitmap(b1, (int) (b1.getWidth() * glassesScale), (int) (b1.getHeight() * glassesScale), false);

						android.util.Log.d(TAG, "onClick: Removing Bitmap Glasses width is " + b1.getWidth() + "Bitmap Height is " + b1.getHeight());

						undoBitmaps.put(currentPosition, b1);
						faceDrawingView.drawUndoBitmap(b1, userPos);

					} else if ((userPos == Globals.BEARDPOSITION)) {
						// get the last inserted bitmap from redo ArrayList
						Bitmap b1 = redoBitmaps.get(cuurentPositionRedo);

						float beardScale = (80 * rectWidth) / 100;
						android.util.Log.d(TAG, "onClick: Beard Scale" + beardScale);
						beardScale = beardScale / b1.getWidth();

						b1 = Bitmap.createScaledBitmap(b1, (int) (b1.getWidth() * beardScale), (int) (b1.getHeight() * beardScale), false);

						android.util.Log.d(TAG, "onClick: Removing Bitmap Bear width is " + b1.getWidth() + "Bitmap Height is " + b1.getHeight());

						undoBitmaps.put(currentPosition, b1);
						faceDrawingView.drawUndoBitmap(b1, userPos);

					}


					// remove bitmap from redoArrayList
					redoBitmaps.remove(cuurentPositionRedo);
					// remove the position of bitmap from Bitmap positions
					userPositionRedo.remove(cuurentPositionRedo);

					// now decrement the position to point to previous bitmap..
					cuurentPositionRedo--;


				}
			}
		});


	   
	   saturation_seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

		   @Override
		   public void onStopTrackingTouch(SeekBar seekBar) {
			   // TODO Auto-generated method stub
		   }

		   @Override
		   public void onStartTrackingTouch(SeekBar seekBar) {
			   // TODO Auto-generated method stub
		   }

		   @Override
		   public void onProgressChanged(SeekBar seekBar, int progress,
										 boolean fromUser) {
			   try {
				   android.util.Log.d(TAG, "onProgressChanged: ");
				   float progress1 = progress + 40;
				   float saturation = (float) progress1 / 100;
				   Log.d("saturation: " + saturation);
				   faceDrawingView.setMaskSaturation(saturation);
			   } catch (NullPointerException n) {
				   n.printStackTrace();
			   }
		   }
	   });


		pDilaog = new ProgressDialog(AdjustLandMarks.this);



		dialog = new Dialog(this);
		beardDialog = new Dialog(this);
		pBeardDialog = new ProgressDialog(AdjustLandMarks.this);


		hairDialog = new Dialog(this);
		pHaireDialog = new ProgressDialog(AdjustLandMarks.this);



		//inapp perchase custome
		keyInitialization();
		startUpLab();
		loadData();

		glassesIcon_9.setVisibility(View.GONE);



	}//end of onCreate
	
	private void SetMasks(int option)
	{
		android.util.Log.d(TAG, "SetMasks: ");
		saturation_seekbar.setVisibility(View.VISIBLE);
		
		Bitmap rightEye=null,leftEye=null,chin=null,forehead=null;

		if(option==0)
		{
			faceDrawingView.setMasks(false,null,null,null,null);
			return;

		}
		else if(option==1)//20
		{
			rightEye = BitmapFactory.decodeResource(getResources(),R.drawable.righteye_20);
		   	leftEye = BitmapFactory.decodeResource(getResources(),R.drawable.lefteye_20);
		   	chin = BitmapFactory.decodeResource(getResources(),R.drawable.chin_20);
		   	forehead = BitmapFactory.decodeResource(getResources(),R.drawable.forehead_20);
	   		
		}
		else if(option==2)//40
		{
			rightEye = BitmapFactory.decodeResource(getResources(),R.drawable.righteye_40);
		   	leftEye = BitmapFactory.decodeResource(getResources(),R.drawable.lefteye_40);
		   	chin = BitmapFactory.decodeResource(getResources(),R.drawable.chin_40);
		   	forehead = BitmapFactory.decodeResource(getResources(),R.drawable.forehead_40);
	   		
		}
		else if(option==3)//60
		{
			rightEye = BitmapFactory.decodeResource(getResources(),R.drawable.righteye_60);
		   	leftEye = BitmapFactory.decodeResource(getResources(),R.drawable.lefteye_60);
		   	chin = BitmapFactory.decodeResource(getResources(),R.drawable.chin_60);
		   	forehead = BitmapFactory.decodeResource(getResources(),R.drawable.forehead_60);
	   		
		}
		else if(option==4)//80 years
		{
			rightEye = BitmapFactory.decodeResource(getResources(),R.drawable.righteye_80);
		   	leftEye = BitmapFactory.decodeResource(getResources(),R.drawable.lefteye_80);
		   	chin = BitmapFactory.decodeResource(getResources(),R.drawable.chin_80);
		   	forehead = BitmapFactory.decodeResource(getResources(),R.drawable.forehead_80);
	   		
		}

		rectWidth = (faceRect.right - faceRect.left);
   		float rectHeight = (faceRect.bottom - faceRect.top);

   		float rightEyeScale = (45*rectWidth)/100;
   		float leftEyeScale = (45*rectWidth)/100;
   		float chinScale = (90*rectWidth)/100;
   		float foreheadScale = (100*rectWidth)/100;


   		rightEyeScale = rightEyeScale/rightEye.getWidth();
   		leftEyeScale = leftEyeScale/leftEye.getWidth();
   		chinScale = chinScale/chin.getWidth();
   		foreheadScale = foreheadScale/forehead.getWidth();



//   		rightEyeScale=3;
   		Log.d("rect width:"+rectWidth+" rect height:"+rectHeight+"righteyescal:"+rightEyeScale);
   		
   	    Bitmap newrightEye = 	 Bitmap.createScaledBitmap(rightEye,(int)(rightEye.getWidth()*rightEyeScale), (int)(rightEye.getHeight()*rightEyeScale),false);
   	    Bitmap newleftEye = 	 Bitmap.createScaledBitmap(leftEye,(int)(leftEye.getWidth()*leftEyeScale), (int)(leftEye.getHeight()*leftEyeScale),false);
   	    Bitmap newChin = 	 Bitmap.createScaledBitmap(chin,(int)(chin.getWidth()*chinScale), (int)(chin.getHeight()*chinScale),false);
   	    Bitmap newForehead = 	 Bitmap.createScaledBitmap(forehead,(int)(forehead.getWidth()*foreheadScale), (int)(forehead.getHeight()*foreheadScale),false);

   	    faceDrawingView.setMasks(true,newrightEye,newleftEye,newChin,newForehead);

	}


	
	private void setGlasses(int option)
	{

		android.util.Log.d(TAG, "setGlasses: ");
		ShowHideUpperLayout2(true);
		currentPosition++;
		if(option == 1)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.glasses_1);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 2)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.glasses_2);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 3)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.glasses_3);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 4)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.glasses_4);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 5)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.glasses_5);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 6)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.glasses_6);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 7)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.glasses_7);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 8)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.glasses_8);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		
		
		

	}




	// Loading bitmap OFF the UI Thread Best Practices..
	public class BitmapWorkerTaskForGlasses extends AsyncTask<Void, Void, Bitmap>
	{
		Bitmap glasses =null;
		int resourceId = 0;

		public BitmapWorkerTaskForGlasses(int  resourceId) {
			this.resourceId = resourceId;
		}

		// Decode image in background.
		@Override
		protected Bitmap doInBackground(Void... params) {
			android.util.Log.d(TAG, "doInBackground: Glasses Bitmap");
			glasses =  BitmapHelper.decodeSampledBitmapFromResource(getResources(),resourceId,250,250);
			return glasses;
		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if ( bitmap != null) {


				float glassesScale = (80*rectWidth)/100;
				android.util.Log.d(TAG, "onClick: Glasses Scale"+glassesScale);
				glassesScale = glassesScale/bitmap.getWidth();

				Bitmap newglasses = Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*glassesScale), (int)(bitmap.getHeight()*glassesScale),false);
				faceDrawingView.setGlasses(true, newglasses);

				// add bitmaps to array of bitmaps for redoing when user wants..
				undoBitmaps.put(currentPosition,newglasses);

				android.util.Log.d(TAG, "onClick: Adding Bitmap Glasses width is " + newglasses.getWidth() + "Bitmap Height is " + newglasses.getHeight());
				android.util.Log.d(TAG, "onPostExecute: Position is" + currentPosition + " Size of Undo Array is" + undoBitmaps.size());

			}
		}

	}





	private void setBeard(int option)
	{


		android.util.Log.d(TAG, "setBeard: ");
		ShowHideUpperLayout2(true);
		currentPosition++;
		if(option == 1)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.beard_1);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);

		}
		else if(option == 2)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.beard_2);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}
		else if(option == 3)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.beard_3);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}
		else if(option == 4)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.beard_4);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}
		else if(option == 5)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.beard_5);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}
		

	}



	// Loading bitmap OFF the UI Thread Best Practices..
	public class BitmapWorkerTaskForBeard extends AsyncTask<Void, Void, Bitmap>
	{
		Bitmap glasses =null;
		int resourceId = 0;

		public BitmapWorkerTaskForBeard(int  resourceId) {

			this.resourceId = resourceId;
		}

		// Decode image in background.
		@Override
		protected Bitmap doInBackground(Void... params) {
			android.util.Log.d(TAG, "doInBackground: Beard Bitmap");
			glasses =  BitmapHelper.decodeSampledBitmapFromResource(getResources(),resourceId,250,250);
			return glasses;
		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if ( bitmap != null) {


				float beardScale = (80*rectWidth)/100;
				beardScale = beardScale/bitmap.getWidth();

				Bitmap newbeard = 	 Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*beardScale), (int)(bitmap.getHeight()*beardScale),false);
				faceDrawingView.setBeard(true, newbeard);

				// add bitmaps to array of bitmaps for redoing when user wants..
				undoBitmaps.put(currentPosition,newbeard);

				android.util.Log.d(TAG, "onClick: Adding Bitmap Bear width is " + newbeard.getWidth() + "Bitmap Height is " + newbeard.getHeight());
				android.util.Log.d(TAG, "onPostExecute: Position is" + currentPosition + " Size of Undo Array is" + undoBitmaps.size());

			}
		}


	}


	private void setDots()
	{

		android.util.Log.d(TAG, "setDots: ");
		Bitmap spots =null;
		
		spots =  BitmapHelper.decodeSampledBitmapFromResource(getResources(), R.drawable.spots, 150, 150);


   		float dotsScale = (10*rectWidth)/100;
		android.util.Log.d(TAG, "onClick: Dot Scale"+dotsScale);
		dotsScale = dotsScale/spots.getWidth();

   		
   	    spots = 	 Bitmap.createScaledBitmap(spots,(int)(spots.getWidth()*dotsScale), (int)(spots.getHeight()*dotsScale),false);
   	    faceDrawingView.setSpots(true, spots);




		// increment the current position
		currentPosition++;
		// add bitmaps to array of bitmaps for redoing when user wants..
		undoBitmaps.put(currentPosition, spots);

		// user position
		userPosition.put(currentPosition, Globals.SPOTPOSITION);

		android.util.Log.d(TAG, "onClick: Adding Bitmap Dots width is " + spots.getWidth() + "Bitmap Height is " + spots.getHeight());
		android.util.Log.d(TAG, "onPostExecute: Position is"+currentPosition+" Size of Undo Array is"+undoBitmaps.size());


	}

	private void setHair(int option)
	{

		android.util.Log.d(TAG, "setHair: ");
		ShowHideUpperLayout2(true);

		currentPosition++;
		if(option == 1)
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.hair_1,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		else if(option == 2)
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.hair_2,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		else if(option == 3)
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.hair_3,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		else if(option == 4)
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.hair_4,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		else if(option == 5)
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.hair_5,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}

		else if(option == 6)
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.hair_6,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		else if(option == 7)
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.hair_7,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		else if(option == 8)
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.hair_8,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		else if(option == 9)
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.hair_9,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}else if(option == 10)
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.hair_10,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}

		else if(option == 15)
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.hair_11,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}

		else if(option == 16)
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.hair_12,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}


		// TODO: this option is for baldnes..

		else if(option == 11)//for baldness
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.bald_1,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		else if(option == 12)
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.bald_2,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		else if(option == 13)
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.bald_3,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		else if(option == 14)
		{
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.bald_4,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		

	}



	// Loading bitmap OFF the UI Thread Best Practices..
	public class BitmapWorkerTaskForHair extends AsyncTask<Void, Void, Bitmap>
	{
		Bitmap glasses =null;
		int resourceId = 0;
		int bitmapPosition = 0;

		public BitmapWorkerTaskForHair(int  resourceId , int bitmapPosition) {

			this.resourceId = resourceId;
			this.bitmapPosition = bitmapPosition;
		}

		// Decode image in background.
		@Override
		protected Bitmap doInBackground(Void... params) {
			android.util.Log.d(TAG, "doInBackground: Glasses Bitmap");
			glasses =  BitmapHelper.decodeSampledBitmapFromResource(getResources(),resourceId,250,250);
			return glasses;
		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if ( bitmap != null) {


				float hairScale = (110*rectWidth)/100;
				android.util.Log.d(TAG, "onClick: Hair Scale"+hairScale);
				hairScale = hairScale/bitmap.getWidth();
				Bitmap newhair = 	 Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*hairScale), (int)(bitmap.getHeight()*hairScale),false);
				faceDrawingView.setHair(true, newhair);


				// add bitmaps to array of bitmaps for redoing when user wants..
				undoBitmaps.put(currentPosition,newhair);

				android.util.Log.d(TAG, "onClick: Adding Bitmap haird width is " + newhair.getWidth() + "Bitmap Height is " + newhair.getHeight());
				android.util.Log.d(TAG, "onPostExecute: Position is"+currentPosition+" Size of Undo Array is"+undoBitmaps.size());

			}
		}


	}





	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		android.util.Log.d(TAG, "onActivityResult: ");
		 if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {

			 // get data from intent
			 Uri selectedImageUri = data.getData();

			 // get path to image from uri
			 //   imgPath = ImageFilePath.getPath(getApplicationContext(), selectedImageUri);

			 String[] projection = {MediaStore.MediaColumns.DATA};
			 CursorLoader cursorLoader = new CursorLoader(AdjustLandMarks.this, selectedImageUri, projection, null, null, null);
			 Cursor cursor = cursorLoader.loadInBackground();
			 int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
			 cursor.moveToFirst();
			 imgPath = cursor.getString(column_index);
			 cursor.close();

			 displayImageGallery(imgPath);
            
        
		 }
		 
	 else
	if(requestCode == SELECT_PICTURE && resultCode == RESULT_CANCELED){
		Log.i("User cannot select picture");
		finish();
	}
		
		
		
		if(requestCode == REQ_CAMERA_IMAGE && resultCode == RESULT_OK){
		imgPath = data.getStringExtra(CameraActivity.EXTRA_IMAGE_PATH);
			Log.i("Got image path: "+ imgPath);
			displayImage(imgPath);

			// send broadcast to all activity that new picture is taken..
			Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
			File f = new File(imgPath);
			Uri contentUri = Uri.fromFile(f);
			mediaScanIntent.setData(contentUri);
			AdjustLandMarks.this.sendBroadcast(mediaScanIntent);

		} else
		if(requestCode == REQ_CAMERA_IMAGE && resultCode == RESULT_CANCELED){
			Log.i("User didn't take an image");
			finish();
		}
		
		else if(requestCode == IAPCODE )
		{
			android.util.Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + data);
	        if (mHelper == null) return;

	        // Pass on the activity result to the helper for handling
	        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {




	            super.onActivityResult(requestCode, resultCode, data);
	        }
	        else {
	           android.util.Log.d(TAG, "onActivityResult handled by IABUtil.");
	        }
			
		}

		/*//inApp perchase result part part
		android.util.Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + data);
		if (mHelper == null) return;

		// Pass on the activity result to the helper for handling
		if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
			// not handled, so handle it ourselves (here's where you'd
			// perform any handling of activity results not related to in-app
			// billing...
			super.onActivityResult(requestCode, resultCode, data);
		}
		else {
			android.util.Log.d(TAG, "onActivityResult handled by IABUtil.");
		}*/
		
		
	}
	
	private void displayImageGallery(String path) {
		// TODO Auto-generated method stub
		
		// no rotate the gallery iamge only rotate camera image after taken

		android.util.Log.d(TAG, "displayImageGallery: " + path);


		BitmapWorkerCamFace camFace = new BitmapWorkerCamFace(path);
		camFace.execute();

	}

	// Loading bitmap OFF the UI Thread Best Practices..
	public class BitmapWorkerCamFace extends AsyncTask<Void, Void, Bitmap>
	{

		String path = null;

		public BitmapWorkerCamFace(String  path) {
			this.path = path;
		}


		// Decode image in background.
		@Override
		protected Bitmap doInBackground(Void... params) {
			android.util.Log.d(TAG, "doInBackground: Camera Face");
			return ExifUtil.rotateBitmap(path, BitmapFactory.decodeFile(path));
		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(Bitmap camFace) {
			if ( camFace != null) {
				cameraBitmap = camFace;

				android.util.Log.d(TAG, "onPostExecute: Camera Face");
				BitmapWorkerGallery oldFace = new BitmapWorkerGallery(R.drawable.forehead_40);
				oldFace.execute();

			}
		}
	}




	// Loading bitmap OFF the UI Thread Best Practices..
	public class BitmapWorkerGallery extends AsyncTask<Void, Void, Bitmap>
	{
		Bitmap glasses =null;
		int resourceId = 0;

		public BitmapWorkerGallery(int  resourceId) {
			this.resourceId = resourceId;
		}

		// Decode image in background.
		@Override
		protected Bitmap doInBackground(Void... params) {
			android.util.Log.d(TAG, "doInBackground: Gallery Face");
			return BitmapHelper.decodeSampledBitmapFromResource(getResources(), resourceId, 300, 400);
		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(Bitmap oldFace) {
			if ( oldFace != null) {
				oldBitmap = oldFace;

				android.util.Log.d(TAG, "onPostExecute: Gallery");
				// after the execution of both doInBackgrounds
				// Now Detect Faces...
				Globals.oldBitmap = cameraBitmap;
				detectFace(cameraBitmap, oldBitmap);
			}
		}
	}




	// use to display the Bitmap Image on ImageView
	private void displayImage(String path) {


		saturation_seekbar.setVisibility(View.INVISIBLE);

		android.util.Log.d(TAG, "displayImage:Camera ");

		// execute the bitmap task..
		BitmapWorkerCameraCamFace camera = new BitmapWorkerCameraCamFace(path);
		camera.execute();

		
	}

	// Loading bitmap OFF the UI Thread Best Practices..
	public class BitmapWorkerCameraCamFace extends AsyncTask<Void, Void, Bitmap>
	{
		String path = null;

		public BitmapWorkerCameraCamFace(String  path) {
			this.path = path;
		}

		// Decode image in background.
		@Override
		protected Bitmap doInBackground(Void... params) {
			android.util.Log.d(TAG, "doInBackground: Camera Face");
			return BitmapHelper.decodeSampledBitmap(path, 300, 400);
		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(Bitmap camFace) {
			if ( camFace != null) {

				android.util.Log.d(TAG, "onPostExecute: Camera Face");
				cameraBitmap = camFace;

				BitmapWorkerCameraOldFace oldFace = new BitmapWorkerCameraOldFace(R.drawable.forehead_40);
				oldFace.execute();

			}
		}


	}



	// Loading bitmap OFF the UI Thread Best Practices..
	public class BitmapWorkerCameraOldFace extends AsyncTask<Void, Void, Bitmap>
	{
		int resourceId = 0;

		public BitmapWorkerCameraOldFace(int  resourceId) {
			this.resourceId = resourceId;
		}

		// Decode image in background.
		@Override
		protected Bitmap doInBackground(Void... params) {
			android.util.Log.d(TAG, "doInBackground: Camera Old Face");
			return BitmapFactory.decodeResource(getResources(), resourceId);
		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(Bitmap oldFace) {
			if ( oldFace != null) {

				oldBitmap = oldFace;
				android.util.Log.d(TAG, "onPostExecute: Camera old Face");

				// after the execution of both doInBackgrounds
				// Now Detect Faces...


				Globals.oldBitmap = Bitmap.createScaledBitmap(cameraBitmap,cameraBitmap.getWidth(),cameraBitmap.getHeight(),false);


				int faceDetected =  detectFace(cameraBitmap,oldBitmap);

				if(faceDetected <= 0)
				{
					Toast.makeText(getApplicationContext(),"Kindly Retake Picture With Face Infront of Camera",Toast.LENGTH_SHORT).show();

					Intent intent = new Intent(AdjustLandMarks.this, AdjustLandMarks.class);
					intent.putExtra("option", CAMERA_CODE);
					startActivity(intent);
					finish();

				}



			}
		}


	}



	// detect face is used to detect face and apply image on face area 
	
	private int  detectFace(Bitmap camFace, Bitmap oldFace){

		android.util.Log.d(TAG, "detectFace: ");
		
		double faceScale,scaleWidth,scaleHeight;


//		camFace = desaturateBitmap(camFace);
		// get camFace Width Height
		int width = camFace.getWidth();
		int height = camFace.getHeight();
		
		faceScale =(double) (screenHeight - (options_linearLayout.getLayoutParams().height))/height;
		 Log.d("faceScale:"+faceScale);
		// get Scaled Height and Width
		 scaleWidth =(double) faceScale*width;
	     scaleHeight = (double)faceScale*height;

	     android.util.Log.d("msg","Cam Face Width Height is   " +width +"and" + height );
	     android.util.Log.d("msg","Scaled Factor Width and heigth is  " +scaleWidth +"and" + scaleHeight );
	     android.util.Log.d("msg","Screen Width and height is  " +screenWidth +"and" + screenHeight );

		 
		// CREATE A MATRIX FOR THE MANIPULATION
		 
		Matrix matrix = new Matrix();
		 
		// RESIZE THE BIT MAP
		 
		matrix.postScale((float)scaleWidth, (float)scaleHeight);
		 
		// RECREATE THE NEW BITMAP
	//	Bitmap resizedBitmap = Bitmap.createBitmap(camFace, 0, 0, width, height, matrix, false);
		 
		Bitmap resizedBitmap = 	 Bitmap.createScaledBitmap(camFace,(int)(scaleWidth), (int)(scaleHeight),true);
		Bitmap canvasOverlay = BitmapFactory.decodeResource(getResources(),R.drawable.canvasoverlay);
		canvasOverlay= 	 Bitmap.createScaledBitmap(canvasOverlay,(int)(scaleWidth), (int)(scaleHeight),true);
		
		 //Create a Paint object for drawing with
        Paint myRectPaint = new Paint();
        myRectPaint.setStrokeWidth(5);
        myRectPaint.setColor(Color.RED);
        myRectPaint.setStyle(Paint.Style.STROKE);

        
        Paint landmarksPaint = new Paint();
        landmarksPaint.setStrokeWidth(10);
        landmarksPaint.setColor(Color.RED);
        landmarksPaint.setStyle(Paint.Style.STROKE);
    
        FaceDetector faceDetector =
                new FaceDetector.Builder(getApplicationContext())
                .setTrackingEnabled(false)
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .build();
        
        //!!!
        //Cannot resolve method setTrackingEnabled(boolean)
        //skip for now
        //faceDetector.setTrackingEnabled(false);
        ViewGroup.LayoutParams param = faceDrawingView.getLayoutParams();
        param.width = resizedBitmap.getWidth();
        param.height = resizedBitmap.getHeight();
        faceDrawingView.setLayoutParams(param);
        faceDrawingView.setBitmap(resizedBitmap,canvasOverlay);

//        resizedBitmap = faceDrawingView.getBitmap();
        Frame frame = new Frame.Builder().setBitmap(resizedBitmap).build();
        SparseArray<Face> faces = faceDetector.detect(frame);
        detectedFaces = faces.size();


		android.util.Log.d(TAG, "detectFace: "+detectedFaces);

		if(detectedFaces <=0)
			return 0;


        //Draw Rectangles on the Faces
        for(int i=0; i<faces.size(); i++) {
           
        	Face thisFace = faces.valueAt(i);

			ranglerz.face.fun.model.Face f = new ranglerz.face.fun.model.Face();

            float foreHeadHeight =((50*thisFace.getHeight())/100);

            float x1 = thisFace.getPosition().x;
            float y1 = thisFace.getPosition().y;
            float x2 = x1 + thisFace.getWidth();
            float y2 = y1 + foreHeadHeight;

			remainingHeight = thisFace.getHeight() - foreHeadHeight;


            Log.d("face height: "+thisFace.getHeight()+" remaining height:"+remainingHeight);

            // use to get the tilt Angle  of the image
			float angleY = thisFace.getEulerY();
			float angleR = thisFace.getEulerZ();


			foreHeadx1 = x1;
			foreHeady1 = y1;
			foreHeadx2 = x2;
			foreHeady2 = y2;

			faceRect = new RectF(thisFace.getPosition().x,thisFace.getPosition().y,thisFace.getPosition().x+thisFace.getWidth(),thisFace.getPosition().y+thisFace.getHeight());
		

           
           PointF foreheadPoint = new PointF(x1+((x2-x1)/2),(float)(y1+((y2-y1)/1.5)));
           PointF hairPoint = new PointF(foreheadPoint.x,foreheadPoint.y-(faceRect.height()/4));
//           int pixel = camFace.getPixel((int)foreheadPoint.x+30,(int) foreheadPoint.y);
//         
           f.setCirclePosition(5, foreheadPoint);
           f.setCirclePosition(2, hairPoint);


           faceDrawingView.colorPicker.centerX = (int) (foreheadPoint.x+150);
           faceDrawingView.colorPicker.centerY = (int) (foreheadPoint.y);



//           int redValue = Color.red(pixel);
//           int blueValue = Color.blue(pixel);
//           int greenValue = Color.green(pixel);
//           double luminance =(double) 0.2126*redValue + 0.7152*greenValue + 0.0722*blueValue;
//            Log.d("Luminance: "+luminance);
//         
            //Drawing a point on each face features LandMarks
            for(Landmark landmark : thisFace.getLandmarks()) {
                switch (landmark.getType()){
                    case Landmark.RIGHT_EYE:
                    	
                    	int x = (int) landmark.getPosition().x;
                    	int y = (int) landmark.getPosition().y;
                    	
//                    	LayoutParams params = (LayoutParams) imgLE.getLayoutParams();
//                    	
//                    	params.setMargins(x,y, 0, 0);
//                    	faceDrawingView.setCirclePosition(0, new PointF(landmark.getPosition().x,landmark.getPosition().y-options_linearLayout.getLayoutParams().height/2));
                    	f.setCirclePosition(0, new PointF(landmark.getPosition().x,landmark.getPosition().y));
//                    	imgLE.setLayoutParams(params);
                    
                        break;
                    case Landmark.LEFT_EYE:
                    	int xRE = (int) landmark.getPosition().x;
                    	int yRE = (int) landmark.getPosition().y;
                    	
                    	f.setCirclePosition(1, new PointF(landmark.getPosition().x,landmark.getPosition().y));

//                    	imgRE.setLayoutParams(paramsRE);
                        break;
                       case Landmark.RIGHT_MOUTH:
                    	int xRM = (int) landmark.getPosition().x;
                    	int yRM = (int) landmark.getPosition().y;
                    	
//                    	f.setCirclePosition(2, new PointF(landmark.getPosition().x,landmark.getPosition().y));


                        break;

                       case Landmark.LEFT_MOUTH:
                       	int xLM = (int) landmark.getPosition().x;
                       	int yLM = (int) landmark.getPosition().y;
                       	
//                       	f.setCirclePosition(3, new PointF(landmark.getPosition().x,landmark.getPosition().y));
//                       	
                           break;
                       case Landmark.BOTTOM_MOUTH:
                       	int xBM = (int) landmark.getPosition().x;
                       	int yBM = (int) landmark.getPosition().y;
                       	
                       	f.setCirclePosition(4, new PointF(landmark.getPosition().x,landmark.getPosition().y));
                       	
//                       	imgBM.setLayoutParams(paramsBM);
                           break;

                    case Landmark.NOSE_BASE:
						android.util.Log.d(TAG, "detectFace: Nose x="+landmark.getPosition().x+"y="+landmark.getPosition().y);
						// 	   tempCanvas.drawCircle(landmark.getPosition().x, landmark.getPosition().y, 4, landmarksPaint);
                        break;
                    case Landmark.LEFT_CHEEK:
                    //	   tempCanvas.drawCircle(landmark.getPosition().x, landmark.getPosition().y, 4, landmarksPaint); 
                        break;
                    case Landmark.RIGHT_CHEEK:
                    //	   tempCanvas.drawCircle(landmark.getPosition().x, landmark.getPosition().y, 4, landmarksPaint); 
                        break;
                    case Landmark.LEFT_EAR:
                    //	   tempCanvas.drawCircle(landmark.getPosition().x, landmark.getPosition().y, 4, landmarksPaint); 
                        break;
                    case Landmark.LEFT_EAR_TIP:
                   // 	   tempCanvas.drawCircle(landmark.getPosition().x, landmark.getPosition().y, 4, landmarksPaint); 
                        break;
                    case Landmark.RIGHT_EAR:
                    //	   tempCanvas.drawCircle(landmark.getPosition().x, landmark.getPosition().y, 4, landmarksPaint); 
                        break;
                    case Landmark.RIGHT_EAR_TIP:
                    //	   tempCanvas.drawCircle(landmark.getPosition().x, landmark.getPosition().y, 4, landmarksPaint); 
                        break;
                        
                }
            }



            int glassesX = f.mCircles.get(0).centerX + ((f.mCircles.get(1).centerX - f.mCircles.get(0).centerX)/2);
            int glassesY = f.mCircles.get(0).centerY;

            f.setCirclePosition(3, new PointF(glassesX,glassesY));

            faceDrawingView.AddFace(f);

			// release face Detector...
			faceDetector.release();

		}
        

		return 1;
    }
	public void ShowHideOptionsLayout(int option)//option 1:show 2:hide
	{

		android.util.Log.d(TAG, "ShowHideOptionsLayout: "+option);
		options_linearLayout1.setVisibility(View.GONE);
		options_linearLayout2.setVisibility(View.GONE);
		options_linearLayout3.setVisibility(View.GONE);
		options_linearLayout4.setVisibility(View.GONE);
		options_linearLayout5.setVisibility(View.GONE);
		options_linearLayout6.setVisibility(View.GONE);
		options_relativeLayout7.setVisibility(View.GONE);
		if(option == 1)//first ok button
		{
			isMainOptionsVisible = false;
			options_linearLayout1.setVisibility(View.VISIBLE);	
		}
		else if(option == 2)//main layout
		{
			isMainOptionsVisible = true;
			options_linearLayout2.setVisibility(View.VISIBLE);	
		}
		else if(option == 3)//glasses layout
		{
			isMainOptionsVisible = false;
			options_linearLayout3.setVisibility(View.VISIBLE);	
			ShowHideUpperLayout2(true);
		}
		else if(option == 4)//beard layout
		{
			isMainOptionsVisible = false;
			options_linearLayout4.setVisibility(View.VISIBLE);	
			ShowHideUpperLayout2(true);
		}
		else if(option == 5)//hair layout
		{
			isMainOptionsVisible = false;
			options_linearLayout5.setVisibility(View.VISIBLE);	
			ShowHideUpperLayout2(true);
		}
		else if(option == 6)//bald layout
		{
			isMainOptionsVisible = false;
			options_linearLayout6.setVisibility(View.VISIBLE);	
			ShowHideUpperLayout2(true);
		}
		else if(option == 7)//adjust saturation
		{
			isMainOptionsVisible = false;
			options_relativeLayout7.setVisibility(View.VISIBLE);	
			ShowHideUpperLayout2(false);
		}
	}
	public void ShowHideUpperLayout2(boolean doesShow)//doesShow 0:show 1:hide
	{
		android.util.Log.d(TAG, "ShowHideUpperLayout2: ");
		if(doesShow)
		{
			android.util.Log.d(TAG, "ShowHideUpperLayout2: ");
			upperBar_relativeLayout1.setVisibility(View.GONE);
			upperBar_relativeLayout2.setVisibility(View.VISIBLE);
		}
		else
		{

			android.util.Log.d(TAG, "ShowHideUpperLayout2: ");
			upperBar_relativeLayout2.setVisibility(View.GONE);
			upperBar_relativeLayout1.setVisibility(View.VISIBLE);
		}
	}
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
	
		MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    	
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		 switch (item.getItemId())
	        {
	        case R.id.menu_delete:
	            // Single menu item is selected do something
	            // Ex: launching new activity/screen or show alert message
	         // show Dialog
	        	
	            new AlertDialog.Builder(AdjustLandMarks.this)
	            .setTitle("Delete entry")
	            .setMessage("Are you sure you want to delete this entry?")
	            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) { 
	                    // continue with delete
	                	 Intent intent = new Intent(AdjustLandMarks.this, Guide_Activity.class);
	     	            startActivity(intent);
	     	            finish();
	     	            
	                }
	             })
	            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) { 
	                    // do nothing
	                	
	                }
	             })
	            .setIcon(android.R.drawable.ic_dialog_alert)
	             .show();
	            
	            return true;
	 
	        case R.id.menu_more:
	            Toast.makeText(AdjustLandMarks.this, "More App is Selected", Toast.LENGTH_SHORT).show();
	            Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
	            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
	            startActivity(intent);
	            finish();
	            
	            return true;
	 

	 
	 
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
		
	
	public void Back(View v)
	{
		mp.start();

		if(isMainOptionsVisible)
		{
			finish();
		}
		else
		{
			ShowHideOptionsLayout(2);
		}
	}
	

	public void SaveImage(View v)
	{
		v.setEnabled(false);
		v.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
		mp.start();


		final ProgressDialog progress = new ProgressDialog(AdjustLandMarks.this);
		progress.setMessage("Saving Image");
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.setIndeterminate(true);
		progress.show();




		drawing_relativeLayout.setDrawingCacheEnabled(true);
		Bitmap bitmap = Bitmap.createBitmap(drawing_relativeLayout.getDrawingCache());
		drawing_relativeLayout.setDrawingCacheEnabled(false);
		// save image to disk...
		Uri imageUri = SaveBitmapToDisk(bitmap);

		Globals.newBitmap = Bitmap.createBitmap(bitmap);
		final Intent i = new Intent(this,AfterActivity.class);
		Log.d("ImagePath:" + imageUri.getPath());
		i.putExtra("imagepath", imageUri.toString());

		// TODO: for displaying dialog for saving files...
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				//Do something after 100ms

				// start new activity....
				startActivity(i);
				// finish current activity...
				finish();


			}
		}, 2000);


	}
	
	public Uri SaveBitmapToDisk(Bitmap b)
	{
		File file=null;

		Uri contentUri = null;
		try {


			//create pdf in documents directory
			File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
					Environment.DIRECTORY_PICTURES), "FaceFun");
			if (!pdfFolder.exists()) {
				pdfFolder.mkdir();
				android.util.Log.d(TAG, "SaveBitmapToDisk: ");
			}

		OutputStream fOut = null;
			UUID id = UUID.randomUUID();
		 file = new File(pdfFolder, "AfterImage"+id+".jpg"); // the File to save to
			fOut = new FileOutputStream(file);


		b.compress(Bitmap.CompressFormat.PNG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate


			// notify gallery and other apps that new picture is saved...
			contentUri = Uri.fromFile(file);
			Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,contentUri);
			AdjustLandMarks.this.sendBroadcast(mediaScanIntent);

		fOut.flush();
		fOut.close(); // do not forget to close the stream

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contentUri;
	}


	@Override
	protected void onDestroy()
	{
		mp.stop();
		mp.release();

		inAppBilling_onDestroy();
		super.onDestroy();

		if (dialog.isShowing()){
			dialog.dismiss();
		}
		if (beardDialog.isShowing()){
			beardDialog.dismiss();
		}

		if (hairDialog.isShowing()){
			hairDialog.dismiss();
		}

	}
	

	
	String subSku = "buygoggle1";
	
	IabHelper mHelper;
	

	String devPayLoad = "";

  private static final char[] payloadSymbols = new char[36];

  static {
      for (int idx = 0; idx < 10; ++idx)
    	  payloadSymbols[idx] = (char) ('0' + idx);
      for (int idx = 10; idx < 36; ++idx)
    	  payloadSymbols[idx] = (char) ('a' + idx - 10);
  }


//
//inappbilling start
// 
	
	public void BuyItem()
	{
		Toast.makeText(this, "Bought Item", Toast.LENGTH_LONG).show();
	}



	  public void showBuySubscriptionDialog()
		{
			
			final Dialog dialog = new Dialog(this);
	   	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	   	dialog.setContentView(R.layout.buy_subscription_dialog);
			dialog.setTitle("Add Clause");
			
			Button buy_button =  (Button) dialog.findViewById(R.id.buy_button);
			Button later_button = (Button) dialog.findViewById(R.id.later_button);
			buy_button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();

					beardDialog.dismiss();

					if(goggleNumber==1) {
						try {
							buyStarGoggle1();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					if(goggleNumber==2) {
						try {
							buyStarGoggle2();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(goggleNumber==3) {
						try {
							buyStarGoggle3();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(goggleNumber==4) {
						try {
							buyStarGoggle4();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(goggleNumber==5) {
						try {
							buyStarGoggle5();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(goggleNumber==6) {
						try {
							buyStarGoggle6();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(goggleNumber==7) {
						try {
							buyStarGoggle7();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(goggleNumber==8) {
						try {
							buyStarGoggle8();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(goggleNumber==9) {
						try {
							buyStarGoggle9();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(goggleNumber==10) {
						try {
							buyStarGoggle10();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(goggleNumber==11) {
						try {
							buyStarGoggle11();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(goggleNumber==12) {
						try {
							buyStarGoggle12();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(goggleNumber==13) {
						try {
							buyStarGoggle13();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(goggleNumber==14) {
						try {
							buyStarGoggle14();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(goggleNumber==15) {
						try {
							buyStarGoggle15();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					//******************************** for beard numbers *******************************

					if (beardNumber==1){
						try {
							buyBeard1();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					if (beardNumber==2){
						try {
							buyBeard2();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==3){
						try {
							buyBeard4();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==4){
						try {
							buyBeard4();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==5){
						try {
							buyBeard5();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==6){
						try {
							buyBeard6();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==7){
						try {
							buyBeard7();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==8){
						try {
							buyBeard8();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==9){
						try {
							buyBeard9();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==10){
						try {
							buyBeard10();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==11){
						try {
							buyBeard11();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==12){
						try {
							buyBeard12();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==13){
						try {
							buyBeard13();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==14){
						try {
							buyBeard14();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==15){
						try {
							buyBeard15();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==16){
						try {
							buyBeard16();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==17){
						try {
							buyBeard17();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (beardNumber==18){
						try {
							buyBeard18();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}


					//******************************** for Hairs numbers *******************************
					if (hairNumber==1){
						try {
							buyHair1();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
//******************************** for Hairs numbers *******************************
					if (hairNumber==2){
						try {
							buyHair2();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

//******************************** for Hairs numbers *******************************
					if (hairNumber==3){
						try {
							buyHair3();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

//******************************** for Hairs numbers *******************************
					if (hairNumber==4){
						try {
							buyHair4();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

//******************************** for Hairs numbers *******************************
					if (hairNumber==5){
						try {
							buyHair5();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

//******************************** for Hairs numbers *******************************
					if (hairNumber==6){
						try {
							buyHair6();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

//******************************** for Hairs numbers *******************************
					if (hairNumber==7){
						try {
							buyHair7();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

//******************************** for Hairs numbers *******************************
					if (hairNumber==8){
						try {
							buyHair8();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

//******************************** for Hairs numbers *******************************
					if (hairNumber==9){
						try {
							buyHair9();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

//******************************** for Hairs numbers *******************************
					if (hairNumber==10){
						try {
							buyHair10();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

//******************************** for Hairs numbers *******************************
					if (hairNumber==11){
						try {
							buyHair11();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

//******************************** for Hairs numbers *******************************
					if (hairNumber==12){
						try {
							buyHair12();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

//******************************** for Hairs numbers *******************************
					if (hairNumber==13){
						try {
							buyHair13();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

//******************************** for Hairs numbers *******************************
					if (hairNumber==14){
						try {
							buyHair14();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

//******************************** for Hairs numbers *******************************
					if (hairNumber==15){
						try {
							buyHair15();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

//******************************** for Hairs numbers *******************************
					if (hairNumber==16){
						try {
							buyHair16();
							//buySubscription();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}



				}
			});
			later_button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
						dialog.dismiss();
					
				}
			});
			dialog.show();
			
		}



   
   public void inAppBilling_onDestroy()
   {

	   // very important:
	   if (mBroadcastReceiver != null) {
		   unregisterReceiver(mBroadcastReceiver);
	   }
	   if (mHelper != null) try {
		   mHelper.dispose();
		   mHelper.disposeWhenFinished();
	   } catch (IabHelper.IabAsyncInProgressException e) {
		   e.printStackTrace();
	   }
	   mHelper = null;
   }
   
   
   
   void getSubscriptionList() throws Exception
   {

	   android.util.Log.e("testTag", "Call getsubcription list");
   	
	   mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
   	   public void onQueryInventoryFinished(IabResult result,
   	      Inventory inventory) {

           android.util.Log.d(TAG, "Query inventory finished.");

           // Have we been disposed of in the meantime? If so, quit.
           if (mHelper == null) return;

   		   
   		   
   	      if (result.isFailure()) {
   	      }
   	      
   	      else {
   	    	 android.util.Log.d(TAG, "Query inventory was successful.");

			  setPaidGlasses(2);


   	        
   	        if(inventory.hasPurchase(subSku))
   	        {
   	        	Purchase skuPurchase = inventory.getPurchase(subSku);
   	        	try
   	        	{

//   	        		consumeSubscription(skuPurchase);
   	        	}
   	        	catch(Exception e)
   	        	{
   	        		e.printStackTrace();
   	        	}
   	        }
   	        else
   	        {
   	        	showBuySubscriptionDialog();
   	        }
   	       
   	      }
   	   }
   	};



   	mHelper.queryInventoryAsync(mGotInventoryListener);
   	
   }
   






   
   void buySubscription() throws Exception
   {

	    android.util.Log.e("testTag", "Buy Call");
	   RandomString randomString = new RandomString(36);
	   devPayLoad = randomString.nextString();
 
   	mHelper.launchPurchaseFlow(AdjustLandMarks.this, subSku, IAPCODE,   
   			   mPurchaseFinishedListener, devPayLoad);
   }


	// Callback for when a purchase is finished
	IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
		public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
			android.util.Log.d(TAG, "Purchase finished: " + result + ", purchase: " + purchase);

			// if we were disposed of in the meantime, quit.
			if (mHelper == null) return;

			if (result.isFailure()) {
				complain("Error purchasing: " + result);
				setWaitScreen(false);
				return;
			}
			if (!verifyDeveloperPayload(purchase)) {
				complain("Error purchasing. Authenticity verification failed.");
				setWaitScreen(false);
				return;
			}

			android.util.Log.d(TAG, "Purchase successful.");

			//***************Goggle 1*****************
			if (purchase.getSku().equals(SKU_GOGGLES_STAR)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

			//***************Goggle 2*****************
			if (purchase.getSku().equals(SKU_GOGGLES_HEART)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Heart Goggle. Starting Goggle consumption.");
				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}
			//***************Goggle 3*****************
			if (purchase.getSku().equals(SKU_GOGGLES_SQUAR_DOT)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Squar Dot Goggle. Starting Goggle consumption.");
				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}
			//***************Goggle 4*****************
			if (purchase.getSku().equals(SKU_GOGGLES_CICULAR_RED)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circular Red Goggle. Starting Goggle consumption.");
				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}
			//***************Goggle 5*****************
			if (purchase.getSku().equals(SKU_GOGGLES_YELLOW)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Yellow Goggle. Starting Goggle consumption.");
				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}
			//***************Goggle 6*****************
			if (purchase.getSku().equals(SKU_GOGGLES_BLACK_TRANSPARENT)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Black Transperent Goggle. Starting Goggle consumption.");
				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}
			//***************Goggle 7*****************
			if (purchase.getSku().equals(SKU_GOGGLES_BUTTERFLY_SKY_BLUE)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Butterfly Goggle. Starting Goggle consumption.");
				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}
			//***************Goggle 8*****************
			if (purchase.getSku().equals(SKU_GOGGLES_BIGH_BROWN)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Big Brown Goggle. Starting Goggle consumption.");
				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}
			//***************Goggle 9*****************
			if (purchase.getSku().equals(SKU_GOGGLES_ROUND_PURPLE)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Round Purple Goggle. Starting Goggle consumption.");
				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}
			//***************Goggle 10*****************
			if (purchase.getSku().equals(SKU_GOGGLES_ROUND_RED)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Round Red Goggle. Starting Goggle consumption.");
				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}
			//***************Goggle 11*****************
			if (purchase.getSku().equals(SKU_GOGGLES_LIGHT_PIN_BOY)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Light Pink Goggle. Starting Goggle consumption.");
				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}
			//***************Goggle 12*****************
			if (purchase.getSku().equals(SKU_GOGGLES_BOLD_BORDER_BLACK)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Bold black Goggle. Starting Goggle consumption.");
				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}
			//***************Goggle 13*****************
			if (purchase.getSku().equals(SKU_GOGGLES_BROWN_BLACK_BOY)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Brown Black Goggle. Starting Goggle consumption.");
				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}
			//***************Goggle 14*****************
			if (purchase.getSku().equals(SKU_GOGGLES_RED_BORDER_SMALL)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Red Border Goggle. Starting Goggle consumption.");
				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}
			//***************Goggle 15*****************
			if (purchase.getSku().equals(SKU_GOGGLES_CIRCULAR_SKYBLUE)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");
				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

			//********************************************* Ranglerz Team **********************************

			//******************** Beard 1 *****************************************
			if (purchase.getSku().equals(SKU_BEARD_MUSTACHE_BRWON_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

			//******************** Beard 2 *****************************************
			if (purchase.getSku().equals(SKU_BOLD_LONG_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 3 *****************************************
			if (purchase.getSku().equals(SKU_BROWN_HANSOME_MAN_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 4 *****************************************
			if (purchase.getSku().equals(SKU_CONE_BALCK_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 5 *****************************************
			if (purchase.getSku().equals(SKU_LOCK_BROWN_BOLD_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 6 *****************************************
			if (purchase.getSku().equals(SKU_SPREAD_BROWN_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 7 *****************************************
			if (purchase.getSku().equals(SKU_BOLD_BROWN_MUSTACHE_THIN_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 8 *****************************************
			if (purchase.getSku().equals(SKU_RED_BROWN_HANDSOM_MAN_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 9 *****************************************
			if (purchase.getSku().equals(SKU_THIN_MUSTACHE_BOLD_BLACK_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 10 *****************************************
			if (purchase.getSku().equals(SKU_THIN_MUSTACHE_LONG_BROWN_BREAD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 11 *****************************************
			if (purchase.getSku().equals(SKU_THIN_MUSTACHE_BOLD_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 12 *****************************************
			if (purchase.getSku().equals(SKU_LONG_BLACK_OLDY_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 13 *****************************************
			if (purchase.getSku().equals(SKU_CROWN_WHITE_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 14 *****************************************
			if (purchase.getSku().equals(SKU_LONG_MUSTACHE)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 15 *****************************************
			if (purchase.getSku().equals(SKU_BOARD_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 16 *****************************************
			if (purchase.getSku().equals(SKU_HALF_EGG_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 17 *****************************************
			if (purchase.getSku().equals(SKU_CONE_WITHE_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

//******************** Beard 18 *****************************************
			if (purchase.getSku().equals(SKU_THIN_MUSTACHE_DOT_BEARD)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

			//*************************************** HEAIRS SKU *********************************
// ******************** Hair 1 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_1)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

// ******************** Hair 2 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_2)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}
// ******************** Hair 3 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_3)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

// ******************** Hair 4 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_4)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

// ******************** Hair 5 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_5)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

// ******************** Hair 6 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_6)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

// ******************** Hair 7 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_7)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

// ******************** Hair 8 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_8)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

// ******************** Hair 9 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_9)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

// ******************** Hair 10 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_10)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

// ******************** Hair 11 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_11)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

// ******************** Hair 12 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_12)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

// ******************** Hair 13 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_13)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

// ******************** Hair 14 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_14)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

// ******************** Hair 15 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_15)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}

// ******************** Hair 16 *****************************************
			if (purchase.getSku().equals(BYUING_HAIR_16)) {
				// bought 1/4 tank of gas. So consume it.
				android.util.Log.d(TAG, "Purchase is Circualr Skyblue Goggle. Starting Goggle consumption.");

				try {
					mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error consuming Goggle. Another async operation in progress.");
					setWaitScreen(false);
					return;
				}
			}


		}
	};



	@Override
	public void receivedBroadcast() {
// Received a broadcast notification that the inventory of items has changed
		android.util.Log.d(TAG, "Received broadcast notification. Querying inventory.");
		try {
			mHelper.queryInventoryAsync(mGotInventoryListener);
		} catch (IabHelper.IabAsyncInProgressException e) {
			android.util.Log.e(TAG, "Error querying inventory. Another async operation in progress.");
		}
	}




   public class RandomString {

       /*
        * static { for (int idx = 0; idx < 10; ++idx) symbols[idx] = (char)
        * ('0' + idx); for (int idx = 10; idx < 36; ++idx) symbols[idx] =
        * (char) ('a' + idx - 10); }
        */
	   

       private final Random random = new Random();

       private final char[] buf;

       public RandomString(int length) {
           if (length < 1)
               throw new IllegalArgumentException("length < 1: " + length);
           buf = new char[length];
       }

       public String nextString() {
           for (int idx = 0; idx < buf.length; ++idx)
               buf[idx] = payloadSymbols[random.nextInt(payloadSymbols.length)];
           return new String(buf);
       }

   }

   public final class SessionIdentifierGenerator {

       private SecureRandom random = new SecureRandom();

       public String nextSessionId() {
           return new BigInteger(130, random).toString(32);
       }

   }

	public void forSellGlassesDialog(){


		//final Dialog dialog = new Dialog(this);
		///initforDialog();
		forSelGlass1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				goggleNumber = 1;
				//setPaidGlasses(1);
				showBuySubscriptionDialog();
				dialog.dismiss();



			}
		});

		forSelGlass2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				goggleNumber = 2;

				showBuySubscriptionDialog();
				dialog.dismiss();


			}
		});
		forSelGlass3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				goggleNumber = 3;

				showBuySubscriptionDialog();
				dialog.dismiss();

			}
		});
		forSelGlass4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				goggleNumber = 4;
				showBuySubscriptionDialog();
				dialog.dismiss();

			}
		});
		forSelGlass5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				goggleNumber = 5;

				showBuySubscriptionDialog();
				dialog.dismiss();


			}
		});

		forSelGlass6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				goggleNumber = 6;
				showBuySubscriptionDialog();
				dialog.dismiss();

			}
		});
		forSelGlass7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				goggleNumber = 7;
				showBuySubscriptionDialog();
				dialog.dismiss();

			}
		});
		forSelGlass8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				goggleNumber = 8;
				showBuySubscriptionDialog();
				dialog.dismiss();

			}
		});
		forSelGlass9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				goggleNumber = 9;
				showBuySubscriptionDialog();
				dialog.dismiss();

			}
		});
		forSelGlass10.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				goggleNumber = 10;
				showBuySubscriptionDialog();
				dialog.dismiss();

			}
		});
		forSelGlass11.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				goggleNumber = 11;
				showBuySubscriptionDialog();
				dialog.dismiss();

			}
		});
		forSelGlass12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				goggleNumber = 12;
				showBuySubscriptionDialog();
				dialog.dismiss();

			}
		});
		forSelGlass13.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				goggleNumber = 13;
				showBuySubscriptionDialog();
				dialog.dismiss();

			}
		});
		forSelGlass14.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				goggleNumber = 14;
				showBuySubscriptionDialog();
				dialog.dismiss();

			}
		});
		forSelGlass15.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				goggleNumber = 15;
				showBuySubscriptionDialog();
				dialog.dismiss();

			}
		});


		dialog.setCancelable(true);
		dialog.show();

	}

	//for Sale Dialog for hair
	public void forSaleHairDialog(){

		forSaleHair1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 1;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});
		forSaleHair2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 2;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});
		forSaleHair3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 3;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});
		forSaleHair4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 4;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});
		forSaleHair5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 5;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});
		forSaleHair6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 6;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});
		forSaleHair7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 7;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});
		forSaleHair8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 8;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});
		forSaleHair9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 9;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});
		forSaleHair10.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 10;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});
		forSaleHair11.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 11;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});
		forSaleHair12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 12;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});
		forSaleHair13.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 13;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});
		forSaleHair14.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 14;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});
		forSaleHair15.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 15;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});
		forSaleHair16.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hairNumber = 16;
				showBuySubscriptionDialog();
				hairDialog.dismiss();
			}
		});

		hairDialog.setCancelable(true);
		hairDialog.show();
	}

	public void forSaleBeardDilaog(){

		forSaleBear1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 1;
				//setPaidBeard(1);
				showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 2;
				showBuySubscriptionDialog();
				//setPaidBeard(2);
				//showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 3;
				showBuySubscriptionDialog();
				//showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 4;
				showBuySubscriptionDialog();
				//showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 5;
				showBuySubscriptionDialog();
				//showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 6;
				showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 7;
				showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 8;
				showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 9;
				showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear10.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 10;
				showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear11.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 11;
				showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 12;
				showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear13.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 13;
				showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear14.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 14;
				showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear15.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 15;
				showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear16.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 16;
				showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear17.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 17;
				showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});
		forSaleBear18.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				beardNumber = 18;
				showBuySubscriptionDialog();
				beardDialog.dismiss();
			}
		});


		beardDialog.setCancelable(true);
		beardDialog.show();
	}



	public void initForBeardDilaog(){
		beardDialog.setContentView(R.layout.for_sell_beard_collection_dialog_layout);
		beardDialog.setTitle("Buy Beards");
		//registering for sale beard views
		forSaleBear1 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard1);
		forSaleBear2 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard2);
		forSaleBear3 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard3);
		forSaleBear4 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard4);
		forSaleBear5 = (ImageView)beardDialog.findViewById(R.id.for_sall_beard5);
		forSaleBear6 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard6);
		forSaleBear7 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard7);
		forSaleBear8 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard8);
		forSaleBear9 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard9);
		forSaleBear10 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard10);
		forSaleBear11 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard11);
		forSaleBear12 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard12);
		forSaleBear13 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard13);
		forSaleBear14 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard14);
		forSaleBear15 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard15);
		forSaleBear16 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard16);
		forSaleBear17 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard17);
		forSaleBear18 = (ImageView)beardDialog.findViewById(R.id.for_sell_beard18);

		hidGoggleFromDialogWhenBought();
	}

	public void initforDialog(){
		dialog.setContentView(R.layout.for_sell_glass_collection_dilaog_layout);
		dialog.setTitle("Buy Glasses");
		//registering for sele images Views
		forSelGlass1 = (ImageView)dialog.findViewById(R.id.for_sell_glass1);
		forSelGlass2 = (ImageView)dialog.findViewById(R.id.for_sell_glass2);
		forSelGlass3 = (ImageView)dialog.findViewById(R.id.for_sell_glass3);
		forSelGlass4 = (ImageView)dialog.findViewById(R.id.for_sell_glass4);
		forSelGlass5 = (ImageView)dialog.findViewById(R.id.for_sell_glass5);
		forSelGlass6 = (ImageView)dialog.findViewById(R.id.for_sell_glass6);
		forSelGlass7 = (ImageView)dialog.findViewById(R.id.for_sell_glass7);
		forSelGlass8 = (ImageView)dialog.findViewById(R.id.for_sell_glass8);
		forSelGlass9 = (ImageView)dialog.findViewById(R.id.for_sell_glass9);
		forSelGlass10 = (ImageView)dialog.findViewById(R.id.for_sell_glass10);
		forSelGlass11 = (ImageView)dialog.findViewById(R.id.for_sell_glass11);
		forSelGlass12 = (ImageView)dialog.findViewById(R.id.for_sell_glass12);
		forSelGlass13 = (ImageView)dialog.findViewById(R.id.for_sell_glass13);
		forSelGlass14 = (ImageView)dialog.findViewById(R.id.for_sell_glass14);
		forSelGlass15 = (ImageView)dialog.findViewById(R.id.for_sell_glass15);
		hidGoggleFromDialogWhenBought();
	}

	public void initForHairDialog(){
		hairDialog.setContentView(R.layout.for_sell_hair_collection_dialog_layout);
		hairDialog.setTitle("Buy Hairs");
		//registring for sale hair image view

		forSaleHair1 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_1);
		forSaleHair2 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_2);
		forSaleHair3 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_3);
		forSaleHair4 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_4);
		forSaleHair5 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_5);
		forSaleHair6 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_6);
		forSaleHair7 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_7);
		forSaleHair8 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_8);
		forSaleHair9 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_9);
		forSaleHair10 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_10);
		forSaleHair11 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_11);
		forSaleHair12 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_12);
		forSaleHair13 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_13);
		forSaleHair14 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_14);
		forSaleHair15 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_15);
		forSaleHair16 = (ImageView)hairDialog.findViewById(R.id.for_sale_hair_16);

		hidGoggleFromDialogWhenBought();
	}



	//setting paid Glassess
	private void setPaidGlasses(int option){

		android.util.Log.d(TAG, "setGlasses: ");
		ShowHideUpperLayout2(true);
		currentPosition++;
		if(option == 1)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele1);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 2)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele2);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 3)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele3);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 4)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele4);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 5)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele5);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 6)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele6);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 7)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele7);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 8)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele8);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 9)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele9);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 10)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele10);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 11)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele11);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 12)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele12);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 13)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele13);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 14)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele14);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 15)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele15);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}
		else if(option == 16)
		{
			BitmapWorkerTaskForGlasses b = new BitmapWorkerTaskForGlasses(R.drawable.for_sele16);
			b.execute();
			userPosition.put(currentPosition, Globals.GLASSESPOSITION);
		}



	}//end of setPaidGoggle

	//setting paid Hairs
	public void setPaidHair(int option){
		android.util.Log.d(TAG, "setHairs: ");
		ShowHideUpperLayout2(true);
		currentPosition++;

		if(option == 1) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_1,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		if(option == 2) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_2,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		if(option == 3) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_3,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		if(option == 4) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_4,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		if(option == 5) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_5,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		if(option == 6) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_6,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		if(option == 7) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_7,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		if(option == 8) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_8,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		if(option == 9) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_9,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		if(option == 10) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_10,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		if(option == 11) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_11,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		if(option == 12) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_12,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		if(option == 13) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_13,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		if(option == 14) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_14,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		if(option == 15) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_15,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}
		if(option == 16) {
			BitmapWorkerTaskForHair hair = new BitmapWorkerTaskForHair(R.drawable.for_sale_hair_image_16,Globals.HAIRPOSITION);
			hair.execute();
			userPosition.put(currentPosition, Globals.HAIRPOSITION);
		}

	}

	//setting paid Beard
	private void setPaidBeard(int option){

		android.util.Log.d(TAG, "setBeard: ");
		ShowHideUpperLayout2(true);
		currentPosition++;
		if(option == 1)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_1);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);

		}
		else if(option == 2)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_2);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}
		else if(option == 3)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_3);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}
		else if(option == 4)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_4);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}
		else if(option == 5)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_5);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}

		else if(option == 6)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_6);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}

		else if(option == 7)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_7);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}

		else if(option == 8)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_8);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}

		else if(option == 9)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_9);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}

		else if(option == 10)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_10);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}

		else if(option == 11)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_11);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}

		else if(option == 12)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_12);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}

		else if(option == 13)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_13);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}

		else if(option == 14)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_14);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}

		else if(option == 15)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_15);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}

		else if(option == 16)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_16);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}

		else if(option == 17)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_17);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}

		else if(option == 18)
		{
			BitmapWorkerTaskForBeard beard = new BitmapWorkerTaskForBeard(R.drawable.sale_image_18);
			beard.execute();
			userPosition.put(currentPosition, Globals.BEARDPOSITION);
		}


	}//end of setting paid beards





public class BuyGlasses extends AsyncTask<String, String, String>{

	Context context;


	public BuyGlasses(Context context){
		this.context = context;
	}

	@Override
	protected String doInBackground(String... params) {

		if (Looper.myLooper()!=null){
			pDilaog.dismiss();
		}
		if (Looper.myLooper()==null) {
			Looper.prepare();
		}

		initforDialog();

		return null;

	}

	@Override
	protected void onPostExecute(String s) {
		super.onPostExecute(s);
		pDilaog.dismiss();


		forSellGlassesDialog();

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();


		if(!pDilaog.isShowing()) {
			pDilaog.show();

		}else {
			pDilaog.dismiss();
		}

	}


}//end of buyGlass class


	//buy hairs Class
	public class BuyHairs extends AsyncTask<String, String, String>{

		Context context;


		public BuyHairs(Context context){
			this.context = context;
		}

		@Override
		protected String doInBackground(String... params) {

			if (Looper.myLooper()!=null){
				pHaireDialog.dismiss();
			}
			if (Looper.myLooper()==null) {
				Looper.prepare();
			}

			initForHairDialog();

			return null;

		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			pHaireDialog.dismiss();

			forSaleHairDialog();

		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();


			if(!pHaireDialog.isShowing()) {
				pHaireDialog.show();

			}else {
				pHaireDialog.dismiss();
			}

		}


	}//end of buyHairs class




	public class BuyBeard extends AsyncTask<String, String, String>{

		Context context;



		public BuyBeard(Context context){
			this.context = context;
		}

		@Override
		protected String doInBackground(String... params) {

			if (Looper.myLooper()!=null){
				pBeardDialog.dismiss();
			}
			if (Looper.myLooper()==null) {
				Looper.prepare();
			}

			initForBeardDilaog();

			return null;

		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			pBeardDialog.dismiss();


			forSaleBeardDilaog();

		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();


			if(!pBeardDialog.isShowing()) {
				pBeardDialog.show();

			}else {
				pBeardDialog.dismiss();
			}

		}


	}//end of buyBeardDilaog class






	public void keyInitialization(){
		String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAke1zLKYw7p5G0IZyvGtsNnVb5aPQ2PBWsK5JCMoyyyQWAwEnEciD6L3+eXya7dZvX7FlAPMxSafsrXYue2TwXALVw6nTrOFVQYLZCfiaSE0P362wnrXkO2B9F5Kg2xjcT6HIy/BDwS0G+wo67tvSF+jV4i/plrqDlMgZrtDDRobpirkmO8lvn1a0MurWJRZEZriH+qQf07KPo/UJS5eHCJjru4qRW1zaIsv7l78pMtALQ5gtfpg6766fKZwIGs7HbJfMJbQV7q5H43xcP4EWvZO5xWuodRGktk4TKLHGU/tpaz5e1zY8iR3l+R0EwJqQ+iHA6z+Su/wZEkErSpTLHwIDAQAB";

		// Some sanity checks to see if the developer (that's you!) really followed the
		// instructions to run this sample (don't put these checks on your app!)
		if (base64EncodedPublicKey.contains("CONSTRUCT_YOUR")) {
			throw new RuntimeException("Please put your app's public key in MainActivity.java. See README.");
		}
		if (getPackageName().startsWith("com.example")) {
			throw new RuntimeException("Please change the sample's package name! See README.");
		}

		// Create the helper, passing it our context and the public key to verify signatures with
		android.util.Log.d(TAG, "Creating IAB helper.");
		mHelper = new IabHelper(this, base64EncodedPublicKey);

		// enable debug logging (for a production application, you should set this to false).
		mHelper.enableDebugLogging(true);



	}//end of keyInitialization

	public void startUpLab(){
		// Start setup. This is asynchronous and the specified listener
		// will be called once setup completes.
		android.util.Log.d(TAG, "Starting setup.");

		mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
			public void onIabSetupFinished(IabResult result) {
				android.util.Log.d(TAG, "Setup finished.");

				if (!result.isSuccess()) {
					// Oh noes, there was a problem.
					complain("Problem setting up in-app billing: " + result);
					return;
				}

				// Have we been disposed of in the meantime? If so, quit.
				if (mHelper == null) return;

				mBroadcastReceiver = new IabBroadcastReceiver(AdjustLandMarks.this);
				IntentFilter broadcastFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
				registerReceiver(mBroadcastReceiver, broadcastFilter);

				// IAB is fully set up. Now, let's get an inventory of stuff we own.
				android.util.Log.d(TAG, "Setup successful. Querying inventory.");
				try {
					mHelper.queryInventoryAsync(mGotInventoryListener);
				} catch (IabHelper.IabAsyncInProgressException e) {
					complain("Error querying inventory. Another async operation in progress.");
				}
			}
		});


	}//end of startup lab


		// Listener that's called when we finish querying the items and subscriptions we own
		IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
			public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
				android.util.Log.d(TAG, "Query inventory finished.");

				// Have we been disposed of in the meantime? If so, quit.
				if (mHelper == null) return;

				// Is it a failure?
				if (result.isFailure()) {
					complain("Failed to query inventory: " + result);
					return;
				}

				android.util.Log.d(TAG, "Query inventory was successful.");

				// Check for gas delivery -- if we own gas, we should fill up the tank immediately

				//************for goggle 1 perchase*********************
				if (goggleNumber==1) {
					Purchase gasPurchase = inventory.getPurchase(SKU_GOGGLES_STAR);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_GOGGLES_STAR), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}
				}//end for goggle 1 perchase

				//************for goggle 1 perchase*********************
				if (goggleNumber==2) {
					Purchase gasPurchase = inventory.getPurchase(SKU_GOGGLES_HEART);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_GOGGLES_HEART), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}
				}//end for goggle 1 perchase

				//************for goggle 1 perchase*********************
				if (goggleNumber==3) {
					Purchase gasPurchase = inventory.getPurchase(SKU_GOGGLES_SQUAR_DOT);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_GOGGLES_SQUAR_DOT), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}
				}//end for goggle 1 perchase

				//************for goggle 1 perchase*********************
				if (goggleNumber==4) {
					Purchase gasPurchase = inventory.getPurchase(SKU_GOGGLES_CICULAR_RED);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_GOGGLES_CICULAR_RED), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}
				}//end for goggle 1 perchase

				//************for goggle 1 perchase*********************
				if (goggleNumber==5) {
					Purchase gasPurchase = inventory.getPurchase(SKU_GOGGLES_YELLOW);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_GOGGLES_YELLOW), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}
				}//end for goggle 1 perchase

				//************for goggle 1 perchase*********************
				if (goggleNumber==6) {
					Purchase gasPurchase = inventory.getPurchase(SKU_GOGGLES_BLACK_TRANSPARENT);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_GOGGLES_BLACK_TRANSPARENT), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}
				}//end for goggle 1 perchase

				//************for goggle 1 perchase*********************
				if (goggleNumber==7) {
					Purchase gasPurchase = inventory.getPurchase(SKU_GOGGLES_BUTTERFLY_SKY_BLUE);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_GOGGLES_BUTTERFLY_SKY_BLUE), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}
				}//end for goggle 1 perchase

				//************for goggle 1 perchase*********************
				if (goggleNumber==8) {
					Purchase gasPurchase = inventory.getPurchase(SKU_GOGGLES_BIGH_BROWN);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_GOGGLES_BIGH_BROWN), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}
				}//end for goggle 1 perchase

				//************for goggle 1 perchase*********************
				if (goggleNumber==9) {
					Purchase gasPurchase = inventory.getPurchase(SKU_GOGGLES_ROUND_PURPLE);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_GOGGLES_ROUND_PURPLE), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}
				}//end for goggle 1 perchase

				//************for goggle 1 perchase*********************
				if (goggleNumber==10) {
					Purchase gasPurchase = inventory.getPurchase(SKU_GOGGLES_ROUND_RED);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_GOGGLES_ROUND_RED), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}
				}//end for goggle 1 perchase

				//************for goggle 1 perchase*********************
				if (goggleNumber==11) {
					Purchase gasPurchase = inventory.getPurchase(SKU_GOGGLES_LIGHT_PIN_BOY);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_GOGGLES_LIGHT_PIN_BOY), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}
				}//end for goggle 1 perchase

				//************for goggle 1 perchase*********************
				if (goggleNumber==12) {
					Purchase gasPurchase = inventory.getPurchase(SKU_GOGGLES_BOLD_BORDER_BLACK);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_GOGGLES_BOLD_BORDER_BLACK), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}
				}//end for goggle 1 perchase

				//************for goggle 1 perchase*********************
				if (goggleNumber==13) {
					Purchase gasPurchase = inventory.getPurchase(SKU_GOGGLES_BROWN_BLACK_BOY);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_GOGGLES_BROWN_BLACK_BOY), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}
				}//end for goggle 1 perchase

				//************for goggle 1 perchase*********************
				if (goggleNumber==14) {
					Purchase gasPurchase = inventory.getPurchase(SKU_GOGGLES_RED_BORDER_SMALL);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_GOGGLES_RED_BORDER_SMALL), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}
				}//end for goggle 1 perchase

				//************for goggle 1 perchase*********************
				if (goggleNumber==15) {
					Purchase gasPurchase = inventory.getPurchase(SKU_GOGGLES_CIRCULAR_SKYBLUE);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_GOGGLES_CIRCULAR_SKYBLUE), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}
				}//end for goggle 1 perchase

				//******************************************* Purchase for Beards *************************************************
				//**************************** Beard 1 Purchase **************************
				if (beardNumber==1){
					Purchase gasPurchase = inventory.getPurchase(SKU_BEARD_MUSTACHE_BRWON_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_BEARD_MUSTACHE_BRWON_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 1 purchase


				if (beardNumber==2){
					Purchase gasPurchase = inventory.getPurchase(SKU_BOLD_LONG_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_BOLD_LONG_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 2 purchase

				if (beardNumber==3){
					Purchase gasPurchase = inventory.getPurchase(SKU_BROWN_HANSOME_MAN_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_BROWN_HANSOME_MAN_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 3 purchase

				if (beardNumber==4){
					Purchase gasPurchase = inventory.getPurchase(SKU_CONE_BALCK_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_CONE_BALCK_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 4 purchase

				if (beardNumber==5){
					Purchase gasPurchase = inventory.getPurchase(SKU_LOCK_BROWN_BOLD_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_LOCK_BROWN_BOLD_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 5 purchase

				if (beardNumber==6){
					Purchase gasPurchase = inventory.getPurchase(SKU_SPREAD_BROWN_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_SPREAD_BROWN_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 6 purchase

				if (beardNumber==7){
					Purchase gasPurchase = inventory.getPurchase(SKU_BOLD_BROWN_MUSTACHE_THIN_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_BOLD_BROWN_MUSTACHE_THIN_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 7 purchase

				if (beardNumber==8){
					Purchase gasPurchase = inventory.getPurchase(SKU_RED_BROWN_HANDSOM_MAN_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_RED_BROWN_HANDSOM_MAN_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 8 purchase

				if (beardNumber==9){
					Purchase gasPurchase = inventory.getPurchase(SKU_THIN_MUSTACHE_BOLD_BLACK_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_THIN_MUSTACHE_BOLD_BLACK_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 9 purchase

				if (beardNumber==10){
					Purchase gasPurchase = inventory.getPurchase(SKU_THIN_MUSTACHE_LONG_BROWN_BREAD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_THIN_MUSTACHE_LONG_BROWN_BREAD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 10 purchase

				if (beardNumber==11){
					Purchase gasPurchase = inventory.getPurchase(SKU_THIN_MUSTACHE_BOLD_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_THIN_MUSTACHE_BOLD_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 11 purchase

				if (beardNumber==12){
					Purchase gasPurchase = inventory.getPurchase(SKU_LONG_BLACK_OLDY_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_LONG_BLACK_OLDY_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 12 purchase

				if (beardNumber==13){
					Purchase gasPurchase = inventory.getPurchase(SKU_CROWN_WHITE_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_CROWN_WHITE_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 13 purchase

				if (beardNumber==14){
					Purchase gasPurchase = inventory.getPurchase(SKU_LONG_MUSTACHE);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_LONG_MUSTACHE), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 14 purchase

				if (beardNumber==15){
					Purchase gasPurchase = inventory.getPurchase(SKU_BOARD_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_BOARD_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 15 purchase

				if (beardNumber==16){
					Purchase gasPurchase = inventory.getPurchase(SKU_HALF_EGG_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_HALF_EGG_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 16 purchase

				if (beardNumber==17){
					Purchase gasPurchase = inventory.getPurchase(SKU_CONE_WITHE_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_CONE_WITHE_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 17 purchase

				if (beardNumber==18){
					Purchase gasPurchase = inventory.getPurchase(SKU_THIN_MUSTACHE_DOT_BEARD);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(SKU_THIN_MUSTACHE_DOT_BEARD), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for beard 18 purchase


				if (hairNumber==1){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_1);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_1), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 1 purchase

				if (hairNumber==2){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_2);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_2), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 2 purchase

				if (hairNumber==3){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_3);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_3), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 3 purchase

				if (hairNumber==4){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_4);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_4), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 4 purchase

				if (hairNumber==5){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_5);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_5), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 5 purchase

				if (hairNumber==6){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_6);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_6), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 6 purchase

				if (hairNumber==7){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_7);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_7), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 7 purchase

				if (hairNumber==8){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_8);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_8), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 8 purchase

				if (hairNumber==9){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_9);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_9), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 9 purchase

				if (hairNumber==10){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_10);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_10), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 10 purchase

				if (hairNumber==11){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_11);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_11), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 11 purchase

				if (hairNumber==12){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_12);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_12), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 12 purchase

				if (hairNumber==13){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_13);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_13), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 13 purchase

				if (hairNumber==14){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_14);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_14), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 14 purchase

				if (hairNumber==15){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_15);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_15), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 15 purchase

				if (hairNumber==16){
					Purchase gasPurchase = inventory.getPurchase(BYUING_HAIR_16);
					android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

					if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
						android.util.Log.d(TAG, "We have gas. Consuming it.");
						try {
							mHelper.consumeAsync(inventory.getPurchase(BYUING_HAIR_16), mConsumeFinishedListener);
						} catch (IabHelper.IabAsyncInProgressException e) {
							complain("Error consuming gas. Another async operation in progress.");
						}
						return;
					}

				}//end for haris 16 purchase







				//	updateUi();

				setWaitScreen(false);
				android.util.Log.d(TAG, "Initial inventory query finished; enabling main UI.");
			}
		};

	// Called when consumption is complete
	IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
		public void onConsumeFinished(Purchase purchase, IabResult result) {
			android.util.Log.d(TAG, "Consumption finished. Purchase: " + purchase + ", result: " + result);

			// if we were disposed of in the meantime, quit.
			if (mHelper == null) return;

			// We know this is the "gas" sku because it's the only one we consume,
			// so we don't check which sku was consumed. If you have more than one
			// sku, you probably should check...
			if (result.isSuccess()) {
				// successfully consumed, so we apply the effects of the item in our
				// game world's logic, which in our case means filling the gas tank a bit
				android.util.Log.d(TAG, "Consumption successful. Provisioning.");
				//mTank = mTank == TANK_MAX ? TANK_MAX : mTank + 1;
				//saveData();
				alert("You have successfully buy glass");

				onSuccessGoggleBought();

				if (dialog.isShowing()){
					dialog.dismiss();
					dialog = null;

				}

				if (beardDialog.isShowing()){
					beardDialog.dismiss();
					beardDialog = null;
				}



				//alert("You filled 1/4 tank. Your tank is now " + String.valueOf(mTank) + "/4 full!");
			}
			else {
				complain("Error while consuming: " + result);
			}
			//updateUi();

			setWaitScreen(false);
			android.util.Log.d(TAG, "End consumption flow.");
		}
	};


	void complain(String message) {
		android.util.Log.e(TAG, "**** TrivialDrive Error: " + message);
		alert("Error: " + message);
	}


	void alert(String message) {
		AlertDialog.Builder bld = new AlertDialog.Builder(this);
		bld.setMessage(message);
		bld.setNeutralButton("OK", null);
		android.util.Log.d(TAG, "Showing alert dialog: " + message);
		bld.create().show();
	}

	public void buyStarGoggle1(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}


		// launch the gas purchase UI flow.
		// We will be notified of completion via mPurchaseFinishedListener
		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;

		try {
			mHelper.launchPurchaseFlow(this, SKU_GOGGLES_STAR, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buyStarGoggle1


	public void buyStarGoggle2(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		// launch the gas purchase UI flow.
		// We will be notified of completion via mPurchaseFinishedListener
		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;

		try {
			mHelper.launchPurchaseFlow(this, SKU_GOGGLES_HEART, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buyStarGoggle2

	public void buyStarGoggle3(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		/*if (mTank >= TANK_MAX) {
			complain("Your tank is full. Drive around a bit!");
			return;
		}
*/

		// launch the gas purchase UI flow.
		// We will be notified of completion via mPurchaseFinishedListener
		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;

		try {
			mHelper.launchPurchaseFlow(this, SKU_GOGGLES_SQUAR_DOT, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buyStarGoggle3

	public void buyStarGoggle4(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		/*if (mTank >= TANK_MAX) {
			complain("Your tank is full. Drive around a bit!");
			return;
		}
*/

		// launch the gas purchase UI flow.
		// We will be notified of completion via mPurchaseFinishedListener
		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;

		try {
			mHelper.launchPurchaseFlow(this, SKU_GOGGLES_CICULAR_RED, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buyStarGoggle4

	public void buyStarGoggle5(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		/*if (mTank >= TANK_MAX) {
			complain("Your tank is full. Drive around a bit!");
			return;
		}
*/

		// launch the gas purchase UI flow.
		// We will be notified of completion via mPurchaseFinishedListener
		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;

		try {
			mHelper.launchPurchaseFlow(this, SKU_GOGGLES_YELLOW, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buyStarGoggle5

	public void buyStarGoggle6(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		/*if (mTank >= TANK_MAX) {
			complain("Your tank is full. Drive around a bit!");
			return;
		}
*/

		// launch the gas purchase UI flow.
		// We will be notified of completion via mPurchaseFinishedListener
		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;

		try {
			mHelper.launchPurchaseFlow(this, SKU_GOGGLES_BLACK_TRANSPARENT, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buyStarGoggle6

	public void buyStarGoggle7(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		/*if (mTank >= TANK_MAX) {
			complain("Your tank is full. Drive around a bit!");
			return;
		}
*/

		// launch the gas purchase UI flow.
		// We will be notified of completion via mPurchaseFinishedListener
		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;

		try {
			mHelper.launchPurchaseFlow(this, SKU_GOGGLES_BUTTERFLY_SKY_BLUE, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buyStarGoggle7

	public void buyStarGoggle8(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		/*if (mTank >= TANK_MAX) {
			complain("Your tank is full. Drive around a bit!");
			return;
		}
*/

		// launch the gas purchase UI flow.
		// We will be notified of completion via mPurchaseFinishedListener
		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;

		try {
			mHelper.launchPurchaseFlow(this, SKU_GOGGLES_BIGH_BROWN, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buyStarGoggle8

	public void buyStarGoggle9(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		/*if (mTank >= TANK_MAX) {
			complain("Your tank is full. Drive around a bit!");
			return;
		}
*/

		// launch the gas purchase UI flow.
		// We will be notified of completion via mPurchaseFinishedListener
		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;

		try {
			mHelper.launchPurchaseFlow(this, SKU_GOGGLES_ROUND_PURPLE, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buyStarGoggle9

	public void buyStarGoggle10(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		/*if (mTank >= TANK_MAX) {
			complain("Your tank is full. Drive around a bit!");
			return;
		}
*/

		// launch the gas purchase UI flow.
		// We will be notified of completion via mPurchaseFinishedListener
		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;

		try {
			mHelper.launchPurchaseFlow(this, SKU_GOGGLES_ROUND_RED, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buyStarGoggle10

	public void buyStarGoggle11(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		/*if (mTank >= TANK_MAX) {
			complain("Your tank is full. Drive around a bit!");
			return;
		}
*/

		// launch the gas purchase UI flow.
		// We will be notified of completion via mPurchaseFinishedListener
		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;

		try {
			mHelper.launchPurchaseFlow(this, SKU_GOGGLES_LIGHT_PIN_BOY, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buyStarGoggle11

	public void buyStarGoggle12(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		/*if (mTank >= TANK_MAX) {
			complain("Your tank is full. Drive around a bit!");
			return;
		}
*/

		// launch the gas purchase UI flow.
		// We will be notified of completion via mPurchaseFinishedListener
		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;

		try {
			mHelper.launchPurchaseFlow(this, SKU_GOGGLES_BOLD_BORDER_BLACK, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buyStarGoggle12

	public void buyStarGoggle13(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		/*if (mTank >= TANK_MAX) {
			complain("Your tank is full. Drive around a bit!");
			return;
		}
*/

		// launch the gas purchase UI flow.
		// We will be notified of completion via mPurchaseFinishedListener
		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;

		try {
			mHelper.launchPurchaseFlow(this, SKU_GOGGLES_BROWN_BLACK_BOY, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buyStarGoggle13

	public void buyStarGoggle14(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		/*if (mTank >= TANK_MAX) {
			complain("Your tank is full. Drive around a bit!");
			return;
		}
*/

		// launch the gas purchase UI flow.
		// We will be notified of completion via mPurchaseFinishedListener
		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;

		try {
			mHelper.launchPurchaseFlow(this, SKU_GOGGLES_RED_BORDER_SMALL, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buyStarGoggle14

	public void buyStarGoggle15(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		/*if (mTank >= TANK_MAX) {
			complain("Your tank is full. Drive around a bit!");
			return;
		}
*/

		// launch the gas purchase UI flow.
		// We will be notified of completion via mPurchaseFinishedListener
		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;

		try {
			mHelper.launchPurchaseFlow(this, SKU_GOGGLES_CIRCULAR_SKYBLUE, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buyStarGoggle15


	//handling buy subscription of beard
	public void buyBeard1(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_BEARD_MUSTACHE_BRWON_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard1

	public void buyBeard2(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_BOLD_LONG_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard2

	public void buyBeard3(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_BROWN_HANSOME_MAN_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard3

	public void buyBeard4(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_CONE_BALCK_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard4

	public void buyBeard5(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_LOCK_BROWN_BOLD_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard5

	public void buyBeard6(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_SPREAD_BROWN_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard6

	public void buyBeard7(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_BOLD_BROWN_MUSTACHE_THIN_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard7

	public void buyBeard8(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_RED_BROWN_HANDSOM_MAN_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard8

	public void buyBeard9(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_THIN_MUSTACHE_BOLD_BLACK_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard9

	public void buyBeard10(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_THIN_MUSTACHE_LONG_BROWN_BREAD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard10

	public void buyBeard11(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_THIN_MUSTACHE_BOLD_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard11

	public void buyBeard12(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_LONG_BLACK_OLDY_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard12

	public void buyBeard13(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_CROWN_WHITE_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard13

	public void buyBeard14(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_LONG_MUSTACHE, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard14

	public void buyBeard15(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_BOARD_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard15

	public void buyBeard16(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_HALF_EGG_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard16

	public void buyBeard17(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_CONE_WITHE_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard17

	public void buyBeard18(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, SKU_THIN_MUSTACHE_DOT_BEARD, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard18



////*********************************** Buy Methods for Hairs *********************************************

	public void buyHair1(){
	if (mSubscribedToInfiniteGas) {
		complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
		return;
	}

	setWaitScreen(true);
	android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
	String payload = "";
	android.util.Log.e("testTag", "Buy Call");
	RandomString randomString = new RandomString(36);
	devPayLoad = randomString.nextString();
	payload = devPayLoad;


	try {
		mHelper.launchPurchaseFlow(this, BYUING_HAIR_1, RC_REQUEST,
				mPurchaseFinishedListener, payload);
	} catch (IabHelper.IabAsyncInProgressException e) {
		complain("Error launching purchase flow. Another async operation in progress.");
		setWaitScreen(false);
	}
}//end of buy beard18


	public void buyHair2(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, BYUING_HAIR_2, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard2

	public void buyHair3(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, BYUING_HAIR_3, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard18

	public void buyHair4(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, BYUING_HAIR_4, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard18
	public void buyHair5(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, BYUING_HAIR_5, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard18
	public void buyHair6(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, BYUING_HAIR_6, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard18
	public void buyHair7(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, BYUING_HAIR_7, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard18
	public void buyHair8(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, BYUING_HAIR_8, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard18
	public void buyHair9(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, BYUING_HAIR_9, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard18
	public void buyHair10(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, BYUING_HAIR_10, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard18
	public void buyHair11(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, BYUING_HAIR_11, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard18
	public void buyHair12(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, BYUING_HAIR_12, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard18
	public void buyHair13(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, BYUING_HAIR_13, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard18
	public void buyHair14(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, BYUING_HAIR_14, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard18
	public void buyHair15(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, BYUING_HAIR_15, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard18
	public void buyHair16(){
		if (mSubscribedToInfiniteGas) {
			complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
			return;
		}

		setWaitScreen(true);
		android.util.Log.d(TAG, "Launching purchase flow for gas.");

	/* TODO: for security, generate your payload here for verification. See the comments on
     *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
     *        an empty string, but on a production app you should carefully generate this. */
		String payload = "";
		android.util.Log.e("testTag", "Buy Call");
		RandomString randomString = new RandomString(36);
		devPayLoad = randomString.nextString();
		payload = devPayLoad;


		try {
			mHelper.launchPurchaseFlow(this, BYUING_HAIR_16, RC_REQUEST,
					mPurchaseFinishedListener, payload);
		} catch (IabHelper.IabAsyncInProgressException e) {
			complain("Error launching purchase flow. Another async operation in progress.");
			setWaitScreen(false);
		}
	}//end of buy beard18




	// Enables or disables the "please wait" screen.
	void setWaitScreen(boolean set) {

		Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_SHORT).show();

		/*findViewById(R.id.screen_main).setVisibility(set ? View.GONE : View.VISIBLE);
		findViewById(R.id.screen_wait).setVisibility(set ? View.VISIBLE : View.GONE);*/
	}

	/** Verifies the developer payload of a purchase. */
	boolean verifyDeveloperPayload(Purchase p) {
		String payload = p.getDeveloperPayload();

        /*
         * TODO: verify that the developer payload of the purchase is correct. It will be
         * the same one that you sent when initiating the purchase.
         *
         * WARNING: Locally generating a random string when starting a purchase and
         * verifying it here might seem like a good approach, but this will fail in the
         * case where the user purchases an item on one device and then uses your app on
         * a different device, because on the other device you will not have access to the
         * random string you originally generated.
         *
         * So a good developer payload has these characteristics:
         *
         * 1. If two different users purchase an item, the payload is different between them,
         *    so that one user's purchase can't be replayed to another user.
         *
         * 2. The payload must be such that you can verify it even when the app wasn't the
         *    one who initiated the purchase flow (so that items purchased by the user on
         *    one device work on other devices owned by the user).
         *
         * Using your own server to store and verify developer payloads across app
         * installations is recommended.
         */

		return true;
	}



	public void loadData(){
		sharedPreferences = getSharedPreferences("FORSELLGOGGLE", 0);
		//values for glassess
		if(sharedPreferences!=null) {
			int boughtValue1 = sharedPreferences.getInt("GOGGLE1", 0);//defualt values is zero for all
			int boughtValue2 = sharedPreferences.getInt("GOGGLE2", 0);
			int boughtValue3 = sharedPreferences.getInt("GOGGLE3", 0);
			int boughtValue4 = sharedPreferences.getInt("GOGGLE4", 0);
			int boughtValue5 = sharedPreferences.getInt("GOGGLE5", 0);
			int boughtValue6 = sharedPreferences.getInt("GOGGLE6", 0);
			int boughtValue7 = sharedPreferences.getInt("GOGGLE7", 0);
			int boughtValue8 = sharedPreferences.getInt("GOGGLE8", 0);
			int boughtValue9 = sharedPreferences.getInt("GOGGLE9", 0);
			int boughtValue10 = sharedPreferences.getInt("GOGGLE10", 0);
			int boughtValue11 = sharedPreferences.getInt("GOGGLE11", 0);
			int boughtValue12 = sharedPreferences.getInt("GOGGLE12", 0);
			int boughtValue13 = sharedPreferences.getInt("GOGGLE13", 0);
			int boughtValue14 = sharedPreferences.getInt("GOGGLE14", 0);
			int boughtValue15 = sharedPreferences.getInt("GOGGLE15", 0);

			//values for beard
			int boughtbeard1 = sharedPreferences.getInt("BEARD1", 0);
			int boughtbeard2 = sharedPreferences.getInt("BEARD2", 0);
			int boughtbeard3 = sharedPreferences.getInt("BEARD3", 0);
			int boughtbeard4 = sharedPreferences.getInt("BEARD4", 0);
			int boughtbeard5 = sharedPreferences.getInt("BEARD5", 0);
			int boughtbeard6 = sharedPreferences.getInt("BEARD6", 0);
			int boughtbeard7 = sharedPreferences.getInt("BEARD7", 0);
			int boughtbeard8 = sharedPreferences.getInt("BEARD8", 0);
			int boughtbeard9 = sharedPreferences.getInt("BEARD9", 0);
			int boughtbeard10 = sharedPreferences.getInt("BEARD10", 0);
			int boughtbeard11 = sharedPreferences.getInt("BEARD11", 0);
			int boughtbeard12 = sharedPreferences.getInt("BEARD12", 0);
			int boughtbeard13 = sharedPreferences.getInt("BEARD13", 0);
			int boughtbeard14 = sharedPreferences.getInt("BEARD14", 0);
			int boughtbeard15 = sharedPreferences.getInt("BEARD15", 0);
			int boughtbeard16 = sharedPreferences.getInt("BEARD16", 0);
			int boughtbeard17 = sharedPreferences.getInt("BEARD17", 0);
			int boughtbeard18 = sharedPreferences.getInt("BEARD18", 0);

			//values for hairs

			int boughtHair1 = sharedPreferences.getInt("HAIR1", 0);
			int boughtHair2 = sharedPreferences.getInt("HAIR2", 0);
			int boughtHair3 = sharedPreferences.getInt("HAIR3", 0);
			int boughtHair4 = sharedPreferences.getInt("HAIR4", 0);
			int boughtHair5 = sharedPreferences.getInt("HAIR5", 0);
			int boughtHair6 = sharedPreferences.getInt("HAIR6", 0);
			int boughtHair7 = sharedPreferences.getInt("HAIR7", 0);
			int boughtHair8 = sharedPreferences.getInt("HAIR8", 0);
			int boughtHair9 = sharedPreferences.getInt("HAIR9", 0);
			int boughtHair10 = sharedPreferences.getInt("HAIR10", 0);
			int boughtHair11 = sharedPreferences.getInt("HAIR11", 0);
			int boughtHair12 = sharedPreferences.getInt("HAIR12", 0);
			int boughtHair13 = sharedPreferences.getInt("HAIR13", 0);
			int boughtHair14 = sharedPreferences.getInt("HAIR14", 0);
			int boughtHair15 = sharedPreferences.getInt("HAIR15", 0);
			int boughtHair16 = sharedPreferences.getInt("HAIR16", 0);


			if (boughtValue1 == 1) {
				starGoggle1.setVisibility(View.VISIBLE);
			}
			if (boughtValue2 == 2) {
				boughtGoggle2.setVisibility(View.VISIBLE);
			}

			if (boughtValue3 == 3) {
				boughtGoggle3.setVisibility(View.VISIBLE);
			}
			if (boughtValue4 == 4) {
				boughtGoggle4.setVisibility(View.VISIBLE);
			}
			if (boughtValue5 == 5) {
				boughtGoggle5.setVisibility(View.VISIBLE);
			}
			if (boughtValue6 == 6) {
				boughtGoggle6.setVisibility(View.VISIBLE);
			}
			if (boughtValue7 == 7) {
				boughtGoggle7.setVisibility(View.VISIBLE);
			}
			if (boughtValue8 == 8) {
				boughtGoggle8.setVisibility(View.VISIBLE);
			}
			if (boughtValue9 == 9) {
				boughtGoggle9.setVisibility(View.VISIBLE);
			}
			if (boughtValue10 == 10) {
				boughtGoggle10.setVisibility(View.VISIBLE);
			}
			if (boughtValue11 == 11) {
				boughtGoggle11.setVisibility(View.VISIBLE);
			}
			if (boughtValue12 == 12) {
				boughtGoggle12.setVisibility(View.VISIBLE);
			}
			if (boughtValue13 == 13) {
				boughtGoggle13.setVisibility(View.VISIBLE);
			}
			if (boughtValue14 == 14) {
				boughtGoggle14.setVisibility(View.VISIBLE);
			}
			if (boughtValue15 == 15) {
				boughtGoggle15.setVisibility(View.VISIBLE);
			}

			//beard icon visibliling ******************
			if (boughtbeard1 == 1) {
				boughtBeard1.setVisibility(View.VISIBLE);
			}
			if (boughtbeard2 == 2) {
				boughtBeard2.setVisibility(View.VISIBLE);
			}
			if (boughtbeard3 == 3) {
				boughtBeard3.setVisibility(View.VISIBLE);
			}
			if (boughtbeard4 == 4) {
				boughtBeard4.setVisibility(View.VISIBLE);
			}
			if (boughtbeard5 == 5) {
				boughtBeard5.setVisibility(View.VISIBLE);
			}
			if (boughtbeard6 == 6) {
				boughtBeard6.setVisibility(View.VISIBLE);
			}
			if (boughtbeard7 == 7) {
				boughtBeard7.setVisibility(View.VISIBLE);
			}
			if (boughtbeard8 == 8) {
				boughtBeard8.setVisibility(View.VISIBLE);
			}

			if (boughtbeard9 == 9) {
				boughtBeard9.setVisibility(View.VISIBLE);
			}
			if (boughtbeard10 == 10) {
				boughtBeard10.setVisibility(View.VISIBLE);
			}
			if (boughtbeard11 == 11) {
				boughtBeard11.setVisibility(View.VISIBLE);
			}
			if (boughtbeard12 == 12) {
				boughtBeard12.setVisibility(View.VISIBLE);
			}
			if (boughtbeard13 == 13) {
				boughtBeard13.setVisibility(View.VISIBLE);
			}
			if (boughtbeard14 == 14) {
				boughtBeard14.setVisibility(View.VISIBLE);
			}
			if (boughtbeard15 == 15) {
				boughtBeard15.setVisibility(View.VISIBLE);
			}
			if (boughtbeard16 == 16) {
				boughtBeard16.setVisibility(View.VISIBLE);
			}
			if (boughtbeard17 == 17) {
				boughtBeard17.setVisibility(View.VISIBLE);
			}
			if (boughtbeard18 == 18) {
				boughtBeard18.setVisibility(View.VISIBLE);
			}


			//Visibiling Hairs Icond
			if (boughtHair1 == 1) {
				boughtHairIco1.setVisibility(View.VISIBLE);
			}
			if (boughtHair2 == 2) {
				boughtHairIco2.setVisibility(View.VISIBLE);
			}
			if (boughtHair3 == 3) {
				boughtHairIco3.setVisibility(View.VISIBLE);
			}
			if (boughtHair4 == 4) {
				boughtHairIco4.setVisibility(View.VISIBLE);
			}
			if (boughtHair5 == 5) {
				boughtHairIco5.setVisibility(View.VISIBLE);
			}
			if (boughtHair6 == 6) {
				boughtHairIco6.setVisibility(View.VISIBLE);
			}
			if (boughtHair7 == 7) {
				boughtHairIco7.setVisibility(View.VISIBLE);
			}
			if (boughtHair8 == 8) {
				boughtHairIco8.setVisibility(View.VISIBLE);
			}
			if (boughtHair9 == 9) {
				boughtHairIco9.setVisibility(View.VISIBLE);
			}
			if (boughtHair11 == 11) {
				boughtHairIco11.setVisibility(View.VISIBLE);
			}
			if (boughtHair12 == 12) {
				boughtHairIco12.setVisibility(View.VISIBLE);
			}
			if (boughtHair13 == 13) {
				boughtHairIco13.setVisibility(View.VISIBLE);
			}
			if (boughtHair14 == 14) {
				boughtHairIco14.setVisibility(View.VISIBLE);
			}
			if (boughtHair15 == 15) {
				boughtHairIco15.setVisibility(View.VISIBLE);
			}
			if (boughtHair16 == 16) {
				boughtHairIco16.setVisibility(View.VISIBLE);
			}


			hidBuyIconWhenAllGoggleBought();
		}//end of checking sharePreferences

	}//end of load data


	public void hidGoggleFromDialogWhenBought(){
		sharedPreferences = getSharedPreferences("FORSELLGOGGLE", 0);
		int boughtValue1 = sharedPreferences.getInt("GOGGLE1", 0);//defualt values is zero for all
		int boughtValue2 = sharedPreferences.getInt("GOGGLE2", 0);
		int boughtValue3 = sharedPreferences.getInt("GOGGLE3", 0);
		int boughtValue4 = sharedPreferences.getInt("GOGGLE4", 0);
		int boughtValue5 = sharedPreferences.getInt("GOGGLE5", 0);
		int boughtValue6 = sharedPreferences.getInt("GOGGLE6", 0);
		int boughtValue7 = sharedPreferences.getInt("GOGGLE7", 0);
		int boughtValue8 = sharedPreferences.getInt("GOGGLE8", 0);
		int boughtValue9 = sharedPreferences.getInt("GOGGLE9", 0);
		int boughtValue10 = sharedPreferences.getInt("GOGGLE10", 0);
		int boughtValue11 = sharedPreferences.getInt("GOGGLE11", 0);
		int boughtValue12 = sharedPreferences.getInt("GOGGLE12", 0);
		int boughtValue13 = sharedPreferences.getInt("GOGGLE13", 0);
		int boughtValue14 = sharedPreferences.getInt("GOGGLE14", 0);
		int boughtValue15 = sharedPreferences.getInt("GOGGLE15", 0);

		//values for beard
		int boughtbeard1 = sharedPreferences.getInt("BEARD1", 0);
		int boughtbeard2 = sharedPreferences.getInt("BEARD2", 0);
		int boughtbeard3 = sharedPreferences.getInt("BEARD3", 0);
		int boughtbeard4 = sharedPreferences.getInt("BEARD4", 0);
		int boughtbeard5 = sharedPreferences.getInt("BEARD5", 0);
		int boughtbeard6 = sharedPreferences.getInt("BEARD6", 0);
		int boughtbeard7 = sharedPreferences.getInt("BEARD7", 0);
		int boughtbeard8 = sharedPreferences.getInt("BEARD8", 0);
		int boughtbeard9 = sharedPreferences.getInt("BEARD9", 0);
		int boughtbeard10 = sharedPreferences.getInt("BEARD10", 0);
		int boughtbeard11 = sharedPreferences.getInt("BEARD11", 0);
		int boughtbeard12 = sharedPreferences.getInt("BEARD12", 0);
		int boughtbeard13 = sharedPreferences.getInt("BEARD13", 0);
		int boughtbeard14 = sharedPreferences.getInt("BEARD14", 0);
		int boughtbeard15 = sharedPreferences.getInt("BEARD15", 0);
		int boughtbeard16 = sharedPreferences.getInt("BEARD16", 0);
		int boughtbeard17 = sharedPreferences.getInt("BEARD17", 0);
		int boughtbeard18 = sharedPreferences.getInt("BEARD18", 0);

		//values for hairs

		int boughtHair1 = sharedPreferences.getInt("HAIR1", 0);
		int boughtHair2 = sharedPreferences.getInt("HAIR2", 0);
		int boughtHair3 = sharedPreferences.getInt("HAIR3", 0);
		int boughtHair4 = sharedPreferences.getInt("HAIR4", 0);
		int boughtHair5 = sharedPreferences.getInt("HAIR5", 0);
		int boughtHair6 = sharedPreferences.getInt("HAIR6", 0);
		int boughtHair7 = sharedPreferences.getInt("HAIR7", 0);
		int boughtHair8 = sharedPreferences.getInt("HAIR8", 0);
		int boughtHair9 = sharedPreferences.getInt("HAIR9", 0);
		int boughtHair10 = sharedPreferences.getInt("HAIR10", 0);
		int boughtHair11 = sharedPreferences.getInt("HAIR11", 0);
		int boughtHair12 = sharedPreferences.getInt("HAIR12", 0);
		int boughtHair13 = sharedPreferences.getInt("HAIR13", 0);
		int boughtHair14 = sharedPreferences.getInt("HAIR14", 0);
		int boughtHair15 = sharedPreferences.getInt("HAIR15", 0);
		int boughtHair16 = sharedPreferences.getInt("HAIR16", 0);

try {

	if (boughtValue1 == 1) {
		forSelGlass1.setVisibility(View.GONE);
	}
	if (boughtValue2 == 2) {
		forSelGlass2.setVisibility(View.GONE);
	}
	if (boughtValue3 == 3) {
		forSelGlass3.setVisibility(View.GONE);
	}
	if (boughtValue4 == 4) {
		forSelGlass4.setVisibility(View.GONE);
	}
	if (boughtValue5 == 5) {
		forSelGlass5.setVisibility(View.GONE);
	}
	if (boughtValue6 == 6) {
		forSelGlass6.setVisibility(View.GONE);
	}
	if (boughtValue7 == 7) {
		forSelGlass7.setVisibility(View.GONE);
	}
	if (boughtValue8 == 8) {
		forSelGlass8.setVisibility(View.GONE);
	}
	if (boughtValue9 == 9) {
		forSelGlass9.setVisibility(View.GONE);
	}
	if (boughtValue10 == 10) {
		forSelGlass10.setVisibility(View.GONE);
	}
	if (boughtValue11 == 11) {
		forSelGlass11.setVisibility(View.GONE);
	}
	if (boughtValue12 == 12) {
		forSelGlass12.setVisibility(View.GONE);
	}
	if (boughtValue13 == 13) {
		forSelGlass13.setVisibility(View.GONE);
	}
	if (boughtValue14 == 14) {
		forSelGlass14.setVisibility(View.GONE);
	}
	if (boughtValue15 == 15) {
		forSelGlass15.setVisibility(View.GONE);
	}

	//hidng for sale beard from dialog

	if (boughtbeard1 == 1) {
		forSaleBear1.setVisibility(View.GONE);
	}
	if (boughtbeard2 == 2) {
		forSaleBear2.setVisibility(View.GONE);
	}
	if (boughtbeard3 == 3) {
		forSaleBear3.setVisibility(View.GONE);
	}
	if (boughtbeard4 == 4) {
		forSaleBear4.setVisibility(View.GONE);
	}
	if (boughtbeard5 == 5) {
		forSaleBear5.setVisibility(View.GONE);
	}
	if (boughtbeard6 == 6) {
		forSaleBear6.setVisibility(View.GONE);
	}
	if (boughtbeard7 == 7) {
		forSaleBear7.setVisibility(View.GONE);
	}
	if (boughtbeard8 == 8) {
		forSaleBear8.setVisibility(View.GONE);
	}
	if (boughtbeard9 == 9) {
		forSaleBear9.setVisibility(View.GONE);
	}
	if (boughtbeard10 == 10) {
		forSaleBear10.setVisibility(View.GONE);
	}
	if (boughtbeard11 == 11) {
		forSaleBear11.setVisibility(View.GONE);
	}
	if (boughtbeard12 == 12) {
		forSaleBear12.setVisibility(View.GONE);
	}
	if (boughtbeard13 == 13) {
		forSaleBear13.setVisibility(View.GONE);
	}
	if (boughtbeard14 == 14) {
		forSaleBear14.setVisibility(View.GONE);
	}
	if (boughtbeard15 == 15) {
		forSaleBear15.setVisibility(View.GONE);
	}
	if (boughtbeard16 == 16) {
		forSaleBear16.setVisibility(View.GONE);
	}
	if (boughtbeard17 == 17) {
		forSaleBear17.setVisibility(View.GONE);
	}
	if (boughtbeard18 == 18) {
		forSaleBear18.setVisibility(View.GONE);
	}


	//haind for sale image for hair from dialog

	if (boughtHair1 == 1) {
		forSaleHair1.setVisibility(View.GONE);
	}

	if (boughtHair2 == 2) {
		forSaleHair2.setVisibility(View.GONE);
	}
	if (boughtHair3 == 3) {
		forSaleHair3.setVisibility(View.GONE);
	}
	if (boughtHair4 == 4) {
		forSaleHair4.setVisibility(View.GONE);
	}
	if (boughtHair5 == 5) {
		forSaleHair5.setVisibility(View.GONE);
	}
	if (boughtHair6 == 6) {
		forSaleHair6.setVisibility(View.GONE);
	}
	if (boughtHair7 == 7) {
		forSaleHair7.setVisibility(View.GONE);
	}
	if (boughtHair8 == 8) {
		forSaleHair8.setVisibility(View.GONE);
	}
	if (boughtHair9 == 9) {
		forSaleHair9.setVisibility(View.GONE);
	}
	if (boughtHair10 == 10) {
		forSaleHair10.setVisibility(View.GONE);
	}
	if (boughtHair11 == 11) {
		forSaleHair11.setVisibility(View.GONE);
	}
	if (boughtHair12 == 12) {
		forSaleHair12.setVisibility(View.GONE);
	}
	if (boughtHair13 == 13) {
		forSaleHair13.setVisibility(View.GONE);
	}
	if (boughtHair14 == 14) {
		forSaleHair14.setVisibility(View.GONE);
	}
	if (boughtHair15 == 15) {
		forSaleHair15.setVisibility(View.GONE);
	}
	if (boughtHair16 == 16) {
		forSaleHair16.setVisibility(View.GONE);
	}
}catch (NullPointerException e){
	e.printStackTrace();
}

	}

	public void paidHairClickListener(){
		//bought hairs
		boughtHairIco1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(1);
			}
		});
		boughtHairIco2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(2);
			}
		});
		boughtHairIco3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(3);
			}
		});
		boughtHairIco4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(4);
			}
		});
		boughtHairIco5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(5);
			}
		});
		boughtHairIco6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(6);
			}
		});
		boughtHairIco7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(7);
			}
		});
		boughtHairIco8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(8);
			}
		});
		boughtHairIco9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(9);
			}
		});
		boughtHairIco10.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(10);
			}
		});
		boughtHairIco11.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(11);
			}
		});
		boughtHairIco12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(12);
			}
		});
		boughtHairIco13.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(13);
			}
		});
		boughtHairIco14.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(14);
			}
		});
		boughtHairIco15.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(15);
			}
		});
		boughtHairIco16.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidHair(16);
			}
		});

	}

	public void paidGoggleClickListener(){
		//bought goggle
		starGoggle1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidGlasses(1);
			}
		});

		boughtGoggle2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidGlasses(2);
			}
		});

		boughtGoggle3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidGlasses(3);
			}
		});

		boughtGoggle4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidGlasses(4);
			}
		});

		boughtGoggle5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidGlasses(5);
			}
		});

		boughtGoggle6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidGlasses(6);
			}
		});

		boughtGoggle7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidGlasses(7);
			}
		});

		boughtGoggle8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidGlasses(8);
			}
		});

		boughtGoggle9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidGlasses(9);
			}
		});

		boughtGoggle10.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidGlasses(10);
			}
		});

		boughtGoggle11.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidGlasses(11);
			}
		});

		boughtGoggle12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidGlasses(12);
			}
		});

		boughtGoggle13.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidGlasses(13);
			}
		});

		boughtGoggle14.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidGlasses(14);
			}
		});

		boughtGoggle15.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidGlasses(15);
			}
		});
		//end of bought goggle click listener

	}

	public void paidBeardClickListener(){
		boughtBeard1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(1);
			}
		});
		boughtBeard2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(2);
			}
		});
		boughtBeard3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(3);
			}
		});
		boughtBeard4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(4);
			}
		});
		boughtBeard5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(5);
			}
		});
		boughtBeard6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(6);
			}
		});
		boughtBeard7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(7);
			}
		});
		boughtBeard8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(8);
			}
		});
		boughtBeard9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(9);
			}
		});
		boughtBeard10.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(10);
			}
		});
		boughtBeard11.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(11);
			}
		});
		boughtBeard12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(12);
			}
		});
		boughtBeard13.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(13);
			}
		});
		boughtBeard14.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(14);
			}
		});
		boughtBeard15.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(15);
			}
		});
		boughtBeard16.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(16);
			}
		});
		boughtBeard17.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(17);
			}
		});
		boughtBeard18.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPaidBeard(18);
			}
		});

	}

	public void onSuccessGoggleBought(){
		sharedPreferences = getSharedPreferences("FORSELLGOGGLE", 0);
		SharedPreferences.Editor editor = sharedPreferences.edit();

		if (goggleNumber==1) {
			setPaidGlasses(1);
			//additing values to sharepreferences
			editor.putInt("GOGGLE1", 1);//int value 1 is for stargoggle
			starGoggle1.setVisibility(View.VISIBLE);
		}
		if (goggleNumber==2) {
			setPaidGlasses(2);
			//additing values to sharepreferences
			editor.putInt("GOGGLE2", 2);//int value 2 is for stargoggle
			boughtGoggle2.setVisibility(View.VISIBLE);
		}
		if (goggleNumber==3) {
			setPaidGlasses(3);
			//additing values to sharepreferences
			editor.putInt("GOGGLE3", 3);//int value 3 is for stargoggle
			boughtGoggle3.setVisibility(View.VISIBLE);
		}
		if (goggleNumber==4) {
			setPaidGlasses(4);
			//additing values to sharepreferences
			editor.putInt("GOGGLE4", 4);//int value 4 is for stargoggle
			boughtGoggle4.setVisibility(View.VISIBLE);
		}
		if (goggleNumber==5) {
			setPaidGlasses(5);
			//additing values to sharepreferences
			editor.putInt("GOGGLE5", 5);//int value 5 is for stargoggle
			boughtGoggle5.setVisibility(View.VISIBLE);
		}
		if (goggleNumber==6) {
			setPaidGlasses(6);
			//additing values to sharepreferences
			editor.putInt("GOGGLE6", 6);//int value 6 is for stargoggle
			boughtGoggle6.setVisibility(View.VISIBLE);
		}
		if (goggleNumber==7) {
			setPaidGlasses(7);
			//additing values to sharepreferences
			editor.putInt("GOGGLE7", 7);//int value 7 is for stargoggle
			boughtGoggle7.setVisibility(View.VISIBLE);
		}
		if (goggleNumber==8) {
			setPaidGlasses(8);
			//additing values to sharepreferences
			editor.putInt("GOGGLE8", 8);//int value 8 is for stargoggle
			boughtGoggle8.setVisibility(View.VISIBLE);
		}
		if (goggleNumber==9) {
			setPaidGlasses(9);
			//additing values to sharepreferences
			editor.putInt("GOGGLE9", 9);//int value 9 is for stargoggle
			boughtGoggle9.setVisibility(View.VISIBLE);
		}
		if (goggleNumber==10) {
			setPaidGlasses(10);
			//additing values to sharepreferences
			editor.putInt("GOGGLE10", 10);//int value 10 is for stargoggle
			boughtGoggle10.setVisibility(View.VISIBLE);
		}
		if (goggleNumber==11) {
			setPaidGlasses(11);
			//additing values to sharepreferences
			editor.putInt("GOGGLE11", 11);//int value 11 is for stargoggle
			boughtGoggle11.setVisibility(View.VISIBLE);
		}
		if (goggleNumber==12) {
			setPaidGlasses(12);
			//additing values to sharepreferences
			editor.putInt("GOGGLE12", 12);//int value 12 is for stargoggle
			boughtGoggle12.setVisibility(View.VISIBLE);
		}
		if (goggleNumber==13) {
			setPaidGlasses(13);
			//additing values to sharepreferences
			editor.putInt("GOGGLE13", 13);//int value 13 is for stargoggle
			boughtGoggle13.setVisibility(View.VISIBLE);
		}
		if (goggleNumber==14) {
			setPaidGlasses(14);
			//additing values to sharepreferences
			editor.putInt("GOGGLE14", 14);//int value 14 is for stargoggle
			boughtGoggle14.setVisibility(View.VISIBLE);
		}
		if (goggleNumber==15) {
			setPaidGlasses(15);
			//additing values to sharepreferences
			editor.putInt("GOGGLE15", 15);//int value 15 is for stargoggle
			boughtGoggle15.setVisibility(View.VISIBLE);
		}

		if (beardNumber==1){
			setPaidBeard(1);
			//addinting values to sharepreferences
			editor.putInt("BEARD1", 1);
			boughtBeard1.setVisibility(View.VISIBLE);
		}
		if (beardNumber==2){
			setPaidBeard(2);
			//addinting values to sharepreferences
			editor.putInt("BEARD2", 2);
			boughtBeard2.setVisibility(View.VISIBLE);
		}
		if (beardNumber==3){
			setPaidBeard(3);
			//addinting values to sharepreferences
			editor.putInt("BEARD3", 3);
			boughtBeard3.setVisibility(View.VISIBLE);
		}
		if (beardNumber==4){
			setPaidBeard(4);
			//addinting values to sharepreferences
			editor.putInt("BEARD4", 4);
			boughtBeard4.setVisibility(View.VISIBLE);
		}
		if (beardNumber==5){
			setPaidBeard(5);
			//addinting values to sharepreferences
			editor.putInt("BEARD5", 5);
			boughtBeard5.setVisibility(View.VISIBLE);
		}
		if (beardNumber==6){
			setPaidBeard(6);
			//addinting values to sharepreferences
			editor.putInt("BEARD6", 6);
			boughtBeard6.setVisibility(View.VISIBLE);
		}
		if (beardNumber==7){
			setPaidBeard(7);
			//addinting values to sharepreferences
			editor.putInt("BEARD7", 7);
			boughtBeard7.setVisibility(View.VISIBLE);
		}
		if (beardNumber==8){
			setPaidBeard(8);
			//addinting values to sharepreferences
			editor.putInt("BEARD8", 8);
			boughtBeard8.setVisibility(View.VISIBLE);
		}
		if (beardNumber==9){
			setPaidBeard(9);
			//addinting values to sharepreferences
			editor.putInt("BEARD9", 9);
			boughtBeard9.setVisibility(View.VISIBLE);
		}
		if (beardNumber==10){
			setPaidBeard(10);
			//addinting values to sharepreferences
			editor.putInt("BEARD10", 10);
			boughtBeard10.setVisibility(View.VISIBLE);
		}
		if (beardNumber==11){
			setPaidBeard(11);
			//addinting values to sharepreferences
			editor.putInt("BEARD11", 11);
			boughtBeard11.setVisibility(View.VISIBLE);
		}
		if (beardNumber==12){
			setPaidBeard(12);
			//addinting values to sharepreferences
			editor.putInt("BEARD12", 12);
			boughtBeard12.setVisibility(View.VISIBLE);
		}
		if (beardNumber==13){
			setPaidBeard(13);
			//addinting values to sharepreferences
			editor.putInt("BEARD13", 13);
			boughtBeard13.setVisibility(View.VISIBLE);
		}
		if (beardNumber==14){
			setPaidBeard(14);
			//addinting values to sharepreferences
			editor.putInt("BEARD14", 14);
			boughtBeard14.setVisibility(View.VISIBLE);
		}
		if (beardNumber==15){
			setPaidBeard(15);
			//addinting values to sharepreferences
			editor.putInt("BEARD15", 15);
		}
		if (beardNumber==16){
			setPaidBeard(16);
			//addinting values to sharepreferences
			editor.putInt("BEARD16", 16);
			boughtBeard16.setVisibility(View.VISIBLE);
		}
		if (beardNumber==17){
			setPaidBeard(17);
			//addinting values to sharepreferences
			editor.putInt("BEARD17", 17);
			boughtBeard17.setVisibility(View.VISIBLE);
		}
		if (beardNumber==18){
			setPaidBeard(18);
			//addinting values to sharepreferences
			editor.putInt("BEARD18", 18);
			boughtBeard18.setVisibility(View.VISIBLE);
		}


		//****************************************** for hairs  *********************************

		if (hairNumber==1){
			setPaidHair(1);
			editor.putInt("HAIR1", 1);
			boughtHairIco1.setVisibility(View.VISIBLE);
		}
		if (hairNumber==2){
			setPaidHair(2);
			editor.putInt("HAIR2", 2);
			boughtHairIco2.setVisibility(View.VISIBLE);
		}
		if (hairNumber==3){
			setPaidHair(3);
			editor.putInt("HAIR3", 3);
			boughtHairIco3.setVisibility(View.VISIBLE);
		}
		if (hairNumber==4){
			setPaidHair(4);
			editor.putInt("HAIR4", 4);
			boughtHairIco4.setVisibility(View.VISIBLE);
		}
		if (hairNumber==5){
			setPaidHair(5);
			editor.putInt("HAIR5", 5);
			boughtHairIco5.setVisibility(View.VISIBLE);
		}
		if (hairNumber==6){
			setPaidHair(6);
			editor.putInt("HAIR6", 6);
			boughtHairIco6.setVisibility(View.VISIBLE);
		}
		if (hairNumber==7){
			setPaidHair(7);
			editor.putInt("HAIR7", 7);
			boughtHairIco7.setVisibility(View.VISIBLE);
		}
		if (hairNumber==8){
			setPaidHair(8);
			editor.putInt("HAIR8", 8);
			boughtHairIco8.setVisibility(View.VISIBLE);
		}
		if (hairNumber==9){
			setPaidHair(9);
			editor.putInt("HAIR9", 9);
			boughtHairIco9.setVisibility(View.VISIBLE);
		}
		if (hairNumber==10){
			setPaidHair(10);
			editor.putInt("HAIR10", 10);
			boughtHairIco10.setVisibility(View.VISIBLE);
		}
		if (hairNumber==11){
			setPaidHair(11);
			editor.putInt("HAIR11", 11);
			boughtHairIco11.setVisibility(View.VISIBLE);
		}
		if (hairNumber==12){
			setPaidHair(12);
			editor.putInt("HAIR12", 12);
			boughtHairIco12.setVisibility(View.VISIBLE);
		}
		if (hairNumber==13){
			setPaidHair(13);
			editor.putInt("HAIR13", 13);
			boughtHairIco13.setVisibility(View.VISIBLE);
		}
		if (hairNumber==14){
			setPaidHair(14);
			editor.putInt("HAIR14", 14);
			boughtHairIco14.setVisibility(View.VISIBLE);
		}
		if (hairNumber==15){
			setPaidHair(15);
			editor.putInt("HAIR15", 15);
			boughtHairIco15.setVisibility(View.VISIBLE);
		}
		if (hairNumber==16){
			setPaidHair(16);
			editor.putInt("HAIR16", 16);
			boughtHairIco16.setVisibility(View.VISIBLE);
		}

		//*****************
		editor.commit();

		hidBuyIconWhenAllGoggleBought();

	}//end of on successfull bought

	@Override
	protected void onResume() {
		super.onResume();
		if (pDilaog.isShowing()){
			pDilaog.dismiss();
		}

		if (pBeardDialog.isShowing()){
			pBeardDialog.dismiss();
		}

		if (pHaireDialog.isShowing()){
			pHaireDialog.dismiss();
		}
	}

	//hiding buy icon from view when all goggle bought
	public void hidBuyIconWhenAllGoggleBought(){
		if (starGoggle1.getVisibility()==View.VISIBLE
				&& boughtGoggle2.getVisibility()==View.VISIBLE
				&& boughtGoggle3.getVisibility()==View.VISIBLE
				&& boughtGoggle4.getVisibility()==View.VISIBLE
				&& boughtGoggle5.getVisibility()==View.VISIBLE
				&& boughtGoggle6.getVisibility()==View.VISIBLE
				&& boughtGoggle7.getVisibility()==View.VISIBLE
				&& boughtGoggle8.getVisibility()==View.VISIBLE
				&& boughtGoggle9.getVisibility()==View.VISIBLE
				&& boughtGoggle10.getVisibility()==View.VISIBLE
				&& boughtGoggle11.getVisibility()==View.VISIBLE
				&& boughtGoggle12.getVisibility()==View.VISIBLE
				&& boughtGoggle13.getVisibility()==View.VISIBLE
				&& boughtGoggle14.getVisibility()==View.VISIBLE
				&& boughtGoggle15.getVisibility()==View.VISIBLE
				)
		{

			glassesIcon_8.setVisibility(View.GONE);

		}

		if (boughtBeard1.getVisibility()==View.VISIBLE
				&& boughtBeard2.getVisibility()==View.VISIBLE
				&& boughtBeard3.getVisibility()==View.VISIBLE
				&& boughtBeard4.getVisibility()==View.VISIBLE
				&& boughtBeard5.getVisibility()==View.VISIBLE
				&& boughtBeard6.getVisibility()==View.VISIBLE
				&& boughtBeard7.getVisibility()==View.VISIBLE
				&& boughtBeard8.getVisibility()==View.VISIBLE
				&& boughtBeard9.getVisibility()==View.VISIBLE
				&& boughtBeard10.getVisibility()==View.VISIBLE
				&& boughtBeard11.getVisibility()==View.VISIBLE
				&& boughtBeard12.getVisibility()==View.VISIBLE
				&& boughtBeard13.getVisibility()==View.VISIBLE
				&& boughtBeard14.getVisibility()==View.VISIBLE
				&& boughtBeard15.getVisibility()==View.VISIBLE
				&& boughtBeard16.getVisibility()==View.VISIBLE
				&& boughtBeard17.getVisibility()==View.VISIBLE
				&& boughtBeard18.getVisibility()==View.VISIBLE
				)
		{
			beard_buy_more.setVisibility(View.GONE);
		}

		//hidding buy more icon for hair

		if (boughtHairIco1.getVisibility()==View.VISIBLE
				&& boughtHairIco2.getVisibility()==View.VISIBLE
				&& boughtHairIco3.getVisibility()==View.VISIBLE
				&& boughtHairIco4.getVisibility()==View.VISIBLE
				&& boughtHairIco5.getVisibility()==View.VISIBLE
				&& boughtHairIco6.getVisibility()==View.VISIBLE
				&& boughtHairIco7.getVisibility()==View.VISIBLE
				&& boughtHairIco8.getVisibility()==View.VISIBLE
				&& boughtHairIco9.getVisibility()==View.VISIBLE
				&& boughtHairIco10.getVisibility()==View.VISIBLE
				&& boughtHairIco11.getVisibility()==View.VISIBLE
				&& boughtHairIco12.getVisibility()==View.VISIBLE
				&& boughtHairIco13.getVisibility()==View.VISIBLE
				&& boughtHairIco14.getVisibility()==View.VISIBLE
				&& boughtHairIco15.getVisibility()==View.VISIBLE
				&& boughtHairIco16.getVisibility()==View.VISIBLE
				){

			hairIcon_12.setVisibility(View.GONE);
		}

	}//end of hidBuyIconWhen All Goggle Bought


}