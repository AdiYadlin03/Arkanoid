package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String continueKey;
    private Animation animation;
    private Boolean stop = false;
    private boolean isAlreadyPressed = true;

    /**
     * Constructor.
     * @param sensor keyboard sensor
     * @param continueKey this is the continue key, if pressed we need to go back to the game from the pausing screen
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String continueKey, Animation animation){
        this.animation = animation;
        this.continueKey = continueKey;
        this.keyboardSensor = sensor;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        this.animation.doOneFrame(d);
        if (this.isAlreadyPressed && this.keyboardSensor.isPressed(this.continueKey)) {
            this.stop = true;
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

