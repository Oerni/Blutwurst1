package application;
	
import spieldaten.SpielModel;
import controller.SpielViewController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {			
			SpielModel model = new SpielModel(primaryStage);
			SpielViewController viewController = new SpielViewController(model);
			viewController.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}