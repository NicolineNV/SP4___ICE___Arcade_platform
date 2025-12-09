package Arcade;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class GUI {

    Pane layout;
    public GUI(Pane layout){
        this.layout = layout;
    }

    public void rectangle(Color color, double X, double Y, double Width, double Height){
        Rectangle rect = new Rectangle();
        rect.setFill(color);
        rect.setX(X);
        rect.setY(Y);
        rect.setWidth(Width);
        rect.setHeight(Height);
        layout.getChildren().add(rect);
    }

    public void createButton(){
    }

}
