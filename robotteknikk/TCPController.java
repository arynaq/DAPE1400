import java.util.List;
import java.util.ArrayList;
import java.net.*;
import java.io.*;



public class TCPController{
	private int runningIndex  = 0;
	private int maxDataSize = 5;
	private int port;
	private int callCount = 0;

	private String hostname;
	private Socket socket;
	private DataInputStream in;
	private PrintWriter out;

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
			out = new PrintWriter(socket.getOutputStream(), true);
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
		
		List<DataPoint> dataPoints = data.asList();
		List<DataPoint> dataCopy = new ArrayList<DataPoint>(dataPoints);
		List<DataPoint> toSend = dataCopy.subList(runningIndex, dataCopy.size());
		runningIndex = dataPoints.size();
		writeToSocket(state, tool, new Data(toSend));

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
			toSend = asList.subList(0, maxDataSize);
			remainder = asList.subList(maxDataSize, N);

			out.println(new DataPacket(state, tool, data).toJSON());
			writeToSocket(state,tool, new Data(remainder));
		}
		else {
			out.println(new DataPacket(state,tool,data).toJSON());
			System.out.println("Sent!");
		}


	}
}
