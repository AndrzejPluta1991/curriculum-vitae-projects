package calculator_fxml;

import calculator_fxml.CalculatorController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

public class CalculatorMain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("GUI_Components.fxml"));
			
			Scene scene = new Scene(root, 325, 450);
			scene.getStylesheets().add(getClass().getResource("GUI_Properties.css").toExternalForm());
			
			@SuppressWarnings("unused")
			CalculatorController controller = new CalculatorController();
			
			primaryStage.getIcons().add(new Image("Java-icon.png"));
			primaryStage.setTitle("CalculatorFX");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
