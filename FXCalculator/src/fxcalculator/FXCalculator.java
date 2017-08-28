package fxcalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author lopez
 */
public class FXCalculator extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass()
                .getResource("fxCalculator.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass()
                .getResource("application.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass()
                .getResourceAsStream("player.png")));
        stage.setTitle("Super FX-Calculator");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
