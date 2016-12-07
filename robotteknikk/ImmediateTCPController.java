import java.util.concurrent.*;
import java.util.Date;




/***
 * This controller should send data to the server
 * at intervals of N milliseconds. 
 *
 * Runs in its own seperate thread, called by a scheduled task
 * that calls its send method. 
 *
 *
 * @TODO: 
 * 		Potential problem when N is low and dataset is large.
 * 		This will cause duplicate sends of data. See below
 *
 * 			N = small:
 * 				
 * 				Send -> | ...................................|
 *
 * 						| .......................*...........|
 *
 * 				Send ->
 * 						Will start sending data from * but there is already
 * 						a call to send data from *
 *
 *
 * 			Solution:
 * 					
 * 					Test, keep N small
 *
 *
 *
 *
 *
 * */


public class ImmediateTCPController extends TCPController implements Runnable{

	private ImmediateMode container;
	private boolean sending;



	/**
	 * Calls the constructor of TCPController. See that
	 *
	 * */ 

	public ImmediateTCPController(String hostname, int port){
		super(hostname, port);
	}


	/* 
	 * Sends the controller is sending state, data and tool it needs to have access to these
	 *
	 * Supplied via a "container" class 
	 *
	 * */ 
	public ImmediateTCPController setContainer(ImmediateMode container) {
		this.container = container;
		return this;
	}


	/**
	 * Since the class implements runnable it can be (and should be) started in a seperate thread
	 * as to not block the thread that supplies it data via container.
	 * */ 

	@Override
	public void run(){
		sending = true;
		super.send(container.getState(), container.getTool(), container.getData());
	}


	/*
	 * We dont want to send data if we are aleady sending..
	 *
	 * */ 
	@Override
	public void send(String state, Tool tool, Data data){
		if(sending)
			return;
		super.send(state,tool,data);
	}



	/**
	 * Starts a scheduled thread that will call the run() method in this class every periodMillis
	 *
	 * */ 
	public void startSendingPeriodic(int periodMillis){
		if(sending){
			System.out.println("Already sending....");
			return;
		}
		

		ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
		exec.scheduleAtFixedRate(this, periodMillis, periodMillis, TimeUnit.MILLISECONDS);
	}





	

}


