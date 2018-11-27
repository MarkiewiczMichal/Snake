package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class MakeMap
{
    public void nowyWaz(Canvas plotno)
    {
        GraphicsContext gc = plotno.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, plotno.getWidth(), plotno.getHeight());
    }
}
