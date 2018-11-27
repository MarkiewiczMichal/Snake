package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class MakeMap
{
    Point apple;
    Point head = new Point(20,20);
    int wspX;
    int wspY;


    public void nowyWaz(Canvas plotno)
    {
        GraphicsContext gc = plotno.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, plotno.getWidth(), plotno.getHeight());



    }


    public void odrysuj()
    {

    }





    public enum kierunek
    {
        GORA,
        DOL,
        LEWO,
        Prawo;

    }

}
