package Arcade.DinoGame;

public class Dino extends DinoGameObject{

    private final float gravity = 0.2f;
    private float speed = -20;
    public static final int land_Y = 320;
    private boolean isJumping = false;

    public Dino(String image, double x, double y, double width, double height) {
        super(image, x, y, width, height);
    }
}
