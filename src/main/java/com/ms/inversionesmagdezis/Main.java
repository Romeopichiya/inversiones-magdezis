package main.java.com.ms.inversionesmagdezis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // La ruta ahora incluye /main/ porque tus recursos están dentro de src/main/resources
        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/login-view.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Inversiones Magdezis - Inicio");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

