package Arcade.DinoGame;

import Arcade.GUI;
import Arcade.GUIInterface;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class DinoGame extends GUI {

    public DinoGame(Pane layout) {
        super(layout);
    }

    public Scene createGame (){

        Pane pane = new Pane();

        Label titleLabel = new Label("✨ David The Dino ✨");
        titleLabel.getStyleClass().add("title-label");

        Scene scene = null;

        return scene;
    }


}
