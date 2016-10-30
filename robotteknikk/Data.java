import java.util.List;
import java.util.ArrayList;

public class Data {

	private List<DataPoint> data = new ArrayList<>();
	private static final String empty = "[]";

	public void addPoint(double x, double y) {
		data.add(new DataPoint(x,y));
	}

	public void addPoint(double x, double y, double z){
		data.add(new DataPoint(x,y,z));
	}

	/**
	 * Copy constructor
	 * */
	public Data(List<DataPoint> data){
		this.data = data;
	}

	/**
	 *
	 * Default constructor, does nothing..yet
	 * */
	public Data(){
	}


	public DataPoint getLastDataPoint(){
		return data.get(data.size()- 1);
	}


	public List<DataPoint> asList(){
		return data;
	}


	public int getDataSize(){
		return data.size();
	}

	public String toJSON(){
		if(data.isEmpty())
			return empty;


		StringBuffer buffer = new StringBuffer();
		
		buffer.append("[");
		for(int i=0; i<data.size()-1; i++){
			buffer.append(data.get(i).toJSON());
			buffer.append(",");
		}
		buffer.append(data.get(data.size()-1).toJSON());
		buffer.append("]");


		return buffer.toString();
	}
}
