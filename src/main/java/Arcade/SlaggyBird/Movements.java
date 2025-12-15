package Arcade.SlaggyBird;

public class Movements {
    public static String clicked;

    /// //////////////////////////
    /// CONSTANT COUNT MOTION ///
    /// ////////////////////////
    double count;

    public double constantAdd(double keepsAdding) {
        count += keepsAdding;
        return count;}

    /// ////////////////////
    /// COUNT MOTIONS  ///
    /// //////////////////
    public static double addAtW, keepsAddingW, stopsAtW;

    public static double clickW(double keepsAdding, double stopsAt) {
        keepsAddingW = keepsAdding;
        stopsAtW = stopsAt;
        addAtW = "W".equals(clicked) || (addAtW >= 1) && (addAtW <= stopsAt) ? addAtW + keepsAdding : 0;
        return addAtW;}

    /// ////////////////////
    /// JUMP MOTIONS  ///
    /// //////////////////
    public static boolean coolDownW;
    public static double airTimeW, turningPointw, jumpW, jW, gravity;

    public static double clickW(double airTime, double turningPoint, boolean coolDown) {
        if(coolDown) {} else {
            gravity+=5;
            jumpW = "W".equals(clicked) ? jumpW + turningPoint : jumpW;
            jW = ("W".equals(clicked) || (jW >= 1) && (jW <= jumpW) ? jW + airTime : jW);}
        return jW-gravity;
    }
}

