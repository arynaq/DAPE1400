import javafx.stage.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SimpleGui extends Application {

	@Override
	public void start(Stage stage){
		GridPane root = new GridPane();
		Button knapp1 = new Button("KNAPP 1");
		Button knapp2 = new Button("KNAPP 2");
		Button knapp3 = new Button("KNAPP 3");
		Button knapp4 = new Button("KNAPP 4");
		Button knapp5 = new Button("KNAPP 5");
		Button knapp6 = new Button("KNAPP 6");


		Scene scene = new Scene(root, 200, 200);

		root.add(knapp1, 1,1);
		root.add(knapp2, 2,1);
		root.add(knapp3, 3,1);
		root.add(knapp4, 3,2);
		root.add(knapp5, 1,2,2,2);
		root.add(knapp6, 3,3);

		stage.setScene(scene);
		stage.show();

	}



	public static void main(String... args) {
		Application.launch(args);
	}

}
