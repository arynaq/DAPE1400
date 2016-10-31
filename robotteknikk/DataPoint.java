import java.util.List;
import java.util.ArrayList;
import javafx.geometry.Point3D;

public class DataPoint {

	private Point3D point;

	public DataPoint(double x, double y){
		point = new Point3D(x,y,0);
	}

	public DataPoint(double x, double y, double z){
		point = new Point3D(x,y,z);
	}

	public String toJSON(){
		StringBuffer buffer = new StringBuffer();

		buffer.append("{");

		buffer.append((int)point.getX());
		buffer.append(",");
		buffer.append((int)point.getY());
		buffer.append(",");
		buffer.append((int)point.getZ());

		buffer.append("}");

		return buffer.toString();
	}

}
