import java.util.concurrent.*;




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


	public ImmediateTCPController(String hostname, int port){
		super(hostname, port);
	}

	public ImmediateTCPController setContainer(ImmediateMode container) {
		this.container = container;
		return this;
	}

	@Override
	public void run(){
		System.out.println("HELLO!");
		sending = true;
		System.out.println("Hello!");
		super.send(container.getState(), container.getTool(), container.getData());
	}

	@Override
	public void send(String state, Tool tool, Data data){
		if(sending)
			return;
		super.send(state,tool,data);
	}

	public void startSendingPeriodic(int periodMillis){
		if(sending){
			System.out.println("Already sending....");
			return;
		}
		

		ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
		exec.scheduleAtFixedRate(this, periodMillis, periodMillis, TimeUnit.MILLISECONDS);
	}





	

}


