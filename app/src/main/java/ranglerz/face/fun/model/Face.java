package ranglerz.face.fun.model;

import java.util.ArrayList;

import android.graphics.PointF;

public class Face {
	public ArrayList<CircleArea> mCircles = new ArrayList<CircleArea>();//0:right eye
	public Face()
	{
		mCircles.add(new CircleArea(0,0, 50));//0:right eye
        mCircles.add(new CircleArea(0,0, 50));//1:left eye
        mCircles.add(new CircleArea(0,0, 150));//2:hair
        mCircles.add(new CircleArea(0,0, 150));//3:glasses
        mCircles.add(new CircleArea(0,0, 100));//4:chin
        mCircles.add(new CircleArea(0,0, 50));//5:ForeHead
        mCircles.add(new CircleArea(0,0, 100));//6:dots

	}
	public void setCirclePosition(int index,PointF p)
    {
    	mCircles.get(index).centerX =(int) p.x;
    	mCircles.get(index).centerY =(int) p.y;
    }
}
