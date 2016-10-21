import java.util.List;
import java.util.ArrayList;
import javafx.scene.shape.Path;
import javafx.scene.canvas.Canvas;
import javafx.geometry.Point2D;

public class ImmediateMode {



	/**
	 * This is the main controllre class for immediate mode
	 * 
	 *
	 * */

	private String state; 
	private Tool tool;
	private Data data;
	private TCPController tcp;


	public ImmediateMode(){
		this.state = "Immediate";
		this.tool = null;
		this.data = new Data();
	}


	public void addPoint(double x, double y){
		this.data.addPoint(x,y);
	}

	public void sendTCP(){
		tcp.send(state, tool, data);
	}



}
