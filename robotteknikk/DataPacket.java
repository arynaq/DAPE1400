public class DataPacket {

	private String state;
	private Tool tool;
	private Data data;


	public DataPacket(String state, Tool tool, Data data) {
		this.state = state;
		this.tool = tool;
		this.data = data;
	}


	public void setState(String state){
		this.state = state;
	}

	public void setTool(Tool tool){
		this.tool = tool;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Data getData(){
		return this.data;
	}


	public String toJSON(){
		return null;

	}

}
