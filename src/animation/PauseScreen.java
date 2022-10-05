package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Adi Yadlin
 * This class represents the pause screen.
 */
public class PauseScreen extends Screen {

    /**
     * Constructor.
     * @param k the keyboard sensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboardSensor = k;
        this.stop = false;
    }

    /**
     * Draw frame of pause screen.
     * @param d the surface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        super.doOneFrame(d);
        d.drawText(d.getWidth() / 6, d.getHeight() / 2, "Paused - press space to continue", 32);
    }

    /**
     * returns a boolean value to know if the game needs to stop.
     * @return this.stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
