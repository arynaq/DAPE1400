import java.util.HashMap;
import java.util.Map;

public class Tool {
	private String color;
	private String style;
	private String resolution;
	private static final Map<String, Integer> colorToInt = create();

	

	private static Map<String, Integer> create(){
		Map<String,Integer> map = new HashMap<String, Integer>();

		map.put("black", 0);
		map.put("red", 1);
		map.put("green", 2);
		map.put("blue", 3);
		return map;
	}



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

		int colorValue = colorToInt.get(color);

		buffer.append(colorValue);

		/**
			buffer.append("{");
			buffer.append("Color:"+color);
			buffer.append(",");
			buffer.append("Style:"+style);
			buffer.append(",");
			buffer.append("Resolution:"+resolution);
			buffer.append("}");
		**/

		return buffer.toString();
	}

	public void setColor(String color){
		this.color = color;
	}

}
