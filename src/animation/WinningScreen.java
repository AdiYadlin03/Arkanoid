// ID: 315126433
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * @author Adi Yadlin
 * This is an end screen of the game if the player won.
 */
public class WinningScreen implements Animation{
    private KeyboardSensor keyboardSensor;
    private boolean stop;

    public WinningScreen(KeyboardSensor keyboardSensor) {
        this.keyboardSensor = keyboardSensor;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(200, d.getHeight() / 2, "You Win! Your Score is 1205", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
