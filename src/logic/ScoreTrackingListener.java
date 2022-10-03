package logic;
import game.Counter;
import objects.Ball;
import objects.Block;

/**
 * A listener to the score.
 */
public class ScoreTrackingListener implements HitListener {
    public static final int WIDTH = 800;
    public static final int TEXT_SIZE = 15;
    public static final int FRAME_SIZE = 10;
    public static final int MID_GUI_X = 350;
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter the initial score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
