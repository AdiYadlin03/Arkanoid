// ID: 315126433
package animation;

import biuoop.DrawSurface;
import objects.SpriteCollection;
import java.awt.Color;


/**
 * @author Adi Yadlin
 * This class displays the countdown on the screen.
 */
public class CountdownAnimation implements Animation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop = false;;
    private long startMillisecond;
    private boolean firstFrame = true;

    /**
     * Constructor.
     * @param numOfSeconds number of seconds for the count
     * @param countFrom start counting back from this number
     * @param gameScreen the objects on the screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        //this.sleepTime = this.numOfSeconds / this.countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //draw background
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        this.gameScreen.drawAllOn(d);
        //start timer in the first frame
        if (this.firstFrame) {
            this.startMillisecond = System.currentTimeMillis();
            this.firstFrame = false;
        }
        //get current time
        long currentTimeMillis = System.currentTimeMillis();
        double timeToCount = this.numOfSeconds / (double) this.countFrom;
        int currentNumber = (int) ((double) (this.countFrom + 1)
                - (double) (currentTimeMillis - this.startMillisecond) / (timeToCount * 1000));
        //draw countdown
        d.setColor(Color.RED);
        d.drawText(380, 400, String.valueOf(currentNumber), 80);
        if ((double) (currentTimeMillis - this.startMillisecond) > this.numOfSeconds * 1000) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
