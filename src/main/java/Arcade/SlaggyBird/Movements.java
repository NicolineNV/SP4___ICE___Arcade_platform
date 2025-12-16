package Arcade.SlaggyBird;

public class Movements {
    public static String clicked;




    /// //////////////////////////
    /// CONSTANT COUNT MOTION ///
    /// ////////////////////////
    double count;

    public double constantAdd(double keepsAdding) {
        count += keepsAdding;
        return count;


    }

    /// ///////////////////
    /// CLICK MOTIONS  ///
    /// /////////////////
    public static double addW, addOnceW;

    public static double clickW(double addOnce) {
        addW = addOnce;
        addOnceW = "W".equals(clicked) ? addOnceW + addOnce : addOnceW;
        return addOnceW;
    }

    public static double addS, addOnceS;

    public static double clickS(double addOnce) {
        addS = addOnce;
        addOnceS = "S".equals(clicked) ? addOnceS + addOnce : addOnceS;
        return addOnceS;
    }

    public static double addA, addOnceA;

    public static double clickA(double addOnce) {
        addA = addOnce;
        addOnceA = "A".equals(clicked) ? addOnceA + addOnce : addOnceA;
        return addOnceA;
    }

    public static double addD, addOnceD;

    public static double clickD(double addOnce) {
        addD = addOnce;
        addOnceD = "D".equals(clicked) ? addOnceD + addOnce : addOnceD;
        return addOnceD;
    }

    /// ////////////////////
    /// COUNT MOTIONS  ///
    /// //////////////////
    public static double addAtW, keepsAddingW, stopsAtW;

    public static double clickW(double keepsAdding, double stopsAt) {
        keepsAddingW = keepsAdding;
        stopsAtW = stopsAt;
        addAtW = "W".equals(clicked) || (addAtW >= 1) && (addAtW <= stopsAt) ? addAtW + keepsAdding : 0;
        return addAtW;
    }

    public static double addAtS, keepsAddingS, stopsAtS;

    public static double clickS(double keepsAdding, double stopsAt) {
        keepsAddingS = keepsAdding;
        stopsAtS = stopsAt;
        addAtS = "S".equals(clicked) || (addAtS >= 1) && (addAtS <= stopsAt) ? addAtS + keepsAdding : 0;
        return addAtS;
    }

    public static double addAtA, keepsAddingA, stopsAtA;

    public static double clickA(double keepsAdding, double stopsAt) {
        keepsAddingA = keepsAdding;
        stopsAtA = stopsAt;
        addAtA = "A".equals(clicked) || (addAtA >= 1) && (addAtA <= stopsAt) ? addAtA + keepsAdding : 0;
        return addAtA;
    }

    public static double addAtD, keepsAddingD, stopsAtD;

    public static double clickD(double keepsAdding, double stopsAt) {
        keepsAddingD = keepsAdding;
        stopsAtD = stopsAt;
        addAtD = "D".equals(clicked) || (addAtD >= 1) && (addAtD <= stopsAt) ? addAtD + keepsAdding : 0;
        return addAtD;
    }

    /// ////////////////////
    /// JUMP MOTIONS  ///
    /// //////////////////
    public static boolean coolDownW;
    public static double airTimeW, turningPointw, jumpW, jW, gravity;

    public static double clickW(double airTime, double turningPoint, boolean coolDown) {
        airTimeW = airTime;
        turningPointw = turningPoint;
        coolDownW = coolDown;

        if(coolDown) {
            jumpW = "W".equals(clicked) || (jumpW >= 1) && (jumpW < airTime) ? jumpW + 1 : 0;
            jW = Math.sin(jumpW * Math.PI / airTime) * turningPoint;
        } else {
            gravity+=5;
            jumpW = "W".equals(clicked) ? jumpW + turningPoint : jumpW;
            jW = ("W".equals(clicked) || (jW >= 1) && (jW <= jumpW) ? jW + airTime : jW);
        }
        return jW-gravity;
    }
}

