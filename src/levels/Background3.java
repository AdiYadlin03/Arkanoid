package levels;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import objects.Block;
import objects.Sprite;

import java.awt.Color;

/**
 * @author Adi Yadlin
 * This class contains the background for the third level.
 */
public class Background3 implements Sprite {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    @Override
    public void drawOn(DrawSurface d) {
        java.awt.Color color = new Color(20, 70, 40);
        new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT),
                color).drawOn(d);
    }

    @Override
    public void timePassed() {
    }
}