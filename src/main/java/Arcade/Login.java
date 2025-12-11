package Arcade;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Login extends Application{
    private String name;

    VBox layout;
    Scene scene;

    public void start(Stage stage){

        Label welcomelabel = new Label("Welcome to the arcade! Please enter your name: ");
        TextField nameField = new TextField();
        nameField.setMaxWidth(200);
        nameField.setPromptText("Your name");
        Label responseLabel = new Label();
        Button loginButton = new Button("Login");
        loginButton.setOnAction( e -> handleLogin(nameField, responseLabel, stage));
        nameField.setOnAction(e -> handleLogin(nameField, responseLabel, stage));

        layout = new VBox(30);
        scene = new Scene(layout, 500, 300);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(welcomelabel, nameField, loginButton, responseLabel);

        try {
            scene.getStylesheets().add(getClass().getResource("/applicationNEW.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("CSS file not found - runs without styling");
        }

        stage.setTitle("Login)");
        stage.setScene(scene);
        stage.show();
    }

    private void handleLogin(TextField nameField, Label responseLabel, Stage stage){

        this.name = nameField.getText();

        if (name.isEmpty()) {
            responseLabel.setText("Please enter your name");
        } else if (name.equalsIgnoreCase("xæa-12")){
            responseLabel.setText("Ah yes... Elon Musk's son, my salutations.");
            pauseToMenu(stage);
        } else if (name.matches(".*\\d.*")) {
            responseLabel.setText("Does your name contain a number......? Be serious");
        } else {
            responseLabel.setText("Welcome " + name);
            pauseToMenu(stage);
        }
    }
    public void pauseToMenu(Stage stage){
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            MainJavaFX menu = new MainJavaFX();
            stage.close();
            menu.start(stage);
        });
        pause.play();
    }

    /*public void stop (){
        if (isOpenMainJavaFX == true){
            System.exit(0);
        } else {
            System.out.println("Something went wrong, could not close login");
        }
    }*/

}
