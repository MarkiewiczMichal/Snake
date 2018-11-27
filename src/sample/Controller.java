package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.awt.*;


public class Controller
{

    @FXML
    BorderPane panel;
    @FXML
    TextField poleTekstowe1;
    @FXML
    TextField poleTekstowe2;
    @FXML
    TextField poleTekstowe3;
    @FXML
    TextField snakeSpeed;
    @FXML
    TextField Points;
    @FXML
    Canvas canvas;
    @FXML
    Button szybciejButton;
    @FXML
    Button wolniejButton;


    private int wspX;
    private int wspY;
    private Kierunek wKtóraStrone = Kierunek.DOL;
    final static int STEP_SIZE = 10;
    private Snake snake = new Snake(new Point(STEP_SIZE, STEP_SIZE));
    RandomApple jablko = new RandomApple();
    public int szybkoscWeza = 1000;
    public int points = 0;
    Point wspJablka = jablko.randomApple();


    enum Kierunek
    {
        DOL,
        LEWO,
        PRAWO,
        GORA;
    }

    public void changeColor()
    {
        panel.setStyle("-fx-background-color: #334652");

        final MakeMap nowy = new MakeMap();
        nowy.nowyWaz(canvas);

        snakeSpeed.setText(szybkoscWeza + "");
        Points.setText(points + "");

        szybciejButton.setDisable(false);
        wolniejButton.setDisable(false);


        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                do
                {
                    GraphicsContext gc = canvas.getGraphicsContext2D();

                    gc.setFill(Color.RED);
                    gc.fillRect((double) wspJablka.getWspX(), (double) wspJablka.getWspY(), STEP_SIZE, STEP_SIZE);

                    if (wKtóraStrone == Kierunek.GORA)
                    {
                        snake.ruch(Kierunek.GORA);
                    }
                    if (wKtóraStrone == Kierunek.PRAWO)
                    {
                        snake.ruch(Kierunek.PRAWO);
                    }
                    if (wKtóraStrone == Kierunek.DOL)
                    {
                        snake.ruch(Kierunek.DOL);
                    }
                    if (wKtóraStrone == Kierunek.LEWO)
                    {
                        snake.ruch(Kierunek.LEWO);
                    }

                    for (Point licz : snake.wspWeza)
                    {
                        gc.setFill(Color.GREENYELLOW);
                        gc.fillRect((double) licz.getWspX() * STEP_SIZE, (double) licz.getWspY() * STEP_SIZE, STEP_SIZE, STEP_SIZE);
                    }

                    if (snake.wspWeza.get(0).getWspX() * 10 == canvas.getWidth())
                    {
                        Point nowaGlowa = snake.wspWeza.get(0);
                        snake.wspWeza.set(0, new Point(0, nowaGlowa.getWspY()));
                    }

                    if (snake.wspWeza.get(0).getWspX() * 10 < 0)
                    {
                        Point nowaGlowa = snake.wspWeza.get(0);
                        snake.wspWeza.set(0, new Point((int)canvas.getWidth()/STEP_SIZE, nowaGlowa.getWspY()));
                    }

                    if (snake.wspWeza.get(0).getWspY() * 10 == canvas.getHeight())
                    {
                        Point nowaGlowa = snake.wspWeza.get(0);
                        snake.wspWeza.set(0, new Point(nowaGlowa.getWspX(), 0));
                    }
                    if (snake.wspWeza.get(0).getWspY() * 10 < 0)
                    {
                        Point nowaGlowa = snake.wspWeza.get(0);
                        snake.wspWeza.set(0, new Point( nowaGlowa.getWspX(),(int)canvas.getHeight()/STEP_SIZE));
                    }


                    if (snake.wspWeza.get(0).getWspX() * 10 == wspJablka.getWspX() & snake.wspWeza.get(0).getWspY() * 10 == wspJablka.getWspY())
                    {
                        snake.zjedzJablko();
                        wspJablka = jablko.randomApple();
                        points++;
                        Points.setText(points + "");

                    }

                    poleTekstowe1.setText(wspJablka.getWspX() + " " + wspJablka.getWspY());
                    poleTekstowe2.setText(snake.wspWeza.get(0).getWspX() * 10 + " " + snake.wspWeza.get(0).getWspY() * 10);


                    try
                    {
                        Thread.sleep(szybkoscWeza);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    nowy.nowyWaz(canvas);
                } while (czyKoniec());


            }
        }).start();


    }

    public boolean czyKoniec()
    {
        Point glowa = snake.wspWeza.get(0);
        for (int i = 1; i < snake.wspWeza.size(); i++)
        {
            if (glowa.getWspX() == snake.wspWeza.get(i).getWspX() & glowa.getWspY() == snake.wspWeza.get(i).getWspY())
            {
                Points.setText("przegrana");
                return false;
            }
        }
        return true;
    }


    public void dupa(KeyEvent k)
    {
        if (k.getCode() == KeyCode.LEFT)
        {
            poleTekstowe3.setText("lewo");
            wKtóraStrone = Kierunek.LEWO;

        }
        if (k.getCode() == KeyCode.RIGHT)
        {
            poleTekstowe3.setText("prawo");
            wKtóraStrone = Kierunek.PRAWO;
        }
        if (k.getCode() == KeyCode.UP)
        {
            poleTekstowe3.setText("gora");
            wKtóraStrone = Kierunek.GORA;
        }
        if (k.getCode() == KeyCode.DOWN)
        {
            poleTekstowe3.setText("dol");
            wKtóraStrone = Kierunek.DOL;
        }

    }

    public void szybciej()
    {
        szybkoscWeza = szybkoscWeza - 50;
        snakeSpeed.setText(szybkoscWeza + "");
        if (szybkoscWeza == 50)
        {
            szybciejButton.setDisable(true);
        }
        if (szybkoscWeza < 1200)
        {
            wolniejButton.setDisable(false);
        }


    }

    public void wolniej()
    {
        szybkoscWeza = szybkoscWeza + 50;
        snakeSpeed.setText(szybkoscWeza + "");
        if (szybkoscWeza > 50)
        {
            szybciejButton.setDisable(false);
        }
        if (szybkoscWeza >= 1200)
        {
            wolniejButton.setDisable(true);
        }
    }

}
