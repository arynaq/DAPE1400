import java.util.concurrent.*;

public class Test {


	public static class Inner implements Runnable{
		private int field = 10;

		@override
		public void run(){
			System.out.println("Field: "+field);
			field++;
		}

	}


	public static void main(String[] args){
		
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
		exec.scheduleAtFixedRate(new Inner()



		/**
		 ImmediateTCPController tcp  = new ImmediateTCPController("localhost", 27000);
		Mode m = new ImmediateMode(tcp);

		tcp.setContainer((ImmediateMode) m).connect();

		tcp.startSendingPeriodic(5000);

		m.addPoint(3,3);
		m.addPoint(3,4);

		m.addPoint(3,3);
		m.addPoint(3,3);
		m.addPoint(3,3);
		m.addPoint(3,3);
		m.addPoint(3,3);
		m.addPoint(3,3);
		m.addPoint(3,3);
		m.addPoint(3,4);
		m.addPoint(3,4);
		m.addPoint(3,4);
		m.addPoint(3,4);
		m.addPoint(3,4);
		m.addPoint(3,4);
		m.addPoint(3,4);
		**/
	}

}
