package Arcade.Asteroids;

import Arcade.Menu;
//import Arcade.GUI;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.*;

public class Asteroids {
    private AnimationTimer gameLoop;
    public static int width = 600;
    public static int height = 400;
    private long lastShotTime = 0;
    private final long shootCD = 500;
    Pane pane = new Pane();
    private Text[] text = {new Text(10, 20, "Points: 0")};
    private Ship ship = new Ship(width/2,height/2);
    private final int[] points = {0};
    private List <AsteroidRock> asteroids = new ArrayList<>();
    private List <Projectile> projectiles = new ArrayList<>();

    /*public Asteroids(Pane layout) {
        super(layout);
    }*/

    public Scene createGame () {


        pane.getChildren().add(text[0]);
        pane.setPrefSize(width,height);

        ship.getCharacter().setFill(Color.RED);


        for (int i=0; i<5; i++){
            Random random = new Random();
            AsteroidRock asteroid = new AsteroidRock(random.nextInt(100), random.nextInt(100));
            asteroids.add(asteroid);
        }

        pane.getChildren().add(ship.getCharacter());
        asteroids.forEach(asteroid -> pane.getChildren().add(asteroid.getCharacter()));

        VBox root = new VBox(15);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.TOP_CENTER);
        Text title = new Text("Asteroids");
        title.setFill(Color.YELLOW);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        StackPane paneWrapper = new StackPane(pane);
        paneWrapper.setPadding((new Insets(0,10,0,10)));
        pane.setMinSize(width, height);
        pane.setPrefSize(width, height);
        pane.setMaxSize(width, height);
        pane.setStyle("-fx-background-color: white;");
        VBox.setVgrow(pane, Priority.NEVER);
        Button restart = new Button("Restart");
        Button backToMenu = new Button("Back To Menu");
        restart.setOnAction(e -> restart(pane));
        backToMenu.setOnAction(e -> Menu.backToMenu(backToMenu, gameLoop));
        root.getChildren().addAll(title,pane, restart, backToMenu);
        Scene scene = new Scene(root);
        pane.requestFocus();
        pane.setOnMouseClicked(e -> pane.requestFocus());
        try {
            scene.getStylesheets().add(getClass().getResource("/applicationNEW.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("CSS file not found - runs without styling");
        }

        // Scene scene = new Scene(pane);

        // stage.setTitle("Asteroids");
        //stage.setScene(scene);
        //stage.show();

        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
        scene.setOnKeyPressed(e -> {
            pressedKeys.put(e.getCode(), Boolean.TRUE);
        });
        scene.setOnKeyReleased(e -> {
            pressedKeys.put(e.getCode(), Boolean.FALSE);
            if (e.getCode() == KeyCode.R) {
                restart(pane);
            }
        });


        gameLoop = new AnimationTimer(){

            public void handle (long now){
                long currentTime = System.currentTimeMillis();
                if (pressedKeys.getOrDefault(KeyCode.A, false)){
                    ship.turnLeft();
                }
                if (pressedKeys.getOrDefault(KeyCode.D, false)){
                    ship.turnRight();
                }
                if (pressedKeys.getOrDefault(KeyCode.W, false)){
                    ship.accelerate();
                }
                if (pressedKeys.getOrDefault(KeyCode.S, false)){
                    ship.slowingDown();
                }
                if (pressedKeys.getOrDefault(KeyCode.SPACE, false) && projectiles.size() < 3 && currentTime - lastShotTime >= shootCD){
                    lastShotTime = currentTime;
                    Projectile projectile = new Projectile((int)ship.getCharacter().getTranslateX(), (int)ship.getCharacter().getTranslateY());
                    projectile.getCharacter().setRotate(ship.getCharacter().getRotate());
                    projectile.getCharacter().setFill(Color.RED);
                    projectiles.add(projectile);
                    projectile.accelerate();
                    // normalize() to 1 unit per frame
                    // multiply() to 3 units per frame
                    projectile.setMovement(projectile.getMovement().normalize().multiply(3));
                    pane.getChildren().add(projectile.getCharacter());
                }
                if (pressedKeys.getOrDefault(KeyCode.ESCAPE, false)){
                    Menu.backToMenu(backToMenu, gameLoop);
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
        };gameLoop.start();

        return scene;
    }


    private void restart(Pane pane){
        gameLoop.stop();
        pane.getChildren().clear();
        points[0] = 0;
        text[0].setText("Points: 0");
        ship = new Ship(width/2, height/2);
        ship.getCharacter().setFill(Color.RED);
        asteroids = new ArrayList<>();
        projectiles = new ArrayList<>();
        for (int i=0; i<5; i++){
            Random random = new Random();
            AsteroidRock asteroid = new AsteroidRock(random.nextInt(100), random.nextInt(100));
            asteroids.add(asteroid);
        }
        pane.getChildren().add(text[0]);
        pane.getChildren().add(ship.getCharacter());
        asteroids.forEach(e -> pane.getChildren().add(e.getCharacter()));
        gameLoop.start();
        pane.requestFocus();
    }

}