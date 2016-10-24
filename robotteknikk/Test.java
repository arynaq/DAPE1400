public class Test {

	public static void main(String[] args){
		ImmediateTCPController tcp = new ImmediateTCPController("localhost", 1234);
		tcp.startSendingPeriodic(100);
	}
}
