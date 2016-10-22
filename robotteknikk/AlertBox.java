import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.GridPane;


import java.io.*;

public class AlertBox {

	private Alert alert;
	private StringWriter sw;
	private PrintWriter pw;
	private String exceptionString;
	private GridPane pane;
	private Label label;
	private TextArea textArea;



	public AlertBox(Exception e){
		this.alert = new Alert(AlertType.ERROR);
		this.sw = new StringWriter();
		this.pw = new PrintWriter(sw);
		this.label = new Label("Exception stacktrace");

	}

	public AlertBox(String error){

	}

	private void setupAndShow(Exception e){

		alert.setTitle("Error.");
		alert.setHeaderText("An error occured");
		alert.setContentText("K");

		e.printStackTrace(pw);
		exceptionString = sw.toString();

		textArea = new TextArea(exceptionString);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);

		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent

		
	}


}
