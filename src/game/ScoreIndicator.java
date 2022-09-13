// ID: 315126433
package game;

import biuoop.DrawSurface;
import objects.Sprite;
import java.awt.Color;

/**
 * @author Adi Yadlin
 * Score indicator shows the score to the player.
 */
public class ScoreIndicator implements Sprite {
    public static final int WIDTH = 800;
    public static final int TEXT_SIZE = 15;
    public static final int FRAME_SIZE = 10;
    public static final int MID_GUI_X = 350;
    private Counter currentScore;

    /**
     * Constructor.
     * @param currentScore the initial score
     */
    public ScoreIndicator(Counter currentScore) {
        this.currentScore = currentScore;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, WIDTH, FRAME_SIZE * 2);
        d.setColor(Color.BLACK);
        d.drawRectangle(0, 0, WIDTH, FRAME_SIZE * 2);
        d.drawText(MID_GUI_X, TEXT_SIZE, "Score: " + this.currentScore.getValue(), TEXT_SIZE);
    }

    @Override
    public void timePassed() {
    }

    /**
     * add the score indicator to the gameLevel.
     * @param gameLevel the gameLevel to add to
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
