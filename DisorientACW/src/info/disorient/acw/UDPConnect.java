package info.disorient.acw;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class UDPConnect extends AsyncTask {

	private Context mContext;
	private int toX;
	private int toY;

	public UDPConnect(int x, int y) {
		// TODO Auto-generated constructor stub
		toX = x;
		toY = y;
	}

	@Override
	protected Object doInBackground(Object... params) {
		String messageStr = ":" + toX + "," + toY;

		String address = "192.168.1.2";
		int server_port = 11870;

		Log.d("UDPConnect", "Connecting to " + address + ":" + server_port);

		DatagramSocket s = null;

		try {
			s = new DatagramSocket();
		} catch (SocketException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		InetAddress local = null;

		try {
			local = InetAddress.getByName("192.168.1.2");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Log.i("InetAddress", local.toString());

		int msg_length = messageStr.length();
		byte[] message = messageStr.getBytes();
		message[0] = 1;
		DatagramPacket p = new DatagramPacket(message, msg_length, local,
				server_port);

		try {
			s.send(p);
			Log.d("UDPConnect", "data sent: " + p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("UDPConnect", e.toString());
		}


		return null;

	}

	// InetAddress getBroadcastAddress() throws IOException {
	// WifiManager wifi = (WifiManager)
	// mContext.getSystemService(Context.WIFI_SERVICE);
	//
	// List<WifiConfiguration> wifiConfigurations =
	// wifi.getConfiguredNetworks();
	//
	// if (wifiConfigurations!=null) for (WifiConfiguration result :
	// wifiConfigurations) {
	// if (result!=null) Log.i("WifiConfiguration", result.toString());
	//
	// }
	// else Log.i("WifiConfiguration", "none");
	//
	//
	// DhcpInfo dhcp = wifi.getDhcpInfo();
	// // handle null somehow
	//
	// Log.i("getBroadcastAddress()", dhcp.toString());
	//
	// int broadcast = (dhcp.ipAddress & dhcp.netmask) | ~dhcp.netmask;
	// byte[] quads = new byte[4];
	// for (int k = 0; k < 4; k++)
	// quads[k] = (byte) ((broadcast >> k * 8) & 0xFF);
	// return InetAddress.getByAddress(quads);
	// }

}
