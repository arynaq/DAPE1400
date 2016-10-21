import java.util.List;
import java.util.ArrayList;
import javafx.scene.shape.Path;
import javafx.scene.canvas.Canvas;
import javafx.geometry.Point2D;

public class ImmediateMode extends Mode{

	/**
	 * This is the main controller class for immediate mode
	 * 
	 *
	 * */

	public ImmediateMode(TCPController tcp){
		super("Immediate", new Tool(), new Data(), tcp);
	}


}
