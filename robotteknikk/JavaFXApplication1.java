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

    Circle circle11 = new Circle(100, 100, 25, Color.TRANSPARENT);

    double startX, startY, stopX, stopY;
    double totalX, totalY;
    double radius;
    double prevRadius = 0;
    double moveX;
    double moveY;
    double startMoveX;
    double startMoveY;

    //creating color picker object for the whole class
    ColorPicker colPicker = new ColorPicker(Color.BLACK);
    Color c;
    //eraser toggle button for the whole class
    ToggleButton eraser = new ToggleButton("ERASER");
    ToggleButton circle = new ToggleButton("CIRCLE");

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
                
                //Testing of the coordinates array print on closing of the application
                System.out.println("Close requested");
                System.out.println("Foreløpig data: ");
                System.out.println(currentMode.getData().toJSON());
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        colPicker.setId("colPicker");
		black.setId("blackButton");
		blue.setId("blueButton");
		red.setId("redButton");
		green.setId("greenButton");

        //detach circle from autoplacement
        circle11.setManaged(false);


        StackPane stackPane = new StackPane();
        stackPane.setId("stackPane");
        //defining color picker as a combobox/dropdown menu
        c = colPicker.getValue();
        colPicker.getStyleClass().add("split-button");
        colPicker.getStyleClass().add("button");


        //creating drawing space
        Canvas workSpace = new Canvas(800, 800);
        workSpace.setId("canvas");
        GraphicsContext graphicsContext = workSpace.getGraphicsContext2D();


        //border for working space
        Canvas border_canvas = new Canvas(800, 800);
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



        //color picker decides color in graphicContext on eventhandler
        colPicker.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                graphicsContext.setStroke(colPicker.getValue());
            }
        });

        //Clear all
        clearAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                graphicsContext.clearRect(0, 0, workSpace.getWidth(), workSpace.getHeight());
                stackPane.getChildren().remove(circle11);
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


                        if (circle.isSelected() == true) {
                            stackPane.getChildren().add(circle11);
                            Bounds bounds = circle11.getBoundsInParent();
                            System.out.println("bounds are : " + bounds);
                        }
                    }
                });


        //what happens while holding mouse
        workSpace.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {

                        moveX = event.getX();
                        moveY = event.getY();
                          
                        
                        System.out.println("X verdien er " + moveX + "." + "Y verdien er " + moveY);


                        if (eraser.isSelected() == true && circle.isSelected() == false) {
                            graphicsContext.clearRect(event.getX(), event.getY(), 30, 30);
                        } else if (eraser.isSelected() == false && circle.isSelected() == false) {
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
                        if (circle.isSelected() == true && eraser.isSelected() == false) {

                        }

						if(!eraser.isSelected() && !circle.isSelected()){
							currentMode.addPoint(event.getX(), event.getY(), 1);
						}
                    }
                });


        //buttongs for left toolbar
        Button pen = new Button("PEN");


        ToolBar drawBar = new ToolBar();
        drawBar.setId("drawBar");
        drawBar.setOrientation(Orientation.VERTICAL);
        drawBar.getItems().addAll(
                circle,
                pen,
                //colPicker,  skip adding this for now, full palette not supported
                eraser,
                clearAll,
				black,
				blue,
				red,
				green
        );

        //buttons for left toolbar
        Button sendPicture = new Button("Send");
        ToggleButton savedMode = new ToggleButton("Saved Mode");
        ToolBar choiceBar = new ToolBar();
        choiceBar.setId("rightBar");
        choiceBar.getItems().addAll(
                sendPicture,
                savedMode
        );

        savedMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (savedMode.isSelected()) {
                    currentMode = new SavedMode(null);
                } else
                    currentMode = new ImmediateMode(null);

                System.out.println("Mode is: " + currentMode);

            }
        });

        choiceBar.setOrientation(Orientation.VERTICAL);


        //ip toolbar bottom
        TextField ipField = new TextField();
        Label ipText = new Label("Enter IP adress");
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
                System.out.println(Arrays.deepToString(values));
                String hostname = values[0];
                int port = Integer.parseInt(values[1]);

				tcp = new ImmediateTCPController(hostname, port);
				tcp.setContainer((ImmediateMode) currentMode);
				tcp.connect();
				tcp.startSendingPeriodic(500);
            }
        });

        //outline of circle
        circle11.setStroke(Color.BLACK);
        circle11.setCursor(Cursor.CROSSHAIR);


        BorderPane root = new BorderPane();
        root.setLeft(drawBar);
        root.setRight(choiceBar);
        root.setBottom(ipBar);
        root.setCenter(stackPane);
        stackPane.getChildren().addAll(border_canvas,workSpace);


        circle11.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                //saving start location of circle for resize calculations
                startX = circle11.getCenterX();
                startY = circle11.getCenterY();
                System.out.println("startX er: " + startX);
                System.out.println("startY er: " + startY);


            }
        });


        //removing cicrle object from stackPane and other circle related ops
        circle11.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                startMoveX = event.getX();
                startMoveY = event.getY();


                if (eraser.isSelected() == true) {
                    stackPane.getChildren().remove(circle11);
                }

            }


        });

        circle11.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                stopX = event.getX();
                stopY = event.getY();

                System.out.println("stopX er: " + stopX);
                System.out.println("stopY er: " + stopY);

                totalX = stopX - startX;
                System.out.println("totalX er: " + totalX);
                totalY = stopY - startY;

                radius = totalX * totalX + totalY * totalY;

                if (radius > prevRadius + 10 && event.isShiftDown()) {

                    circle11.setRadius(circle11.getRadius() + 1);
                } else if (radius < prevRadius + 10 && event.isShiftDown()) {

                    circle11.setRadius(circle11.getRadius() - 1);
                }

                //circle move logic here
                else {
                    System.out.println("shift not pressed");
                    circle11.setCenterX(stopX);
                    circle11.setCenterY(stopY);
                }


                System.out.println("totalY er: " + totalY);

                prevRadius = radius;


            }
        });


        circle11.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {


                Bounds bounds = circle11.getBoundsInLocal();
                System.out.println("bounds: " + bounds);


            }


        });


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
        gc.setStroke(colPicker.getValue());
        gc.setLineWidth(1);


    }




}


