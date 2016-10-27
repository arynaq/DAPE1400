import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

public class Test {



	public static void main(String[] args){
		


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
	}

}
