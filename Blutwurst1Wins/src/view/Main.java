package view;
	
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
//			
//			Parent root = FXMLLoader.load(getClass().getResource("SpielView.fxml"));
//			Scene scene = new Scene(root);
//			primaryStage.setScene(scene);
//			primaryStage.show();
			
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