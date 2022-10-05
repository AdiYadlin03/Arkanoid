package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import objects.Block;
import java.awt.*;

public class StartingScreen extends Screen{

    public StartingScreen(KeyboardSensor keyboardSensor) {
        this.keyboardSensor = keyboardSensor;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        super.doOneFrame(d);
        d.drawText(d.getWidth() /4, d.getHeight() / 3, "Welcome to Arkanoid!", 32);
        d.drawText(d.getWidth() /4, d.getHeight() / 3 + 50, "Press enter to start playing", 20);
        d.drawText(d.getWidth() /4, d.getHeight() / 3 + 80, "Press p to pause the game", 20);
        d.drawText(d.getWidth() /4, d.getHeight() / 3 + 130, "Enjoy the game!", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
