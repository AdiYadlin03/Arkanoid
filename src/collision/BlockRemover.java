// ID: 315126433

package collision;
import game.GameLevel;
import logic.HitListener;
import game.Counter;
import objects.Block;
import objects.Ball;

/**
 * @author Adi Yadlin
 * a BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param gameLevel the gameLevel for the counter
     * @param remainingBlocks the blocks that were remaining
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed
     * from the gameLevel. Remember to remove this listener from the block
     * that is being removed from the gameLevel.
     * @param beingHit the block that is being hit
     * @param hitter the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.gameLevel.getCounterOfBlocks().decrease(1);
    }
}