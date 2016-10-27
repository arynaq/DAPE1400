public class Tool {
	private String color;
	private String style;
	private String resolution;




	public Tool(String color, String style, String resolution){
		this.color = color;
		this.style = style;
		this.resolution = resolution;
	}

	public Tool(){
		this.color = "black";
		this.style = "dashed";
		this.resolution = "default";
	}

	public String toJSON(){
		StringBuffer buffer = new StringBuffer();

		buffer.append("{");

		buffer.append("Color: "+color);
		buffer.append(",");
		buffer.append("Style: "+style);
		buffer.append(",");
		buffer.append("Resolution: "+resolution);

		buffer.append("}");

		return buffer.toString();
	}
}
