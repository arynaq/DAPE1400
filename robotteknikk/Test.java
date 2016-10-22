public class Test {

	public static void main(String[] args){
		TCPController tcp = new TCPController("test", 1234);
		tcp.connect();
	}

}
