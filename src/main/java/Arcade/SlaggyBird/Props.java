package Arcade.SlaggyBird;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Props extends Movements {

    Pane layout;
    Props(Pane layout){
        this.layout = layout;
    }

    public Text text(String text,String Style,Color color, double x, double y){
        Text tex = new Text(text);
        tex.setStyle(Style);
        tex.setFill(color);
        tex.setX(x);
        tex.setY(y);
        layout.getChildren().add(tex);
        return tex;
    }

    public ImageView image(String image, double x, double y, double Width, double Height){
        Image imag = new Image(getClass().getResource(image).toExternalForm());
        ImageView view = new ImageView(imag);
        view.setX(x);
        view.setY(y);
        view.setFitWidth(Width);
        view.setFitHeight(Height);
        layout.getChildren().add(view);
        return view;
    }
}
