package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import objects.Block;

import java.awt.*;

public class StartingScreen implements Animation{
    private KeyboardSensor keyboardSensor;
    private boolean stop;

    public StartingScreen(KeyboardSensor keyboardSensor) {
        this.keyboardSensor = keyboardSensor;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        new Block(new geometry.Rectangle(new Point(0, 0), 800, 600), Color.pink).drawOn(d);
        d.setColor(Color.white);
        d.drawText(d.getWidth() /4, d.getHeight() / 2, "Welcome to Arkanoid!", 32);
        d.drawText(d.getWidth() /4, d.getHeight() / 2 + 50, "Press enter to start", 20);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
