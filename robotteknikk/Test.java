import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

public class Test {



	public static void main(String[] args){
		


		 ImmediateTCPController tcp  = new ImmediateTCPController("localhost", 27000);
		final Mode m = new ImmediateMode(tcp);

		tcp.setContainer((ImmediateMode) m).connect();

		tcp.startSendingPeriodic(50);

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

		Runnable r = new Runnable(){
			private int i;
			private int j;
			@Override
			public void run(){
				m.addPoint(i++, j++);
			}

		};

		ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
		exec.scheduleAtFixedRate(r, 100, 100, TimeUnit.MILLISECONDS);

		
	}
}
