package collision;
import game.GameLevel;
import logic.HitListener;
import game.Counter;
import objects.Block;
import objects.Ball;

/**
 * @author Adi Yadlin
 * a BallRemover is in charge of removing balls from the gameLevel, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param gameLevel the gameLevel for the counter
     * @param remainingBalls the balls that were remaining
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Balls that hit the death block at the bottom of the screen
     * should be removed.
     * @param beingHit the block that is being hit
     * @param hitter the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        hitter.removeHitListener(this);
        this.gameLevel.getCounterOfBalls().decrease(1);
    }
}