/**
 * This class does nothing currently. 
 *
 * Just acts as a baseclass so assignment of mode is easy
 *
 *
 *
 * */


public abstract class Mode {
	
	/* This class acts as a controller for the drawing application, supplies an interface
	 * for changing the application state via set and get methods on the contained data, tool, state
	 * and tcpcontroller.
	 * */


	private String state;
	private Tool tool;
	private Data data;
	private TCPController tcp;
	private float xMaxCanvas = 800;
	private float xMaxRobot = 200;
	private float yMaxCanvas = 800;
	private float yMaxRobot = 220;

	private float xScale = xMaxRobot/xMaxCanvas;
	private float yScale = yMaxRobot/yMaxCanvas;

	public Mode(String state, Tool tool, Data data, TCPController tcp){
		this.state = state;
		this.tool  = tool;
		this.data  = data;
		this.tcp = tcp;
	}

	public void addPoint(double x, double y){
		int scaledX = (int)(x * xScale);
		int scaledY = (int)(y * yScale); 
		
		this.data.addPoint(scaledX,scaledY);
	}

	public void addPoint(double x, double y, double z) {
		int scaledX = (int)(x * xScale);
		int scaledY = (int)(y * yScale); 
		
		this.data.addPoint(scaledX,scaledY, z);
	}

	public void sendTCP(){
		tcp.send(state,tool,data);
	}

	public String getState(){
		return state;
	}

	public void setState(String state){
		this.state = state;
	}

	public void setTool(Tool tool){
		this.tool = tool;
	}

	public Tool getTool(){
		return this.tool;
	}

	public void setData(Data data){
		this.data = data;
	}
	
	public void setTCPController(TCPController tcp){
		this.tcp = tcp;
	}

	public Data getData(){
		return this.data;
	}


}
