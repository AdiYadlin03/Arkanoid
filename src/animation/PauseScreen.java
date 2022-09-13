// ID: 315126433
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Adi Yadlin
 * This class represents the pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     * @param k the keyboard sensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * Draw frame of pause screen.
     * @param d the surface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * returns a boolean value to know if the game needs to stop.
     * @return this.stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
