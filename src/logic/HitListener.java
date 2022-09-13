// ID: 315126433
package logic;
import objects.Ball;
import objects.Block;

/**
 * @author Adi Yadlin
 * This represents a listener for the hits.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block that is being hit
     * @param hitter the ball that hits the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
