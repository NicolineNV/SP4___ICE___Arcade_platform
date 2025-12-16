package Arcade;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Menu {

    public static void backToMenu (Button menuBtn, AnimationTimer gameLoop){

        Stage currentStage = (Stage) menuBtn.getScene().getWindow();
        Stage mainStage = new Stage();
        MainJavaFX toMenu = new MainJavaFX();

        try {
            gameLoop.stop();
            toMenu.start(mainStage);
            currentStage.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
