/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Leonovo
 */
public class JavaFXApplication1 extends Application {

	//changing active drawig mode
	private ImmediateTCPController tcp;
	private Mode currentMode = new ImmediateMode(tcp);


	private	double startX, startY, stopX, stopY;
	private	double totalX, totalY;
	private	double moveX;
	private	double moveY;
	private	double startMoveX;
	private	double startMoveY;

	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;

	//eraser toggle button for the whole class
	ToggleButton eraser = new ToggleButton("Eraser");

	Button black = new Button("Black");
	Button blue  = new Button("Blue");
	Button red = new Button("Red");
	Button green = new Button("Green");

	Button clearAll = new Button("Clear All");


	@Override
	public void start(Stage primaryStage) {

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});

		black.setId("blackButton");
		blue.setId("blueButton");
		red.setId("redButton");
		green.setId("greenButton");


		StackPane stackPane = new StackPane();
		stackPane.setId("stackPane");

		//creating drawing space
		Canvas workSpace = new Canvas(WIDTH, HEIGHT);
		workSpace.setId("canvas");
		GraphicsContext graphicsContext = workSpace.getGraphicsContext2D();


		//border for working space
		Canvas border_canvas = new Canvas(WIDTH, HEIGHT);
		GraphicsContext graphicsContext_border = border_canvas.getGraphicsContext2D();
		graphicsContext_border.strokeRect(
				0,              //x of the upper left corner
				0,              //y of the upper left corner
				border_canvas.getWidth(),    //width of the rectangle
				border_canvas.getHeight());  //height of the rectangle);


		initDraw(graphicsContext);


		black.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				currentMode.getTool().setColor("black");
				graphicsContext.setStroke(Color.BLACK);
			}
		});

		green.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				currentMode.getTool().setColor("green");
				graphicsContext.setStroke(Color.GREEN);
			}
		});


		blue.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				currentMode.getTool().setColor("blue");
				graphicsContext.setStroke(Color.BLUE);
			}
		});


		red.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				currentMode.getTool().setColor("red");
				graphicsContext.setStroke(Color.RED);
			}
		});

		//Clear all
		clearAll.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				graphicsContext.clearRect(0, 0, workSpace.getWidth(), workSpace.getHeight());
				graphicsContext.strokeRect(
						0,              //x of the upper left corner
						0,              //y of the upper left corner
						workSpace.getWidth(),    //width of the rectangle
						workSpace.getHeight());  //height of the rectangle

			}

		});


		//what happens when mouse pressed
		workSpace.addEventHandler(MouseEvent.MOUSE_PRESSED,
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						graphicsContext.beginPath();
						//reading the position of mouse pointer
						graphicsContext.moveTo(event.getX(), event.getY());
						graphicsContext.stroke();
					}
				});


		//what happens while holding mouse
		workSpace.addEventHandler(MouseEvent.MOUSE_DRAGGED,
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						moveX = event.getX();
						moveY = event.getY();

						if (eraser.isSelected()) {
							graphicsContext.clearRect(event.getX(), event.getY(), 30, 30);
						} else {
							graphicsContext.lineTo(event.getX(), event.getY());
							graphicsContext.stroke();
							Bounds b = workSpace.boundsInLocalProperty().getValue();
							if(b.contains(moveX,moveY))
								currentMode.addPoint(moveX,moveY);
						}


					}
				});

		//what happens when mouse is unpressed on workspace
		workSpace.addEventHandler(MouseEvent.MOUSE_RELEASED,
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						if(!eraser.isSelected()){
							currentMode.addPoint(event.getX(), event.getY(), 1);
						}
					}
				});


		//buttongs for left toolbar
		Button pen = new Button("Pen");


		ToolBar drawBar = new ToolBar();
		drawBar.setId("drawBar");
		drawBar.setOrientation(Orientation.VERTICAL);
		drawBar.getItems().addAll(
				pen,
				eraser,
				clearAll
				);

		ToolBar choiceBar = new ToolBar();
		choiceBar.setId("rightBar");
		choiceBar.getItems().addAll(
				black,
				red,
				blue,
				green
				);


		choiceBar.setOrientation(Orientation.VERTICAL);


		//ip toolbar bottom
		TextField ipField = new TextField("localhost:27000");
		Label ipText = new Label("   Enter IP adress:");
		Button connect = new Button("Connect");
		ToolBar ipBar = new ToolBar();
		ipBar.setId("ipBar");
		ipBar.getItems().addAll(
				ipText,
				ipField,
				//new Separator(),
				connect
				);


		//tcp connect logic
		connect.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(tcp != null && tcp.isConnected()){
					System.out.println("Connection already established");
					return;
				}
				String[] values = ipField.getText().split(":");
				
				String hostname = values[0];
				int port = Integer.parseInt(values[1]);

				tcp = new ImmediateTCPController(hostname, port);
				tcp.setContainer((ImmediateMode) currentMode);
				tcp.connect();
				tcp.startSendingPeriodic(50);
			}
		});

		BorderPane root = new BorderPane();
		root.setLeft(drawBar);
		root.setRight(choiceBar);
		root.setBottom(ipBar);
		root.setCenter(stackPane);
		stackPane.getChildren().addAll(border_canvas,workSpace);

		Scene scene = new Scene(root, 800, 600);

		scene.getStylesheets().add(this.getClass().getResource("myCSS.css").toExternalForm());

		primaryStage.setTitle("RobotTP");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}


	//draw method
	private void initDraw(GraphicsContext gc) {


		System.out.println("javafxapplication1.JavaFXApplication1.initDraw()");

		double canvasWidth = gc.getCanvas().getWidth();
		double canvasHeight = gc.getCanvas().getHeight();

		gc.setFill(Color.LIGHTGRAY);
		gc.setStroke(Color.BLACK);


		gc.fill();
		/*gc.strokeRect(
		  0,              //x of the upper left corner
		  0,              //y of the upper left corner
		  canvasWidth,    //width of the rectangle
		  canvasHeight);  //height of the rectangle*/

		//eraser
		if (eraser != null) {
			gc.clearRect(canvasWidth, canvasWidth, canvasWidth, canvasWidth);
		}

		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);


	}




}


