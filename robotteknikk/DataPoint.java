import java.util.List;
import java.util.ArrayList;
import javafx.geometry.Point2D;

public class DataPoint {

	private Point2D point;

	public DataPoint(double x, double y){
		point = new Point2D(x,y);
	}

	public String toJSON(){
		StringBuffer buffer = new StringBuffer();

		buffer.append("{");

		buffer.append("x: "+ point.getX());
		buffer.append(",");
		buffer.append("y: "+ point.getY());

		buffer.append("}");

		return buffer.toString();
	}

}
