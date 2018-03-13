package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			TabPane root = new TabPane();
			Tab stopWatch = new Tab();
			Tab timer = new Tab();
			stopWatch.setText("Stop Watch");
			timer.setText("Timer");
			stopWatch.setClosable(false);
			timer.setClosable(false);
			root.getTabs().add(stopWatch);
			root.getTabs().add(timer);
			stopWatch.setContent((VBox)FXMLLoader.load(getClass().getResource("/application/stopWatchGUI.fxml")));
			timer.setContent((VBox) FXMLLoader.load(getClass().getResource("/application/timerGUI.fxml")));
			Scene scene = new Scene(root,400,435);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
