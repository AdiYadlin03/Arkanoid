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
    @Override
    public void drawOn(DrawSurface d) {
        new Block(new Rectangle(new Point(0, 0), 800, 600),
                Color.WHITE).drawOn(d);
    }

    @Override
    public void timePassed() {
    }
}
