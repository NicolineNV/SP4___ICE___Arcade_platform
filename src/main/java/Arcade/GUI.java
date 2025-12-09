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

    public void rectangle(String style, double X, double Y, double Width, double Height){
        Rectangle rect = new Rectangle();
        rect.getStyleClass().add(style);
        rect.setX(X);
        rect.setY(Y);
        rect.setWidth(Width);
        rect.setHeight(Height);
        layout.getChildren().add(rect);
    }

    public void createButton(){
    }

}
