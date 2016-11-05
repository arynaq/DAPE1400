import java.util.List;
import java.util.ArrayList;
import java.net.*;
import java.io.*;


/**
 * Where the TCP magic happens
 *
 * Should be started by first supplying hostname and port by its constructor
 *
 * Then calling its connect() method.
 *
 * */ 

public class TCPController{

	/*
	 * Keeps a running index of the data it has sent from the data list so it doesn't send
	 * duplicate data if a new send is requested with the same data object
	 *
	 * (And in current implementation only the same data object (which can grow with points!)
	 * is used.
	 * */ 
	private int runningIndex  = 0;

	/* 
	 * The maximum number of datapoints we can send. The ABB robot is limited to a buffer of 1024 bytes
	 *
	 * */
	private int maxDataSize = 80;
	private int port;
	private int callCount = 0;

	private String hostname;
	private Socket socket;
	
	/**
	 * Input and output streams for socket 
	 * in reads from server
	 * out writes to server
	 * */
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
		if(!isConnected())
			setupSocket();
	}

	public boolean isConnected(){
		if (socket == null) return false;
		return socket.isConnected();
	}


	/**
	 * Internal helper method, sets up a socket and sets up read/writes streams to the socket.
	 *
	 * Disables Nagles algorithm (where data is NOT sent as soon as possible).
	 *
	 *
	 * */ 
	private void setupSocket(){
		System.out.println("Setting up socket..");
		try {
			socket = new Socket(hostname, port);
			out = new PrintWriter(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			socket.setTcpNoDelay(true);
			connected = true;
		}catch(IOException e){
			System.out.println("Failed at initiating socket");
			new AlertBox(e);
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
		List<DataPoint> asList = data.asList();
		List<DataPoint> toSend = null;
		List<DataPoint> remainder = null;
		int N = asList.size();

		if(N> maxDataSize) {
			toSend = asList.subList(0, maxDataSize);
			remainder = asList.subList(maxDataSize, N);

			String packetAsJSON = new DataPacket(state,tool,new Data(toSend)).toJSON();

			out.println(packetAsJSON);
			out.flush();
			System.out.println("Sent: ");
			System.out.println(packetAsJSON);


			writeToSocket(state,tool, new Data(remainder));
		}
		else {
			String packetAsJSON = new DataPacket(state,tool,data).toJSON();			

			out.println(packetAsJSON);
			out.flush();

			System.out.println("Sent: ");
			System.out.println(packetAsJSON);
		}


	}
}
