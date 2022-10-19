package levels;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import objects.Block;
import objects.Sprite;

import java.awt.Color;

/**
 * @author Adi Yadlin
 * This class contains the background for the second level.
 */
public class Background2 implements Sprite {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    @Override
    public void drawOn(DrawSurface d) {
        new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT),
                Color.WHITE).drawOn(d);
    }

    @Override
    public void timePassed() {
    }
}
