package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private Boolean stop = false;
    private boolean isAlreadyPressed = true;

    /**
     * Constructor.
     * @param sensor keyboard sensor
     * @param key key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation){
        this.animation = animation;
        this.key = key;
        this.keyboardSensor = sensor;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        this.animation.doOneFrame(d);
        if (!this.isAlreadyPressed && this.keyboardSensor.isPressed(this.key)) {
            this.stop = true;
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

