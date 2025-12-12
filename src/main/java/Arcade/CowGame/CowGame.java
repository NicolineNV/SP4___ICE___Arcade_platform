package Arcade.CowGame;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Random;

public class CowGame extends Props implements GameInterface {

    private boolean gameOver = false;
    public boolean isGameOver() {
        return gameOver;
    }

    int random1;
    int random2;
    double W;
    double S;
    double A;
    double D;
    double C;
    double gW;
    double gS;
    double gA;
    double gD;

    public void runClickedW() {
        W = motion.clickW();
    }

    public void runClickedS() {
        S = motion.clickS();
    }

    public void runClickedA() {
        A = motion.clickA();
    }

    public void runClickedD() {
        D = motion.clickD();
    }

    public void gravity() {
        gW = motion.gravityW("");
        gS = motion.gravityS("");
        gA = motion.gravityA("");
        gD = motion.gravityD("");
    }

    public void runConstant() {
        C = motion.constantMotion();
    }

    Movements motion;
    public CowGame(Pane layout, Movements motion) {
        super(layout);
        this.motion = motion;
    }

    public void runGame() {
        layout.getChildren().clear();
        createGame();
    }

    /// /////////////////////////////////////////////////////////////////////////////////////////////

    public void createGame() {

        //Background______________________________________________________________________________________________
        image("/Grass.png", 0 - (C % 800 + 1) * 1.5, 0, 1300, 800, true);
        image("/Grass.png", 1300 - (C % 800 + 1) * 1.5, 0, 1300, 800, true);

        //COW________________________________________________________________________________________________
        double CowFrameChange = Math.ceil((C % 12 + 1) / 3);
        double jumpUp = Math.sin(-gW * Math.PI / 25);

        double CowX = 100 + D * 22 - (C) - A * 22;
        double CowY = 480 + jumpUp * 200;
        double CowHeight = 200;
        double CowWidth = 200;

        if (gA > 1 && gA < 20) {
            image("/cowAK47.png", CowX + 10, CowY - 45, CowWidth - 20, CowHeight, true);
        } else if (CowY >= 480 && gS <= 1) {
            image("/cow1.png", CowX, CowY, CowWidth, CowHeight, CowFrameChange == 1);
            image("/cow2.png", CowX, CowY, CowWidth, CowHeight, CowFrameChange == 2);
            image("/cow3.png", CowX, CowY, CowWidth, CowHeight, CowFrameChange == 3);
            image("/cow4.png", CowX, CowY, CowWidth, CowHeight, CowFrameChange == 4);
        } else if (gS >= 1) {
            image("/cowASS.png", CowX, 570, CowWidth, 50, CowFrameChange == 1);
            image("/cowLookingLeft.png", CowX, 570, CowWidth, 50, CowFrameChange == 2);
            image("/cowFronting.png", CowX, 570, CowWidth, 50, CowFrameChange == 3);
            image("/cow1.png", CowX, 570, CowWidth, 50, CowFrameChange == 4);
        } else {
            image("/cow1.png", CowX, CowY, CowWidth, CowHeight, CowFrameChange == 1);
            image("/cowASS.png", CowX, CowY, CowWidth, CowHeight, CowFrameChange == 2);
            image("/cowLookingLeft.png", CowX, CowY, CowWidth, CowHeight, CowFrameChange == 3);
            image("/cowFronting.png", CowX, CowY, CowWidth, CowHeight, CowFrameChange == 4);
        }

        //Props________________________________________________________________________________________________
        double Speed1 = 10;
        double Speed2 = 15;

        double Car1X = 1300 - (C % (1400 / Speed1)) * Speed1;
        double Car1Y = 460;
        double Car1Width = 220;
        double Car1Height = 220;
        image("/Car1.png", Car1X, Car1Y, Car1Width, Car1Height, random1 == 0);

        double ClockX = 1300 - (C % (1400 / Speed1)) * Speed1;
        double ClockY = 200;
        double ClockWidth = 70;
        double ClockHeight = 380;
        image("/Clock.png", ClockX, ClockY, ClockWidth, ClockHeight, random1 == 1);

        double FistX = 1300 - (C % (1400 / Speed1)) * Speed1;
        double FistY = 400 + Math.sin((C / 5) % 1300) * 80;
        double FistWidth = 100;
        double FistHeight = 120;
        image("/Fist.png", FistX, FistY, FistWidth, FistHeight, random1 == 2);

        double RaketRobotX = 1300 - (C % (1400 / Speed2)) * Speed2;
        double RaketRobotY = 500;
        double RaketRobotWidth = 100;
        double RaketRobotHeight = 70;
        image("/Robot.png", RaketRobotX, RaketRobotY, RaketRobotWidth, RaketRobotHeight, random2 == 0);

        double Vulture1X = 1300 - (C % (1400 / Speed2)) * Speed2;
        double Vulture1Y = 350;
        double Vulture1Width = 70;
        double Vulture1Height = 100;
        image("/Vulture.png", Vulture1X, Vulture1Y, Vulture1Width, Vulture1Height, random2 == 1);

        double Vulture2X = 1300 - (C % (1400 / Speed2)) * Speed2;
        double Vulture2Y = 475;
        double Vulture2Width = 70;
        double Vulture2Height = 100;
        image("/Vulture.png", Vulture2X, Vulture2Y, Vulture2Width, Vulture2Height, random2 == 1);

        double Car2X = 1300 - (C % (1400 / Speed2)) * Speed2;
        double Car2Y = 500;
        double Car2Width = 220;
        double Car2Height = 140;
        image("/Car2.png", Car2X, Car2Y, Car2Width, Car2Height, random2 == 2);

        double ExplosionFrameChange = Math.ceil((C % 24 + 1) / 2) * ((Car1X < 0 || Car2X < 0 || FistX < 0) ? 1 : 0);
        double ExplosionX = -150;
        double ExplosionY = 340;
        double ExplosionWidth = 400;
        double ExplosionHeight = 400;
        image("/Explosion1.png", ExplosionX, ExplosionY, ExplosionWidth, ExplosionHeight, ExplosionFrameChange == 1);
        image("/Explosion2.png", ExplosionX, ExplosionY, ExplosionWidth, ExplosionHeight, ExplosionFrameChange == 2);
        image("/Explosion3.png", ExplosionX, ExplosionY, ExplosionWidth, ExplosionHeight, ExplosionFrameChange == 3);
        image("/Explosion4.png", ExplosionX, ExplosionY, ExplosionWidth, ExplosionHeight, ExplosionFrameChange == 4);
        image("/Explosion5.png", ExplosionX, ExplosionY, ExplosionWidth, ExplosionHeight, ExplosionFrameChange == 5);
        image("/Explosion6.png", ExplosionX, ExplosionY, ExplosionWidth, ExplosionHeight, ExplosionFrameChange == 6);
        image("/Explosion7.png", ExplosionX, ExplosionY, ExplosionWidth, ExplosionHeight, ExplosionFrameChange == 7);
        image("/Explosion8.png", ExplosionX, ExplosionY, ExplosionWidth, ExplosionHeight, ExplosionFrameChange == 8);
        image("/Explosion9.png", ExplosionX, ExplosionY, ExplosionWidth, ExplosionHeight, ExplosionFrameChange == 9);
        image("/Explosion10.png", ExplosionX, ExplosionY, ExplosionWidth, ExplosionHeight, ExplosionFrameChange == 10);
        image("/Explosion11.png", ExplosionX, ExplosionY, ExplosionWidth, ExplosionHeight, ExplosionFrameChange == 11);
        image("/Explosion12.png", ExplosionX, ExplosionY, ExplosionWidth, ExplosionHeight, ExplosionFrameChange == 12);

        double FireFrameChange = Math.ceil((C % 26 + 1) / 2);
        double FireX = -100;
        double FireY = 390;
        double FireWidth = 300;
        double FireHeight = 250;
        image("/Fire1.png", FireX, FireY, FireWidth, FireHeight, FireFrameChange == 1);
        image("/Fire2.png", FireX, FireY, FireWidth, FireHeight, FireFrameChange == 2);
        image("/Fire3.png", FireX, FireY, FireWidth, FireHeight, FireFrameChange == 3);
        image("/Fire4.png", FireX, FireY, FireWidth, FireHeight, FireFrameChange == 4);
        image("/Fire5.png", FireX, FireY, FireWidth, FireHeight, FireFrameChange == 5);
        image("/Fire6.png", FireX, FireY, FireWidth, FireHeight, FireFrameChange == 6);
        image("/Fire7.png", FireX, FireY, FireWidth, FireHeight, FireFrameChange == 7);
        image("/Fire8.png", FireX, FireY, FireWidth, FireHeight, FireFrameChange == 8);
        image("/Fire9.png", FireX, FireY, FireWidth, FireHeight, FireFrameChange == 9);
        image("/Fire10.png", FireX, FireY, FireWidth, FireHeight, FireFrameChange == 10);
        image("/Fire11.png", FireX, FireY, FireWidth, FireHeight, FireFrameChange == 11);
        image("/Fire12.png", FireX, FireY, FireWidth, FireHeight, FireFrameChange == 12);
        image("/Fire13.png", FireX, FireY, FireWidth, FireHeight, FireFrameChange == 13);


        Random ran1 = new Random();
        double nextProp1X = random1 == 0 ? Car1X : random1 == 1 ? ClockX : random1 == 2 ? FistX : 0;
        double nextProp1Y = random1 == 0 ? Car1Y : random1 == 1 ? ClockY : random1 == 2 ? FistY : 0;
        double nextProp1Width = random1 == 0 ? Car1Width : random1 == 1 ? ClockWidth : random1 == 2 ? FistWidth : 0;
        double nextProp1Height = random1 == 0 ? Car1Height : random1 == 1 ? ClockHeight : random1 == 2 ? FistHeight : 0;
        random1 = nextProp1X < 0 ? ran1.nextInt(3) : random1;

        Random ran2 = new Random();
        double nextProp2X = random2 == 0 ? RaketRobotX : random1 == 1 ? Vulture1X : random1 == 2 ? Car2X : 0;
        double nextProp2Y = random2 == 0 ? RaketRobotY : random1 == 1 ? Vulture1Y : random1 == 2 ? Car2Y : 0;
        double nextProp2Width = random2 == 0 ? RaketRobotWidth : random1 == 1 ? Vulture1Width : random1 == 2 ? Car2Width : 0;
        double nextProp2Height = random2 == 0 ? RaketRobotHeight : random1 == 1 ? Vulture1Height : random1 == 2 ? Car2Height : 0;
        random2 = nextProp2X < 0 ? ran2.nextInt(3) : random2;

        //gamePhysics_________________________________________________________________________________________


        int down = (gS >= 1) ? 150 : 0;
        boolean Hitbox1X = (nextProp1X + 60 < CowX + CowWidth && nextProp1X + nextProp1Width - 60 > CowX);
        boolean Hitbox1Y = (nextProp1Y + 130 < CowY + CowHeight && nextProp1Y + nextProp1Height > CowY+down + 90);
        boolean Hitbox2X = (nextProp2X + 80 < CowX + CowWidth && nextProp2X + nextProp2Width + 60 > CowX);
        boolean Hitbox2Y = (nextProp2Y + 60 < CowY + CowHeight && nextProp2Y + nextProp2Height - 60 > CowY);

        if (Hitbox1X && Hitbox1Y) {
            image("/Burger.png", 400, 200, 400, 400, true);
            text("You Just Got BURGERED", "-fx-font-size: 24px;-fx-font-weight: 900;", Color.ORANGE, 450, 200, true);
            gameOver = true;
        }
    }
}

