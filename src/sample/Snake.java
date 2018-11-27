package sample;

import java.util.ArrayList;

public class Snake
{
    public ArrayList<Point> wspWeza = new ArrayList<>();


    public Snake(Point head)
    {
        wspWeza.add(head);
    }

    public void ruch(Controller.Kierunek kier)
    {
        Point head = wspWeza.get(0);
        wspWeza.remove(wspWeza.size() - 1);
        int x = head.getWspX();
        int y = head.getWspY();

        switch (kier)
        {
            case DOL:
                y++;
                break;
            case GORA:
                y--;
                break;
            case LEWO:
                x--;
                break;
            case PRAWO:
                x++;
                break;
        }
        wspWeza.add(0, new Point(x, y));
    }


    public void zjedzJablko()
    {
        Point dupaWeza = wspWeza.get(wspWeza.size() - 1);
        wspWeza.add(new Point(dupaWeza.getWspX(), dupaWeza.getWspX()));
    }

}