import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.*;

public class AlertBox{



	/**
	 * A class for displaying errors as standalone windows. 
	 *
	 *
	 *
	 * Is currently unused
	 *
	 *
	 * */
 
	private Alert alert;
	private StringWriter sw;
	private PrintWriter pw;
	private String exceptionString;
	private GridPane pane;
	private Label label;
	private TextArea textArea;
	private String labelText;


	public AlertBox(Exception e){
		this.alert = new Alert(AlertType.ERROR);
		System.out.println(alert.getModality());
		this.sw = new StringWriter();
		this.pw = new PrintWriter(sw);
		this.label = new Label("Exception stacktrace");

		setupAndShow(e);

	}

	public AlertBox(Exception e, String text){
		this(e);
		this.labelText = text;
	}


	/**
	 * Create new error from text string
	 *
	 * */

	public AlertBox(String error){
		this(new Exception(error));
	}


	/**
	 * Sets up the displayed text in the error box..
	 *
	 * @TODO: Can probably find better descriptions.
	 *
	 * */

	private void setupAndShow(Exception e){

		alert.setTitle("WARNING/ERROR");
		alert.setHeaderText("An error has occured, please check details.");
		alert.setContentText("AN ERROR OR WARNING HAS BEEN GENERATED");

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
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0 );
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);
		alert.show();
	}


}
