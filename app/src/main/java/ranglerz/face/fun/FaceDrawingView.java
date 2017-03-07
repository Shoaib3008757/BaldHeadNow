package ranglerz.face.fun;

import java.util.ArrayList;
import java.util.Random;

import ranglerz.face.fun.model.CircleArea;
import ranglerz.face.fun.model.Face;
import ranglerz.face.fun.model.Globals;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

public class FaceDrawingView extends View {

    private static final String TAG = "CirclesDrawingView";

    /** Main bitmap */
    private Bitmap mBitmap = null,canvasOverlay=null,rightEyeMask=null,leftEyeMask=null,chinMask=null,foreHeadMask = null,glassesMask = null,beardMask = null,spotsMask=null,hairMask=null;
    private Bitmap foreheadAfterBitmap=null,righteyeAfterBitmap=null,lefteyeAfterBitmap=null,chinAfterBitmap=null;
    
    private Bitmap moverBitmap=null;
    private Bitmap landmarkBitmap = null;
	private Bitmap skinTonePicker = null;
    private boolean isSetMaskEnabled = false,isGlassesEnabled=false,isBeardEnabled=false,isspotsEnabled=false,isHairEnabled=false;
    private Rect mMeasuredRect;
    float maskSaturation=1;
    public CircleArea colorPicker;
    private ArrayList<Face> faces = new ArrayList<Face>(); 
    float red,green,blue;
    
    
    /** Paint to draw circles */
    private Paint mCirclePaint;

    private final Random mRadiusGenerator = new Random();
    // Radius limit in pixels
    private final static int RADIUS_LIMIT = 100;

    private static final int CIRCLES_LIMIT = 3;

    /** All available circles */
//    private ArrayList<CircleArea> mCircles = new ArrayList<CircleArea>(CIRCLES_LIMIT);
    private SparseArray<CircleArea> mCirclePointer = new SparseArray<CircleArea>(CIRCLES_LIMIT);

    /**
     * Default constructor
     *
     * @param ct {@link android.content.Context}
     */
    public FaceDrawingView(final Context ct) {
        super(ct);
        
        init(ct);
    }

    public FaceDrawingView(final Context ct, final AttributeSet attrs) {
        super(ct, attrs);
        
        init(ct);
    }

    public FaceDrawingView(final Context ct, final AttributeSet attrs, final int defStyle) {
        super(ct, attrs, defStyle);
        
        init(ct);
    }

    private void init(final Context ct) {
        // Generate bitmap used for background

        Log.d(TAG, "init: ");
        
        mBitmap = BitmapFactory.decodeResource(ct.getResources(), R.drawable.logo);
        canvasOverlay = BitmapFactory.decodeResource(ct.getResources(), R.drawable.canvasoverlay);
        landmarkBitmap = BitmapFactory.decodeResource(ct.getResources(), R.drawable.landmark_icon);
		skinTonePicker = BitmapFactory.decodeResource(ct.getResources(), R.drawable.ic_action_picker);
        moverBitmap = BitmapFactory.decodeResource(ct.getResources(), R.drawable.movercircle);


        moverBitmap = Bitmap.createScaledBitmap(moverBitmap, 50, 50, false);
        landmarkBitmap = Bitmap.createScaledBitmap(landmarkBitmap, landmarkBitmap.getWidth()/2, landmarkBitmap.getHeight()/2,false);
		skinTonePicker = Bitmap.createScaledBitmap(skinTonePicker, skinTonePicker.getWidth(), skinTonePicker.getHeight(),false);
        colorPicker = new CircleArea(0, 0, 150);
        
        mCirclePaint = new Paint();

        mCirclePaint.setColor(Color.BLUE);
        mCirclePaint.setStrokeWidth(40);
        mCirclePaint.setStyle(Paint.Style.FILL);
    }





    public void setBitmap(Bitmap b,Bitmap canvasOverlay)
    {
        Log.d(TAG, "setBitmap: ");
        mBitmap = b;
    	this.canvasOverlay = canvasOverlay;
    	invalidate();
    }

    public void AddFace(Face f)
    {
        Log.d(TAG, "AddFace: ");
        
        faces.add(f);
    }
    
    public void setMasks(boolean isSetMaskEnabled,Bitmap rightEyeMask,Bitmap leftEyeMask,Bitmap chinMask,Bitmap foreHeadMask)
    {

        Log.d(TAG, "setMasks: ");
        this.isSetMaskEnabled = isSetMaskEnabled;
    	if(isSetMaskEnabled)
    	{
			Log.d(TAG, "setMasks: Mask Enabled");

			this.rightEyeMask = rightEyeMask;
	    	this.leftEyeMask = leftEyeMask;
	    	this.chinMask = chinMask;
	    	this.foreHeadMask = foreHeadMask;

	    	int foreheadPixel = mBitmap.getPixel(colorPicker.centerX, colorPicker.centerY);

	    	red = Color.red(foreheadPixel) /128f;
	    	green = Color.green(foreheadPixel)/128f;
	      	blue = Color.blue(foreheadPixel)/128f;

			colorMask3();
    	}
    	
    	invalidate();
    }

    private void colorMask3()
    {
        Log.d(TAG, "colorMask3: ");
        Paint paint = new Paint();
    	
    	float[] src = new float[]{
                red, 0, 0, 0, 0, 
                0, green, 0, 0, 0,
                0, 0, blue, 0, 0, 
                0, 0, 0, maskSaturation, 0};
        // The definition of ColorMatrix, and specify the RGBA matrix
        //ColorMatrix colorMatrix = new ColorMatrix();
//        float saturation = (float)(maskSaturation);
//        colorMatrix.setSaturation(saturation);
//        colorMatrix.set(src);
        // Set the Paint color
        paint.setColorFilter(new ColorMatrixColorFilter(src));
        // By specifying the RGBA matrix Paint of the original picture of the blank picture
     // Empty picture gets a and baseBitmap the same size editable
        foreheadAfterBitmap = Bitmap.createBitmap(foreHeadMask.getWidth(),
                foreHeadMask.getHeight(), foreHeadMask.getConfig());
        
        lefteyeAfterBitmap = Bitmap.createBitmap(leftEyeMask.getWidth(),
                leftEyeMask.getHeight(), leftEyeMask.getConfig());
        
        righteyeAfterBitmap = Bitmap.createBitmap(rightEyeMask.getWidth(),
                rightEyeMask.getHeight(), rightEyeMask.getConfig());
        
        chinAfterBitmap = Bitmap.createBitmap(chinMask.getWidth(),
                chinMask.getHeight(), chinMask.getConfig());
        
        
        
        
        Canvas canvas = new Canvas(foreheadAfterBitmap);
        canvas.drawBitmap(foreHeadMask, new Matrix(), paint);
        
        canvas = new Canvas(lefteyeAfterBitmap);
        canvas.drawBitmap(leftEyeMask, new Matrix(), paint);
        
        canvas = new Canvas(righteyeAfterBitmap);
        canvas.drawBitmap(rightEyeMask, new Matrix(), paint);
        
        canvas = new Canvas(chinAfterBitmap);
        canvas.drawBitmap(chinMask, new Matrix(), paint);

    }
    
    public void setMaskSaturation(float value)
    {
        Log.d(TAG, "setMaskSaturation: ");
        this.maskSaturation = value;        
    	colorMask3();
        invalidate();
    	
    }


    public void setGlasses(boolean isGlassesEnabled,Bitmap glassesMask)
    {

        Log.d(TAG, "setGlasses: ");
        this.isGlassesEnabled = isGlassesEnabled;
    	this.glassesMask = glassesMask;
    	invalidate();
    }
    public void setBeard(boolean isBeardEnabled,Bitmap beardMask)
    {

        Log.d(TAG, "setBeard: ");
        
        this.isBeardEnabled = isBeardEnabled;
    	this.beardMask = beardMask;
    	invalidate();
    }

	// TODO for adjustment in spots change the number that are multiplyed with spots width and height...
    public void setSpots(boolean isspotsEnabled,Bitmap spotsMask)
    {
    	this.isspotsEnabled = isspotsEnabled;
    	this.spotsMask = spotsMask;
    	for(Face f: faces)
    	{
    		f.mCircles.get(6).centerX = f.mCircles.get(1).centerX + ((20*spotsMask.getWidth())/100) ;
    		f.mCircles.get(6).centerY = f.mCircles.get(1).centerY + ((150*spotsMask.getHeight())/100);
    	}
    	invalidate();
    }
    public void setHair(boolean isHairEnabled,Bitmap hairMask)
    {
    	this.hairMask = hairMask;
    	this.isHairEnabled = isHairEnabled;
    	invalidate();
    }

	public void removeAllUnsedBitmaps()
	{
		this.isHairEnabled = false;
		this.isBeardEnabled = false;
		this.isGlassesEnabled = false;
		this.isspotsEnabled = false;


		invalidate();
	}

	public void drawUndoBitmap(Bitmap hairMask ,int position )
	{
		if(position == Globals.SPOTPOSITION)
		{
			this.isspotsEnabled = true;
			this.spotsMask = hairMask;
		}
		else if((position == Globals.HAIRPOSITION))
		{
			this.isHairEnabled = true;
			this.isGlassesEnabled = false;
			this.isBeardEnabled = false;
			this.isspotsEnabled = false;

			this.hairMask = hairMask;
		}
		else if((position == Globals.GLASSESPOSITION))
		{

			this.isHairEnabled = false;
			this.isGlassesEnabled = true;
			this.isBeardEnabled = false;
			this.isspotsEnabled = false;
			this.glassesMask = hairMask;
		}
		else if((position == Globals.BEARDPOSITION))
		{

			this.isHairEnabled = false;
			this.isGlassesEnabled = false;
			this.isBeardEnabled = true;
			this.isspotsEnabled = false;
			this.beardMask = hairMask;
		}

		invalidate();
	}
    
    public void Resize(int type,int option)//type 1:hair 2:glasses 3:beard || option: 1:increase 2:reduce
    {


        Log.d(TAG, "Resize: ");
        if(type==1 && hairMask!=null)
    	{
    		int width = hairMask.getWidth();
			int height = hairMask.getHeight();
			float updatedWidth =(float) (5*width)/100;
			float scaleFactor =0;
    		if(option == 1)
    		{
    			scaleFactor =(float) (width+updatedWidth)/width;
    		}
    		else if(option == 2)
    		{
    			scaleFactor =(float) (width-updatedWidth)/width;
    		}
    		width = (int) (width*scaleFactor);
			height =(int) (height*scaleFactor);
			hairMask = Bitmap.createScaledBitmap(hairMask,width, height, false);
    	}
    	else if(type==2 && glassesMask!=null)
    	{

            Log.d(TAG, "Resize: GLasses ");
            int width = glassesMask.getWidth();
			int height = glassesMask.getHeight();
			float updatedWidth =(float) (5*width)/100;
			float scaleFactor =0;
    		if(option == 1)
    		{
    			scaleFactor =(float) (width+updatedWidth)/width;
    		}
    		else if(option == 2)
    		{
    			scaleFactor =(float) (width-updatedWidth)/width;
    		}
    		width = (int) (width*scaleFactor);
			height =(int) (height*scaleFactor);
			glassesMask = Bitmap.createScaledBitmap(glassesMask,width, height, false);
    	}
    	else if(type==3 && beardMask!=null)
    	{
    		int width = beardMask.getWidth();
			int height = beardMask.getHeight();
			float updatedWidth =(float) (5*width)/100;
			float scaleFactor =0;
    		if(option == 1)
    		{
    			scaleFactor =(float) (width+updatedWidth)/width;
    		}
    		else if(option == 2)
    		{
    			scaleFactor =(float) (width-updatedWidth)/width;
    		}
    		width = (int) (width*scaleFactor);
			height =(int) (height*scaleFactor);
			beardMask = Bitmap.createScaledBitmap(beardMask,width, height, false);
    	}
    	invalidate();
    }
    boolean isHairRotated,isGlassesRotated,isBeardRotated;
    public void Rotate(int type,int option)//type 1:hair 2:glasses 3:beard || option: 1:clockwise 2:anticlockwise
    {


        Log.d(TAG, "Rotate: ");
        
        Matrix matrix = new Matrix();
		if(option == 1)
		{
			matrix.postRotate(5);
		}
		else if(option == 2)
		{
			matrix.postRotate(-5);
		}
		
    	if(type==1 && hairMask!=null)
    	{
    		hairMask = Bitmap.createBitmap(hairMask , 0, 0, hairMask.getWidth(), hairMask.getHeight(), matrix, true);
    	}
    	else if(type==2 && glassesMask!=null)
    	{
            Log.d(TAG, "Rotate: Glasses");
            
            
            glassesMask = Bitmap.createBitmap(glassesMask , 0, 0, glassesMask.getWidth(), glassesMask.getHeight(), matrix, true);
    	}
    	else if(type==3 && beardMask!=null)
    	{
    		beardMask = Bitmap.createBitmap(beardMask , 0, 0, beardMask.getWidth(), beardMask.getHeight(), matrix, true);
    	}
    	invalidate();
    }
    @Override
    public void onDraw(final Canvas canv) {
        // background bitmap to cover all area
        canv.drawBitmap(mBitmap, null, mMeasuredRect, null);


        Log.d(TAG, "onDraw: ");
        
        
        if(isSetMaskEnabled)
        {
        	Log.d("FaceDrawingView","setmask enabled");
        	drawMask(canv);
//        	canv.drawBitmap(moverBitmap,mCircles.get(6).centerX - (landmarkBitmap.getWidth()/2), mCircles.get(6).centerY - (landmarkBitmap.getHeight()/2),  mCirclePaint);
        }
        else
        {
        	for(Face f: faces)
        	{
	        	int i=0;
		        for (CircleArea circle : f.mCircles) {
		        
	//	            canv.drawCircle(circle.centerX, circle.centerY, circle.radius, mCirclePaint);
		        	if(i!=3 && i!=6)//3:glasses 6:dots
		        	{
		        		canv.drawBitmap(landmarkBitmap, circle.centerX - (landmarkBitmap.getWidth()/2), circle.centerY - (landmarkBitmap.getHeight()/2),  mCirclePaint);	        		
		        	}
	
		        	else
		        	{
	//	        		canv.drawBitmap(moverBitmap, circle.centerX - (moverBitmap.getWidth()/2), circle.centerY - (moverBitmap.getHeight()/2),  mCirclePaint);
		        	}
		        	i++;

		        }
		        
        	}


			// for drawing color picker on face or skin ton picker...
			// you can change any drawable image for this
    		canv.drawBitmap(skinTonePicker, colorPicker.centerX, colorPicker.centerY ,  mCirclePaint);

        }
//        canv.drawBitmap(canvasOverlay, null, mMeasuredRect, null);
    }

    public void drawMask(Canvas canv)
    {

        Log.d(TAG, "drawMask: ");
        
        for(Face f:faces)
    	{
//    		ColorMatrix colorMatrix = new ColorMatrix();
//            colorMatrix.setSaturation(maskSaturation); //Remove Colour
//
//            ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
//            Paint paint = new Paint();
////            paint.setAlpha(30);
//            paint.setColorFilter(colorFilter);   

	    	float rightEyeXMinus = (55*rightEyeMask.getWidth())/100;
	    	float rightEyeYMinus = (32*rightEyeMask.getHeight())/100;
	    	canv.drawBitmap(righteyeAfterBitmap, f.mCircles.get(0).centerX-rightEyeXMinus, f.mCircles.get(0).centerY-rightEyeYMinus, null);


	    	
	    	float leftEyeXMinus = (40*leftEyeMask.getWidth())/100;
	    	float leftEyeYMinus = (32*leftEyeMask.getHeight())/100;
	    	canv.drawBitmap(lefteyeAfterBitmap, f.mCircles.get(1).centerX-leftEyeXMinus, f.mCircles.get(1).centerY-leftEyeYMinus, null);
	    	
	    	float chinXMinus = (50*chinMask.getWidth())/100;
	    	float chinYMinus = (30*chinMask.getHeight())/100;
	    	canv.drawBitmap(chinAfterBitmap, f.mCircles.get(4).centerX-chinXMinus, f.mCircles.get(4).centerY-chinYMinus, null);
	
	    	float foreheadXMinus = (50*foreheadAfterBitmap.getWidth())/100;
	    	float foreheadYMinus = (50*foreheadAfterBitmap.getHeight())/100;
	    	canv.drawBitmap(foreheadAfterBitmap, f.mCircles.get(5).centerX-foreheadXMinus, f.mCircles.get(5).centerY-foreheadYMinus, null);
	    	
	    	if(isGlassesEnabled)
	    	{

                Log.d(TAG, "drawMask: Glasses");

                int glassesCenterX,glassesCenterY;
		    	float glassesXMinus = (50*glassesMask.getWidth())/100;
		    	float glassesYMinus = (50*glassesMask.getHeight())/100;
//		    	glassesCenterX = f.mCircles.get(0).centerX +((f.mCircles.get(1).centerX - f.mCircles.get(0).centerX)/2);
//		    	glassesCenterY = f.mCircles.get(0).centerY;
		    	glassesCenterX = f.mCircles.get(3).centerX;
		    	glassesCenterY = f.mCircles.get(3).centerY;		
		    	canv.drawBitmap(glassesMask, glassesCenterX-glassesXMinus, glassesCenterY-glassesYMinus, null);
	    	}
	    	if(isBeardEnabled)
	    	{
				Log.d(TAG, "drawMask: Beared");
	    		int beardCenterX,beardCenterY;
		    	float beardXMinus = (50*beardMask.getWidth())/100;
		    	float beardYMinus = (70*beardMask.getHeight())/100;
		    	beardCenterX = f.mCircles.get(4).centerX;
		    	beardCenterY = f.mCircles.get(4).centerY;
		    			
		    	canv.drawBitmap(beardMask, beardCenterX-beardXMinus, beardCenterY-beardYMinus, null);
	    		
	    	}
	    	if(isspotsEnabled)
	    	{

				Log.d(TAG, "drawMask: Spot");
	    		int spotsCenterX,spotsCenterY;
		    	float spotsXMinus = (50*spotsMask.getWidth())/100;
		    	float spotsYMinus = (10*spotsMask.getHeight())/100;
		    	spotsCenterX = f.mCircles.get(6).centerX;
		    	spotsCenterY = f.mCircles.get(6).centerY;
		    	canv.drawBitmap(spotsMask, spotsCenterX-spotsXMinus, spotsCenterY-spotsYMinus, null);
	    	}
	    	if(isHairEnabled)
	    	{
				Log.d(TAG, "drawMask: Hair");
	    		int hairCenterX,hairCenterY;
		    	float hairXMinus = (50*hairMask.getWidth())/100;
		    	float hairYMinus = (28*hairMask.getHeight())/100;
		    	hairCenterX = f.mCircles.get(2).centerX;
		    	hairCenterY = f.mCircles.get(2).centerY;
		    	canv.drawBitmap(hairMask, hairCenterX-hairXMinus, hairCenterY-hairYMinus, null);
	    	}
    	}
    }
    
    /**
     * Clears all CircleArea - pointer id relations
     */
    private void clearCirclePointer() {
        Log.w(TAG, "clearCirclePointer");

        mCirclePointer.clear();
    }

    
    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        boolean handled = false;
       
	        CircleArea touchedCircle;
	        int xTouch;
	        int yTouch;
	        int pointerId;
	        int actionIndex = event.getActionIndex();
	
	        // get touch event coordinates and make transparent circle from it
	        switch (event.getActionMasked()) {
	            case MotionEvent.ACTION_DOWN:
	                // it's the first pointer, so clear all existing pointers data
	                clearCirclePointer();
	
	                xTouch = (int) event.getX(0);
	                yTouch = (int) event.getY(0);
	
	                // check if we've touched inside some circle
	                touchedCircle = obtainTouchedCircle(xTouch, yTouch);
	                if(touchedCircle !=null){
		                touchedCircle.centerX = xTouch;
		                touchedCircle.centerY = yTouch;
		                mCirclePointer.put(event.getPointerId(0), touchedCircle);
		
		                invalidate();
		                handled = true;
	                }
	                break;
	
	            case MotionEvent.ACTION_POINTER_DOWN:
	                Log.w(TAG, "Pointer down");
	                // It secondary pointers, so obtain their ids and check circles
	                pointerId = event.getPointerId(actionIndex);
	
	                xTouch = (int) event.getX(actionIndex);
	                yTouch = (int) event.getY(actionIndex);
	
	                // check if we've touched inside some circle
	                touchedCircle = obtainTouchedCircle(xTouch, yTouch);
	                if(touchedCircle !=null){
		                mCirclePointer.put(pointerId, touchedCircle);
		                touchedCircle.centerX = xTouch;
		                touchedCircle.centerY = yTouch;
		                invalidate();
		                handled = true;
	                }
	                break;
	
	            case MotionEvent.ACTION_MOVE:
	                final int pointerCount = event.getPointerCount();
	
	                Log.w(TAG, "Move");
	
	                for (actionIndex = 0; actionIndex < pointerCount; actionIndex++) {
	                    // Some pointer has moved, search it by pointer id
	                    pointerId = event.getPointerId(actionIndex);

						Log.d(TAG, "onTouchEvent: Pointer Id is"+pointerId);
	
	                    xTouch = (int) event.getX(actionIndex);
	                    yTouch = (int) event.getY(actionIndex);
	
	                    touchedCircle = mCirclePointer.get(pointerId);
	
	                    if (null != touchedCircle) {
	                        touchedCircle.centerX = xTouch;
	                        touchedCircle.centerY = yTouch;
	                    }
	                }
	                invalidate();
	                handled = true;
	                break;
	
	            case MotionEvent.ACTION_UP:
	                clearCirclePointer();
	                invalidate();
	                handled = true;
	                break;
	
	            case MotionEvent.ACTION_POINTER_UP:
	                // not general pointer was up
	                pointerId = event.getPointerId(actionIndex);
	
	                mCirclePointer.remove(pointerId);
	                invalidate();
	                handled = true;
	                break;
	
	            case MotionEvent.ACTION_CANCEL:
	                handled = true;
	                break;
	
	            default:
	                // do nothing
	                break;
	        }
        
        return super.onTouchEvent(event) || handled;
    }


    /**
     * Search and creates new (if needed) circle based on touch area
     *
     * @param xTouch int x of touch
     * @param yTouch int y of touch
     *
     * @return obtained {@link CircleArea}
     */
    private CircleArea obtainTouchedCircle(final int xTouch, final int yTouch) {
        CircleArea touchedCircle = getTouchedCircle(xTouch, yTouch);

//        if (null == touchedCircle) {
//            touchedCircle = new CircleArea(xTouch, yTouch, mRadiusGenerator.nextInt(RADIUS_LIMIT) + RADIUS_LIMIT);
//
//            if (mCircles.size() == CIRCLES_LIMIT) {
//                Log.w(TAG, "Clear all circles, size is " + mCircles.size());
//                // remove first circle
//                mCircles.clear();
//            }
//
//            Log.w(TAG, "Added circle " + touchedCircle);
//            mCircles.add(touchedCircle);
//        }

        return touchedCircle;
    }

    /**
     * Determines touched circle
     *
     * @param xTouch int x touch coordinate
     * @param yTouch int y touch coordinate
     *
     * @return {@link CircleArea} touched circle or null if no circle has been touched
     */
    private CircleArea getTouchedCircle(final int xTouch, final int yTouch) {
        CircleArea touched = null;
        for(Face f : faces)
        {
        int i=0;
        for (CircleArea circle : f.mCircles) {
            if ((circle.centerX - xTouch) * (circle.centerX - xTouch) + (circle.centerY - yTouch) * (circle.centerY - yTouch) <= circle.radius * circle.radius) {
                touched = circle;
                if(isSetMaskEnabled)
                	if(i==2 || i==3||i==4||i==6)//2:hair 3:glasses 5:beard 6:dots
                	{
                		
                	}
                	else
                	{
                		touched =null;
                	}
                break;
            }
            i++;
        }
        }
        if ((colorPicker.centerX - xTouch) * (colorPicker.centerX - xTouch) + (colorPicker.centerY - yTouch) * (colorPicker.centerY - yTouch) <= colorPicker.radius * colorPicker.radius)
        {
			Log.d(TAG, "getTouchedCircle: Color Picker");
			touched = colorPicker;
        }

        return touched;
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mMeasuredRect = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }
}