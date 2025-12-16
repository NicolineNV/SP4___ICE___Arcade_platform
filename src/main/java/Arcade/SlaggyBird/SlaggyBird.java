package Arcade.SlaggyBird;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.Random;

public class SlaggyBird extends Props {

    private boolean gameOver = false;
    private final int[] pipeX = new int[5];
    private final int[] pipeRand = new int[5];
    private final int[] pipeTriggers = {0, 260, 520, 780, 1040};
    private final Random random = new Random();

    public boolean isGameOver() {
        return gameOver;
    }

    public SlaggyBird(Pane layout) {
        super(layout);
    }

    public void runGame() {
        layout.getChildren().clear();

        // Background
        image("/SlaggyBirdPictures/Sunset.png", 0, 0, 1300, 800);
        image("/SlaggyBirdPictures/Mountain.png", 0, 0, 1300, 800);
        image("/SlaggyBirdPictures/Mountains.png", 0, 0, 1300, 800);
        image("/SlaggyBirdPictures/StaticTrees.png", 0, 0, 1300, 800);

        // Bird
        double change = clickW(1, 8);
        System.out.println(change);
        double birdY = 300 - clickW(8, 100, false);
        ImageView birdBody = image("/SlaggyBirdPictures/BirdBody" + (1 + (int) change) + ".png", 108, birdY + 7, 31.5, 31.5);
        image("/SlaggyBirdPictures/Bird" + (1 + (int) change) + ".png", 100, birdY, 50, 50);

        // Moving trees
        double treeX = (constantAdd(3) % 1345) - 2;
        image("/SlaggyBirdPictures/DynamicTrees.png", 1300 - treeX, 300, 1300, 500);
        image("/SlaggyBirdPictures/DynamicTrees.png", -treeX, 300, 1300, 500);

        // Pipes
        for (int i = 0; i < 5; i++) {
            if (treeX > pipeTriggers[i] || treeX < pipeTriggers[i] - 100) {
                if (pipeX[i] == 0)
                    pipeRand[i] = random.nextInt(6);
                pipeX[i] += 3;

                ImageView up = image("/SlaggyBirdPictures/PipeUpsideDown.png", 1300 - pipeX[i], -pipeRand[i] * 70, 40, 600);
                ImageView down = image("/SlaggyBirdPictures/Pipe.png", 1300 - pipeX[i], 750 - pipeRand[i] * 70, 40, 600);

                if (birdBody.getBoundsInParent().intersects(up.getBoundsInParent()) ||
                        birdBody.getBoundsInParent().intersects(down.getBoundsInParent())) {
                    endGame();
                }
            } else {
                pipeX[i] = 0;
            }
        }
        if (birdY <= 50 || birdY >= 750) {
            endGame();
        }
    }
    private void endGame() {
        text("Game Over", "-fx-font-size: 24px;-fx-font-weight: 900;", Color.BLUE, 400, 400);
        gameOver = true;
    }


}