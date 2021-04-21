package application;
	 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
    		
             ScrollPane scrollPane = new ScrollPane(root);

             //scrollPane.setPrefSize(1000,1000);
             Scene scene = new Scene(scrollPane,700,400);
     		 scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
     		 primaryStage.setScene(scene);
     	     primaryStage.show();
             scrollPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
             scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

       		/*
       		Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
    		Scene scene = new Scene(root,700,400);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		primaryStage.setScene(scene);
    		primaryStage.show();*/
    	}
    	catch(Exception e) {
    		e.printStackTrace();    		
    	}
    }
}