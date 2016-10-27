import java.util.List;
import java.util.ArrayList;
import java.net.*;
import java.io.*;



public class TCPController{
	private int runningIndex  = 0;
	private int maxDataSize = 50;
	private int port;
	private int callCount = 0;

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
		System.out.println("Setting up socket..");
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
		System.out.println(this+ " sendcount: "+callCount++);
		System.out.println("Runningindex before send: " + runningIndex);
		
		List<DataPoint> dataPoints = data.asList();
		List<DataPoint> dataCopy = new ArrayList<DataPoint>(dataPoints);
		List<DataPoint> toSend = dataCopy.subList(runningIndex, dataCopy.size());
		runningIndex = dataPoints.size();
		writeToSocket(state, tool, new Data(toSend));

		System.out.println("Runningindex after send: " + runningIndex);
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
		System.out.println("In write to socket...");
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
				out.writeChars(new DataPacket(state, tool, data).toJSON());
			} catch(IOException e) {
				System.out.println("Failed to write datapacket");
			}
			writeToSocket(state,tool, new Data(remainder));
		}
		// if it actually fits, send now!
		else {
			System.out.println("In directsend..");
			try {
				out.writeUTF(new DataPacket(state,tool, data).toJSON());
			}catch(IOException e){
				System.out.println("Failed to write datapacket");
			}
			System.out.println("Sent!");
		}



	}
}
