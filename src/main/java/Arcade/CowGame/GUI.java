package Arcade.CowGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GUI {

    Pane layout;

    GUI(Pane layout){
        this.layout = layout;
    }

    public void text(String text,String Style,Color color, double x, double y, boolean Display){
        Text tex = new Text(text);
        tex.setStyle(Style);
        tex.setFill(color);
        tex.setX(x);
        tex.setY(y);
        if(Display)
            layout.getChildren().add(tex);
    }

    public void image(String image, double x, double y, double Width, double Height, boolean Display){
        Image imag = new Image(getClass().getResource( "/CowGamePictures" + image).toExternalForm());
        ImageView view = new ImageView(imag);
        view.setX(x);
        view.setY(y);
        view.setFitWidth(Width);
        view.setFitHeight(Height);
        if(Display)
            layout.getChildren().add(view);
    }

}
