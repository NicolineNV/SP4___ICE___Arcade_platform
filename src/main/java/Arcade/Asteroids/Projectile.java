package Arcade.Asteroids;

import javafx.scene.shape.Polygon;

public class Projectile extends Character{


    public Projectile (int x, int y){
     super(new Polygon(4, -2, 4, 2, 4 , 0, -4, 0), x, y);
    }

}
