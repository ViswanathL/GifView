package exp.viswanath.gifview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Viswanath L 
 *
 * GifView : exp.viswanath.gifview
 *
 * Feb 22, 2014
 **/
public class LoadingView extends View {

	// Paint object
	private Paint paint;

	// Images to be drawn
	private Bitmap image1;
	private Bitmap image2;
	private Bitmap image3;

	// Width of the view
	private int width;
	// Height of the view
	private int height;

	// Integer that is used to identify the image to be drawn
	private int imageNumber = 1;

	// Handler used to provide delay
	private Handler handler;

	private Runnable r;
	
	// Used to pause animation
	private boolean isPaused;

	public LoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);

		image1 = BitmapFactory.decodeResource(getResources(), R.drawable.img1);
		image2 = BitmapFactory.decodeResource(getResources(), R.drawable.img2);
		image3 = BitmapFactory.decodeResource(getResources(), R.drawable.img3);
		paint = new Paint();
		handler = new Handler();

		width = image1.getWidth();
		height = image1.getHeight();
	}

	@Override
	protected void onDraw(final Canvas canvas) {
		super.onDraw(canvas);	
		drawBitmap(canvas);
	}

	private void drawBitmap(final Canvas canvas) {
		canvas.drawBitmap(getBitmap(), 0, 0, paint);

		r = new Runnable() {			
			@Override
			public void run() {
				LoadingView.this.invalidate();
			}
		};
		if(!isPaused)
			handler.postDelayed(r, getDelay());
	}

	/**
	 * Always wrap_content, If you need to change change here
	 */
	@Override
	protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec){
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		this.setMeasuredDimension(width, height);
	}

	/**
	 * This method selects the next bitmap to be drawn
	 * @return bitmap to draw next
	 */
	private Bitmap getBitmap()
	{
		final int IMAGE1 = 1;
		final int IMAGE2 = 2;
		final int IMAGE3 = 3;

		Bitmap bmp = null;
		switch(imageNumber)
		{
		case IMAGE1:
			bmp = image1;
			break;
		case IMAGE2:
			bmp = image2;
			break;
		case IMAGE3:
			bmp = image3;
			break;
		}
		++imageNumber;

		if(imageNumber > IMAGE3)
			imageNumber = IMAGE1;

		return bmp;
	}

	/**
	 * This method identifies the delay to draw the next bitmap
	 * @return delay
	 */
	private int getDelay()
	{
		int SMALL_DELAY = 500;
		int LONG_DELAY = 1500;

		int delay = SMALL_DELAY;
		if(imageNumber == 1)
			delay = LONG_DELAY;

		return delay;
	}

	/**
	 * Pause the animation
	 * @param isPaused
	 */
	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
		if(!isPaused)
			this.invalidate();
		else
			handler.removeCallbacks(r);
	}
	
}
