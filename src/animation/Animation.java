// ID: 315126433
package animation;

import biuoop.DrawSurface;

/**
 * This is the interface of the animation in the game.
 */
public interface Animation {
    /**
     * Draw and notify all the objects in the frame.
     * @param d the surface to draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Check if the game is over or not.
     * @return true to stop the game, false otherwise.
     */
    boolean shouldStop();
}