// ID: 315126433
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * @author Adi Yadlin
 * This is an end screen of the game if the player lost.
 */
public class GameOver implements Animation {
    private int score;
    private KeyboardSensor keyboardSensor;
    private boolean stop;

    /**
     * Constructor
     * @param keyboardSensor the keyboard sensor
     * @param score the final score
     */
    public GameOver(KeyboardSensor keyboardSensor, int score) {
        this.keyboardSensor = keyboardSensor;
        this.stop = false;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 4, d.getHeight() / 2, "Game Over. Your score is " + this.score, 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
