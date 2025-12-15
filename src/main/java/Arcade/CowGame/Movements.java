package Arcade.CowGame;

public class Movements {

    double clickW;
    double clickS;
    double clickA;
    double clickD;
    double gravW;
    double gravS;
    double gravA;
    double gravD;
    double slideCounter;

    public double clickW() {
        clickW++;
        return clickW;
    }

    public double clickS() {
        clickS++;
        return clickS;
    }

    public double clickA() {
        clickA++;
        return clickA;
    }

    public double clickD() {
        clickD++;
        return clickD;
    }

    public double gravityW(String picked) {
        gravW = picked.equals("W") || (gravW >= 1) && (gravW<=24) ? gravW + 1 : 0;
        return gravW;
    }

    public double constantMotion() {
        slideCounter++;
        return slideCounter;
    }
}
