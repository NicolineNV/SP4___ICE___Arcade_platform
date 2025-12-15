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

    public double gravityS(String picked) {
        gravS = picked.equals("S") || (gravS >= 1) && (gravS<=24) ? gravS + 1 : 0;
        return gravS;
    }

    public double gravityA(String picked) {
        gravA = picked.equals("A") || (gravA >= 1) && (gravA<=24) ? gravA + 1 : 0;
        return gravA;
    }

    public double gravityD(String picked) {
        gravD = picked.equals("D") || (gravD >= 1) && (gravD<=24) ? gravD + 1 : 0;
        return gravD;
    }

    public double constantMotion() {
        slideCounter++;
        return slideCounter;
    }
}
