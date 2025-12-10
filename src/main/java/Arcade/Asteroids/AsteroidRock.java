package Arcade.Asteroids;

import javafx.scene.shape.Polygon;

import java.util.Random;

public class AsteroidRock extends Character{

    private double rotationMovement;

    public AsteroidRock (int x, int y){
        super(new Polygon(0,-20,15,-10,15,10,-10,15,-15,5,-5,-15), x,y);
        Random random = new Random();
        super.getCharacter().setRotate(random.nextInt(360));
        int accelerationAmount = 1 + random.nextInt(10);
        for (int i=0; i<accelerationAmount; i++){
            accelerate();
        }
        this.rotationMovement = 0.5 - random.nextDouble();
    }

    public void move(){
        super.move();
        super.getCharacter().setRotate(super.getCharacter().getRotate() + rotationMovement);
    }
}
