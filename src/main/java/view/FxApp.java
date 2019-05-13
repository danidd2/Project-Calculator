package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Contains the GUI.
 */
public class FxApp extends Application {
    /**
     * Contains the GUI.
     * @param stage The main stage
     * @throws Exception possible exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }
}
