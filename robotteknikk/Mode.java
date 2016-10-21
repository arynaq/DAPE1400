/**
 * This class does nothing currently. 
 *
 * Just acts as a baseclass so assignment of mode is easy
 *
 *
 *
 * */


public abstract class Mode {

	private String state;
	private Tool tool;
	private Data data;
	private TCPController tcp;

	public Mode(String state, Tool tool, Data data, TCPController tcp){
		this.state = state;
		this.tool  = tool;
		this.data  = data;
		this.tcp = tcp;
	}

	public void addPoint(double x, double y){
		this.data.addPoint(x,y);
	}

	public void sendTCP(){
		tcp.send(state,tool,data);
	}

	public String getState(){
		return state;
	}

	public void setTool(Tool tool){
		this.tool = tool;
	}

	public void setData(Data data){
		this.data = data;
	}
	
	public void setTCPController(TCPController tcp){
		this.tcp = tcp;
	}


}
