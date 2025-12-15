package Arcade.SlaggyBird;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Random;

public class SlaggyBird extends Props {

    private boolean gameOver = false;

    public boolean isGameOver() {
        return gameOver;
    }

    public SlaggyBird(Pane layout) {
        super(layout);
    }

    /// /////////////////////////////////////////////////////////////////////////////////////

    int random1;
    int random2;
    int random3;
    int random4;
    int random5;

    int AA;
    int BB;
    int CC;
    int DD;
    int EE;

    public void runGame() {
        isGameOver();
        layout.getChildren().clear();


        //Static images
        image("/SlaggyBirdPictures/Sunset.png", 0, 0, 1300, 800);
        image("/SlaggyBirdPictures/Mountain.png", 0, 0, 1300, 800);
        image("/SlaggyBirdPictures/Mountains.png", 0, 0, 1300, 800);
        image("/SlaggyBirdPictures/StaticTrees.png", 0, 0, 1300, 800);

        //Character
        double change = clickW(1, 8);
        double birdY = 300 - clickW(8, 100, false);
        ImageView birdBody = image("/SlaggyBirdPictures/BirdBody" + (1 + (int) change) + ".png", 108, birdY+7, 31.5, 31.5);
        image("/SlaggyBirdPictures/Bird" + (1 + (int) change) + ".png", 100, birdY, 50, 50);

        //Dynamic images
        double treeX = (constantAdd(3) % 1345) - 2;

        image("/SlaggyBirdPictures/DynamicTrees.png", 1300 - treeX, 300, 1300, 500);
        image("/SlaggyBirdPictures/DynamicTrees.png", -treeX, 300, 1300, 500);

        Random ran1 = new Random();
        random1 = AA == 3 ? ran1.nextInt(6) : random1;
        Random ran2 = new Random();
        random2 = BB == 3 ? ran2.nextInt(6) : random2;
        Random ran3 = new Random();
        random3 = CC == 3 ? ran3.nextInt(6) : random3;
        Random ran4 = new Random();
        random4 = DD == 3 ? ran4.nextInt(6) : random4;
        Random ran5 = new Random();
        random5 = EE == 3 ? ran5.nextInt(6) : random5;

        if (treeX > 0) {
            AA += 3;
            ImageView a = image("/SlaggyBirdPictures/PipeUpsideDown.png", 1300 - AA, -random1 * 70, 40, 600);
            ImageView A = image("/SlaggyBirdPictures/Pipe.png", 1300 - AA, 750 - random1 * 70, 40, 600);
            if (birdBody.getBoundsInParent().intersects(a.getBoundsInParent()) ||
                    birdBody.getBoundsInParent().intersects(A.getBoundsInParent())) {
                text("Game Over","-fx-font-size: 24px;-fx-font-weight: 900;",Color.BLUE,400,400);
                gameOver = true;
            } } else {
            AA = 0;
        }

        if (treeX > 260 || treeX < 160) {
            BB += 3;
            ImageView b = image("/SlaggyBirdPictures/PipeUpsideDown.png", 1300 - BB, -random2 * 70, 40, 600);
            ImageView B = image("/SlaggyBirdPictures/Pipe.png", 1300 - BB, 750 - random2 * 70, 40, 600);
            if (birdBody.getBoundsInParent().intersects(b.getBoundsInParent()) ||
                    birdBody.getBoundsInParent().intersects(B.getBoundsInParent())) {
                text("Game Over","-fx-font-size: 24px;-fx-font-weight: 900;",Color.BLUE,400,400);
                gameOver = true;
            }} else {
            BB = 0;
        }

        if (treeX > 520 || treeX < 420) {
            CC += 3;
            ImageView c = image("/SlaggyBirdPictures/PipeUpsideDown.png", 1300 - CC, -random3 * 70, 40, 600);
            ImageView C = image("/SlaggyBirdPictures/Pipe.png", 1300 - CC, 750 - random3 * 70, 40, 600);
            if (birdBody.getBoundsInParent().intersects(c.getBoundsInParent()) ||
                    birdBody.getBoundsInParent().intersects(C.getBoundsInParent())) {
                text("Game Over","-fx-font-size: 24px;-fx-font-weight: 900;",Color.BLUE,400,400);
                gameOver = true;
            } } else {
            CC = 0;
        }

        if (treeX > 780 || treeX < 680) {
            DD += 3;
            ImageView d = image("/SlaggyBirdPictures/PipeUpsideDown.png", 1300 - DD, -random4 * 70, 40, 600);
            ImageView D = image("/SlaggyBirdPictures/Pipe.png", 1300 - DD, 750 - random4 * 70, 40, 600);
            if (birdBody.getBoundsInParent().intersects(d.getBoundsInParent()) ||
                    birdBody.getBoundsInParent().intersects(D.getBoundsInParent())) {
                text("Game Over","-fx-font-size: 24px;-fx-font-weight: 900;",Color.BLUE,400,400);
                gameOver = true;
            } } else {
            DD = 0;
        }

        if (treeX > 1040 || treeX < 940) {
            EE += 3;
            ImageView e = image("/SlaggyBirdPictures/PipeUpsideDown.png", 1300 - EE, -random5 * 70, 40, 600);
            ImageView E = image("/SlaggyBirdPictures/Pipe.png", 1300 - EE, 750 - random5 * 70, 40, 600);
            if (birdBody.getBoundsInParent().intersects(e.getBoundsInParent()) ||
                    birdBody.getBoundsInParent().intersects(E.getBoundsInParent())) {
                text("Game Over","-fx-font-size: 24px;-fx-font-weight: 900;",Color.BLUE,400,400);
                gameOver = true;
            } } else {
            EE = 0;


            if (birdY <= 50 || birdY >=750) {
                text("Game Over","-fx-font-size: 24px;-fx-font-weight: 900;",Color.BLUE,400,400);
                gameOver = true;
            }
        }

    }
}



