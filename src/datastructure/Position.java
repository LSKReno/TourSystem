package datastructure;

/**
 * Created by LSK.Reno on 2018/01/14.
 */

public class Position {

    private static MyList<Integer> xPos = new MyList<>();
    private static MyList<Integer> yPos = new MyList<>();

    public static MyList<Integer> getxPos() {
        return xPos;
    }

    public static void setxPos(MyList<Integer> xxPos) {
        xPos = xxPos;
    }

    public static MyList<Integer> getyPos() {
        return yPos;
    }

    public static void setyPos(MyList<Integer> yyPos) {
        yPos = yyPos;
    }



}
