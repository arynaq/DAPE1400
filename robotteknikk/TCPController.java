import java.util.List;
import java.util.ArrayList;
import java.net.*;
import java.io.*;



public class TCPController{
	private int runningIndex  = 0;
	private int maxDataSize = 50;
	private int port;

	private String hostname;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;

	private boolean connected;

	public TCPController(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}
	public void setMaxDataSize(int max){
		this.maxDataSize = max;
	}

	public void connect(){
		if(connected)
			return;
		setupSocket();
	}

	public boolean isConnected(){
		return connected;
	}

	private void setupSocket(){
		try {
			socket = new Socket(hostname, port);
			out = new DataOutputStream(socket.getOutputStream());;
			in = new DataInputStream(socket.getInputStream());
			connected = true;
		}catch(IOException e){
			System.out.println("Failed at initiating socket");
		}
	}


	/**
	 * This method will take a copy of the data to be sent before sending it over TCP.
	 *
	 * Will try to send it all in one go if the number of datapoints fit in one datapacket
	 * else it will attempt to split up in fitting chunks. 
	 *
	 * @param state The state variable to write in datapacket to server 
	 * @param tool  The tool variable to write in datapacket to server.
	 * @param data  The set of datapoints encapsulated in data object to be sent.
	 *
	 */
	public void send(String state, Tool tool, Data data){
		System.out.println("In super send.");
		List<DataPoint> asList = new ArrayList<DataPoint>(data.asList());
		
		asList = asList.subList(runningIndex, asList.size());
		writeToSocket(state, tool, new Data(asList));

		runningIndex = asList.size();
	}


	/**
	 * This method will recursively attempt to send the whole contents of the
	 * given list of datapoints in chunks of at most maxDataSize
	 *
	 * 
	 *
	 *
	 * */
	private void writeToSocket(String state, Tool tool, Data data){
		List<DataPoint> asList = data.asList();
		List<DataPoint> toSend = null;
		List<DataPoint> remainder = null;
		int N = asList.size();


		if(N> maxDataSize) {
			// Split into a part we can send now and a remainder we can send afterwards
			// this will run recursively untill all data has been sent.
			toSend = asList.subList(0, maxDataSize);
			remainder = asList.subList(maxDataSize, N);

			

			try{
				out.writeUTF(new DataPacket(state, tool, data).toJSON());
			} catch(IOException e) {
				System.out.println("Failed to write datapacket");
			}
			writeToSocket(state,tool, new Data(remainder));
		}
		// if it actually fits, send now!
		else {
			try {
				out.writeUTF(new DataPacket(state,tool, data).toJSON());
			}catch(IOException e){
				System.out.println("Failed to write datapacket");
			}
		}



	}
}
