package Arcade;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Login extends Application{
    private String name;

    public void start(Stage stage){
        Label welcomelabel = new Label("Welcome to the arcade! Please enter your name: ");
        TextField nameField = new TextField();
        nameField.setMaxWidth(200);
        nameField.setPromptText("Your name");
        Label responseLabel = new Label();
        Button loginButton = new Button("Login");
        loginButton.setOnAction( e -> handleLogin(nameField, responseLabel));
        nameField.setOnAction(e -> handleLogin(nameField, responseLabel));

        VBox layout = new VBox(10);
        Scene scene = new Scene(layout, 500, 300);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(welcomelabel, nameField, loginButton, responseLabel);

        try {
            scene.getStylesheets().add(getClass().getResource("/application2.0.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("CSS file not found - runs without styling");
        }

        stage.setTitle("Login)");
        stage.setScene(scene);
        stage.show();
    }

    private void handleLogin(TextField nameField, Label responseLabel){
        this.name = nameField.getText();
        if (name.isEmpty()) {
            responseLabel.setText("Please enter your name");
        } else {
            responseLabel.setText("Welcome " + name);
        }
    }

}
