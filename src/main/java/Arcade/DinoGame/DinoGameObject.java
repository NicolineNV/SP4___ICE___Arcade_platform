package Arcade.DinoGame;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/*
    This class creates a game object used for pretty much every type of object in this game
    It makes it possible to create what ever object desired
    - and makes it possible to store an image of the object and render it.
 */

public class DinoGameObject {

    private Image image;
    private double xPosition;
    private double yPosition;
    private double width;
    private double height;
    private boolean passed = false;

    public DinoGameObject(String image, double x, double y, double width, double height){
        this.image = new Image(image);
        this.xPosition = x;
        this.yPosition = y;
        this.width = width;
        this.height = height;
    }

    // Renders the image and draws it on our Canvas
    public void renderImage (GraphicsContext gc){
        gc.drawImage(image, xPosition, yPosition, width, height);
    }

    public Rectangle2D getBounds(){
        return new Rectangle2D(xPosition, yPosition, width, height);
    }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }

    public double getXPosition(){
        return xPosition;
    }
    public void setXPosition(double xPosition){
        this.xPosition = xPosition;
    }

    public double getYPosition(){
        return yPosition;
    }
    public void setYPosition(double yPosition){
        this.yPosition = yPosition;
    }

    public double getWidth(){
        return width;
    }
    public void setWidth(double width){
        this.width = width;
    }

    public double getHeight(){
        return height;
    }
    public void setHeight(double height){
        this.height = height;
    }

    public boolean isPassed(){
        return passed;
    }
    public void setPassed(boolean passed){
        this.passed = passed;
    }

}
