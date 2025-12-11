package Arcade.Asteroids;

import Arcade.GUI;
import Arcade.MainJavaFX;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Collectors;

public class Asteroids extends GUI {

    public static int width = 600;
    public static int height = 400;

    private AnimationTimer gameLoop;

    public Asteroids(Pane layout) {
        super(layout);
    }

    public Scene createGame () {

        Pane pane = new Pane();

        Canvas c = new Canvas(400, 600);
        GraphicsContext gc = c.getGraphicsContext2D();


        final Text[] text = {new Text(10, 20, "Points: 0")};
        final int[] points = {0};
        pane.getChildren().add(text[0]);
        pane.setPrefSize(width,height);

        Ship ship = new Ship(width/2,height/2);
        ship.getCharacter().setFill(Color.RED);
        List<AsteroidRock> asteroids = new ArrayList<>();
        List<Projectile> projectiles = new ArrayList<>();

        for (int i=0; i<5; i++){
            Random random = new Random();
            AsteroidRock asteroid = new AsteroidRock(random.nextInt(100), random.nextInt(100));
            asteroids.add(asteroid);
        }

        pane.getChildren().add(ship.getCharacter());
        asteroids.forEach(asteroid -> pane.getChildren().add(asteroid.getCharacter()));


        Scene scene = new Scene(pane, width + 100, height + 300);

        // stage.setTitle("Asteroids");
        //stage.setScene(scene);
        //stage.show();

        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
        scene.setOnKeyPressed(e -> {
            pressedKeys.put(e.getCode(), Boolean.TRUE);
        });
        scene.setOnKeyReleased(e -> {
            pressedKeys.put(e.getCode(), Boolean.FALSE);
        });

        gameLoop = new AnimationTimer(){

            public void handle (long now){
                if (pressedKeys.getOrDefault(KeyCode.LEFT, false)){
                    ship.turnLeft();
                }
                if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)){
                    ship.turnRight();
                }
                if (pressedKeys.getOrDefault(KeyCode.UP, false)){
                    ship.accelerate();
                }
                if (pressedKeys.getOrDefault(KeyCode.DOWN, false)){
                    ship.slowingDown();
                }
                if (pressedKeys.getOrDefault(KeyCode.SPACE, false) && projectiles.size() < 3){
                    Projectile projectile = new Projectile((int)ship.getCharacter().getTranslateX(), (int)ship.getCharacter().getTranslateY());
                    projectile.getCharacter().setRotate(ship.getCharacter().getRotate());
                    projectiles.add(projectile);
                    projectile.accelerate();
                    // normalize() to 1 unit per frame
                    // multiply() to 3 units per frame
                    projectile.setMovement(projectile.getMovement().normalize().multiply(3));
                    pane.getChildren().add(projectile.getCharacter());
                }
                ship.move();
                // interesting method
                asteroids.forEach(Character::move);
                projectiles.forEach(Character::move);

                // Remove asteroid and projectile on collision (assisted code)
                projectiles.forEach(projectile -> {
                    asteroids.forEach(asteroid -> {
                        if (projectile.collide(asteroid)){
                            projectile.setAlive(false);
                            asteroid.setAlive(false);
                        }
                    });
                });
                Iterator<Projectile> projectileIter = projectiles.iterator();
                while (projectileIter.hasNext()){
                    Projectile p = projectileIter.next();
                    if (!p.isAlive()){
                        pane.getChildren().remove(p.getCharacter());
                        projectileIter.remove();
                    }
                }
                Iterator<AsteroidRock> asteroidIter = asteroids.iterator();
                while(asteroidIter.hasNext()){
                    AsteroidRock a = asteroidIter.next();
                    if (!a.isAlive()){
                        text[0].setText("Points: " + ++points[0]);
                        pane.getChildren().remove(a.getCharacter());
                        asteroidIter.remove();
                    }
                }

                asteroids.forEach(asteroid ->{
                    if (ship.collide(asteroid)) {
                        stop();
                    }
                });
                //spawn new asteroids
                if (Math.random() < 0.02){
                    AsteroidRock asteroid = new AsteroidRock(width, height);
                    if (!asteroid.collide(ship)){
                        asteroids.add(asteroid);
                        pane.getChildren().add(asteroid.getCharacter());
                    }
                }
                }
        };
        gameLoop.start();

        return scene;
    }


    /*public static void main(String[] args){
        launch(args);
    }*/

    public void backToMenu (Button menuBtn){

        if (gameLoop != null) {
            gameLoop.stop();
        }

        Stage currentStage = (Stage) menuBtn.getScene().getWindow();
        MainJavaFX toMenu = new MainJavaFX();

        try {
            toMenu.start(currentStage);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

