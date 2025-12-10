package Arcade.DinoGame;

import Arcade.GUI;
import Arcade.GUIInterface;
import javafx.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


import java.util.ArrayList;

public class DinoGame extends GUI {

    Group group;
    Canvas gameCanvas;

    static final int boardWidth = 1000;
    static final int boardHeight = 400;

    GraphicsContext gc;

    DinoGameObject background;
    DinoGameObject platform;



    public DinoGame(Pane layout) {
        super(layout);
    }

    public Scene createGame (){

        try {

            group.getChildren().removeAll();

            Scene scene = new Scene(group, boardWidth, boardHeight);

            Label titleLabel = new Label("✨ David The Dino ✨");
            titleLabel.getStyleClass().add("title-label");


            new AnimationTimer(){

                private long lastUpdate = 0;
                private double titleSize = 1.0;
                private boolean growing = true;

                @Override
                public void handle(long now) {

                    if (now - lastUpdate >= 16_666_666){ // 60 FPS (Frames Per Second)
                        gc.clearRect(0, 0, boardWidth, boardHeight);
                        background.renderImage(gc);
                        platform.renderImage(gc);


                    }

                }
            } .start();


            return scene;


        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void initGame (){
        group.getChildren().clear();
        gameCanvas = new Canvas(boardWidth, boardHeight);
        gc = gameCanvas.getGraphicsContext2D();
        group.getChildren().add(gameCanvas);

        //player = new Dino("", -70, )
    }


}
