import java.util.List;
import java.util.ArrayList;

public class Data {


	/**
	 *  This class acts as a container for datapoints.
	 *
	 *  Exposes it backing storage by the functions asList()
	 *  and addPoint()
	 *
	 *
	 *
	 *
	 * */

	private List<DataPoint> data = new ArrayList<>();
	private static final String empty = "[]";

	public void addPoint(double x, double y) {
		data.add(new DataPoint(x,y));
	}


	/**
	 * Adds a new datapoint to the backing datalist
	 *
	 * */
	public void addPoint(double x, double y, double z){
		data.add(new DataPoint(x,y,z));
	}

	/**
	 * Copy constructor
	 * Creats a new Data object by using the provided data as its own datalist
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

	/**
	 * Returns the list of datapoint.
	 * */

	public List<DataPoint> asList(){
		return data;
	}


	/**
	 * Returns the number of datapoints saved in this objets data list
	 *
	 * */

	public int getDataSize(){
		return data.size();
	}



	/*
	 *  Returns a JSON file containing an arryay of every point converted to JSON.
	 *
	 *  Suppose the list has 2 dataponts that look like this: (1,2,3), (4,5,6)
	 *
	 *  This method will then return
	 *
	 *  [{1,2,3}, {4,5,6}]
	 *
	 *
	 * */ 
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
