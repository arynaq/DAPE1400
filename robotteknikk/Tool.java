public class Tool {
	private String color;
	private String style;
	private String resolution;


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
