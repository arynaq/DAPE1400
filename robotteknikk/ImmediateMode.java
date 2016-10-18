import java.util.List;
import java.util.ArrayList;
import javafx.scene.shape.Path;
import javafx.scene.canvas.Canvas;
import javafx.geometry.Point2D;

public class ImmediateMode {

	private Tool tool;
	private TCPController tcp;
	private DataPacket datapacket;


	public void setTool(Tool tool){
		this.tool = tool;
	}

	public void addpoint(double x, double y) {
		datapacket.getData().addPoint(x,y);
	}

	public void flushToTCP(DataPacket datapacket){
		tcp.send(datapacket);
	}

}
