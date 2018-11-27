package sample;

import java.util.Random;

public class RandomApple
{
    Random rand = new Random();

    public Point randomApple()
    {
        Point wspApple = new Point(rand.nextInt(19)*Controller.STEP_SIZE,rand.nextInt(19)*Controller.STEP_SIZE);

        return wspApple;
    }
}
