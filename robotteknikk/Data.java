import java.util.List;
import java.util.ArrayList;

public class Data {

	private List<DataPoint> data = new ArrayList<>();

	public void addPoint(double x, double y) {
		data.add(new DataPoint(x,y));
	}


	public DataPoint getLastDataPoint(){
		return data.get(data.size()- 1);
	}

	public String toJSON(){
		StringBuffer buffer = new StringBuffer();

		buffer.append("[");

		for(int i=0; i<data.size(); i++){
			DataPoint d = data.get(i);
			buffer.append(d.toJSON());
			buffer.append(",");
		}

		buffer.append("]");
		
		return buffer.toString();

	}


	public List<DataPoint> getDataPoints(){
		return data;
	}


	public int getDataSize(){
		return data.size();
	}
}
