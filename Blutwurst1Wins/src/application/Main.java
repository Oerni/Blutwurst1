package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import model.Model;
import controller.SpielVC;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Model model = new Model(primaryStage);
			SpielVC viewController = new SpielVC(model);
			viewController.show();
			
			
			// FXML-Datei laden!
			Parent root = FXMLLoader.load(getClass().getResource("view.SpielView.fxml"));
		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}