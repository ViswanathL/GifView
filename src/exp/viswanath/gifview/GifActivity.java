package exp.viswanath.gifview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GifActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gif);
		final LoadingView gifView = (LoadingView) findViewById(R.id.loadingView);
		
		Button pause = (Button) findViewById(R.id.pause);
		pause.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				gifView.setPaused(true);
			}
		});
		
		Button resume = (Button) findViewById(R.id.resume);
		resume.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				gifView.setPaused(false);
			}
		});
	}
}
