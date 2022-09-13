// ID: 315126433
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
    @Override
    public void drawOn(DrawSurface d) {
        java.awt.Color color = new Color(42, 129, 21);
        new Block(new Rectangle(new Point(0, 0), 800, 600),
                color).drawOn(d);
    }

    @Override
    public void timePassed() {
    }
}