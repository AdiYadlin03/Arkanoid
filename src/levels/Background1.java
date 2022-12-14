package levels;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import objects.Block;
import objects.Sprite;

import java.awt.Color;

/**
 * @author Adi Yadlin
 * This class contains the background for the first level.
 */
public class Background1 implements Sprite {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    @Override
    public void drawOn(DrawSurface d) {
        new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT), Color.black).drawOn(d);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 155, 90);
        d.drawCircle(400, 155, 60);
        d.drawCircle(400, 155, 120);
        d.drawLine(250, 155, 550, 155);
        d.drawLine(400, 0, 400, 300);
    }

    @Override
    public void timePassed() {
    }
}
