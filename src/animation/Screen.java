package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import objects.Block;

import java.awt.*;

/**
 * @author Adi Yadlin
 * This class represents a general screen
 */
public class Screen implements Animation{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    protected KeyboardSensor keyboardSensor;
    protected boolean stop;

    public Screen(KeyboardSensor keyboardSensor, boolean stop){
        this.keyboardSensor = keyboardSensor;
        this.stop = stop;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        // draw background
        new Block(new geometry.Rectangle(new Point(0, 0), WIDTH, HEIGHT), Color.pink).drawOn(d);
        // define color for future text
        d.setColor(Color.white);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
