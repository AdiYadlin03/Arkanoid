package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


/**
 * @author Adi Yadlin
 * This is an end screen of the game if the player lost.
 */
public class LosingScreen extends Screen {
    private final int score;
    /**
     * Constructor
     * @param keyboardSensor the keyboard sensor
     * @param score the final score
     */
    public LosingScreen(KeyboardSensor keyboardSensor, int score) {
        super(keyboardSensor, false);
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        super.doOneFrame(d);
        d.drawText(d.getWidth() / 5, d.getHeight() / 2, "Game Over. Your score is " + this.score, 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
