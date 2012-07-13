package info.disorient.acw;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class DisorientACWActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		//Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		
		
		//

		// connect to UDP server
//		try {
//			connectToServer();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			toastNote(e.toString());
//		}

		// update status using toasts
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		toastNote("Disorient v0.1");
	}

	private void toastNote(String msg) {
		Context context = getApplicationContext();
		CharSequence text = msg;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();

	}

//	private void connectToServer() throws UnknownHostException, SocketException {
//		// TODO Auto-generated method stub
//
//		Log.d("UDPConnect", "connectToServer( )");
//		new UDPConnect(0, 16).execute();
//
//	}

}