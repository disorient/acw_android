package info.disorient.acw;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;

public class TouchView extends View implements OnTouchListener {
	private static final String TAG = "TouchView";

	List<Point> points = new ArrayList<Point>();
	Paint paint = new Paint();

	private int height;

	private int width;

	public TouchView(Context context, AttributeSet attr) {
		super(context, attr);
		setFocusable(true);
		setFocusableInTouchMode(true);

		this.setBackgroundColor(0xffff1493);	
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		width = display.getHeight();
		height = display.getWidth();
		
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);

		height = metrics.heightPixels;
		width = metrics.widthPixels;

		this.setOnTouchListener(this);
		
		paint.setColor(0xccff8c00);
		paint.setAntiAlias(true);
	}

	@Override
	public void onDraw(Canvas canvas) {
		for (Point point : points) {
			canvas.drawCircle(point.x, point.y, 16, paint);
			// Log.d(TAG, "Painting: "+point);
		}
	}

	public boolean onTouch(View view, MotionEvent event) {
		// if(event.getAction() != MotionEvent.ACTION_DOWN)
		// return super.onTouchEvent(event);
		
		

		Point point = new Point();
		
		int stripWidth = (int) this.width/16;
		int stripHeight = (int) this.height/16;

		int nodex = (int) (16 * event.getX() / this.width);
		int nodey = (int) (16 * event.getY() / this.height);
		
		point.x = nodex * stripWidth;
		point.y = nodey * stripHeight;

		points.add(point);
		invalidate();
		Log.d(TAG, "point: " + point);
		
		new UDPConnect(nodex,nodey).execute();
		return true;
	}
}

class Point {
	float x, y;

	@Override
	public String toString() {
		return x + "," + y;
	}
	

}