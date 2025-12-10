package Arcade.Asteroids;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Character {

    private Polygon character;
    private Point2D movement;
    private boolean alive;

    public Character (Polygon polygon, int x, int y){
        this.character = polygon;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        this.movement = new Point2D(0,0);
        this.alive = true;
    }

    public Polygon getCharacter(){
        return character;
    }

    public void turnLeft(){
        this.character.setRotate(this.character.getRotate()-5);
    }

    public void turnRight(){
        this.character.setRotate(this.character.getRotate()+5);
    }

    public void move(){
        this.character.setTranslateX(this.character.getTranslateX()+this.movement.getX());
        this.character.setTranslateY(this.character.getTranslateY()+this.movement.getY());
        if (this.character.getTranslateX() < 0){
            this.character.setTranslateX(this.character.getTranslateX()+Asteroids.width);
        }
        // using % is safer than - at very high velocities
        if (this.character.getTranslateX() > Asteroids.width){
            this.character.setTranslateX(this.character.getTranslateX() % Asteroids.width);
        }
        if (this.character.getTranslateY() < 0){
            this.character.setTranslateY(this.character.getTranslateY()+Asteroids.height);
        }
        // using % is safer than - at very high velocities
        if (this.character.getTranslateY() > Asteroids.height){
            this.character.setTranslateY(this.character.getTranslateY() % Asteroids.height);
        }
    }

    public void accelerate(){
        double changeX = Math.cos(Math.toRadians(this.character.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.character.getRotate()));
        changeX *= 0.1;
        changeY *= 0.1;
        this.movement = this.movement.add(changeX, changeY);
    }

    public void slowingDown(){
        double changeX = Math.cos(Math.toRadians(this.character.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.character.getRotate()));
        changeX *= -0.07;
        changeY *= -0.07;
        this.movement = this.movement.add(changeX, changeY);
    }

    public boolean collide(Character other){
        Shape collisionArea = Shape.intersect(this.character, other.getCharacter());
        return collisionArea.getBoundsInLocal().getWidth()!=-1;
    }

    public Point2D getMovement() {
        return movement;
    }

    public void setMovement(Point2D movement) {
        this.movement = movement;
    }

    public boolean isAlive(){
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
