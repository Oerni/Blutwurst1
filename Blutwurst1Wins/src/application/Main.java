package application;
	
import controller.SpielVC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Model model = new Model(primaryStage);
			SpielVC viewController = new SpielVC(model);
			viewController.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}