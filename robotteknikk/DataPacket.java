public class DataPacket {

	/**
	 * A convinience class for storing and retrieving the current state, tool and data
	 *
	 * Very convinient for sending data over TCP by the use of its toJSON method.
	 *
	 **/ 


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


	/**
	 * This will produce the following example JSON file
	 *
	 * {
	 * 	State: <String>,
	 * 	Tool:<Object>
	 * 		{
	 * 			Color : <String>, 
	 * 			Style : <String>,
	 * 			Resolution: <String>
	 * 		},
	 * 	Data: <array>
	 * 		[{x,y},
	 * 		 {x,y},
	 * 		 ....
	 * 		 ]
	 * }
	 * 	
	 * By calling the .toJSON() method of each object.
	 *
	 *
	 * @return Returns the datapacket outlined above.
	 * */


	public String toJSON(){
		StringBuffer buffer = new StringBuffer();

		buffer.append("{");
		/**
		 *
		 * The section below is commented out as currently we only have 1 state...
			buffer.append("State:" + state);
			buffer.append(",");
		**/
		buffer.append("Tool:");
		buffer.append(tool.toJSON());
		buffer.append(",");

		buffer.append("Data:");
		buffer.append(data.toJSON());
		
		buffer.append("}");
		return buffer.toString();
	}

}
