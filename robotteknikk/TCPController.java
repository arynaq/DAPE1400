import java.util.List;
import java.net.*;
import java.io.*;


public class TCPController implements Runnable{
	private int runningIndex  = 0;
	private static final int maxDataSize = 100;
	private String hostname;
	private int port;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;

	public TCPController(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	private void setupSocket(){
		try {
			socket = new Socket(hostname, port);
			out = new DataOutputStream(socket.getOutputStream());;
			in = new DataInputStream(socket.getInputStream());

		}catch(IOException e){
			System.out.println("Failed at initiating socket");
		}
	}


	/***
	 * This does NOTHING as of yet.
	 * The idea is to get this to run in a scheduled periodic class
	 *
	 *
	 * */
	@Override
	public void run(){

	}



	/**
	 * This method is the main method to be called from external controllers.
	 *
	 * The method works under the assumption that the list of datapoints from the Data
	 * object is continiously growing (and only refreshed in the sense that it is extended
	 * in size when a new datapoint is added). 
	 *
	 * Keeps a running inded of what it has already sent so as to not send duplicates.
	 *
	 * 
	 *
	 * @param state The state variable to write in datapacket to server 
	 * @param tool  The tool variable to write in datapacket to server.
	 * @param data  The set of datapoints encapsulated in data object to be sent.
	 *
	 */
	public void send(String state, Tool tool, Data data){
		List<DataPoint> asList = data.asList();
		
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
			// Send mxaDataSize datapoints first.
			toSend = asList.subList(0, maxDataSize);
			remainder = asList.subList(maxDataSize, N);

			

			out.writeUTF(new DataPacket(state, tool, data).toJSON());
			writeToSocket(state,tool, new Data(remainder));
		}

	}


}
