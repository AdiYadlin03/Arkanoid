// ID: 315126433

package objects;

import biuoop.DrawSurface;

/**
 * @author Adi Yadlin
 * This class represents a Sprite object.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d the surface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}