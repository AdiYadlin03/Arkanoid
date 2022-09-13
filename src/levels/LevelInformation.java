// ID: 315126433
package levels;

import collision.Velocity;
import objects.Block;
import objects.Sprite;
import java.util.List;

/**
 * @author Adi Yadlin
 * This is the interface for the levels of the game.
 */
public interface LevelInformation {
    /**
     * The number of balls.
     * @return number of balls
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls().
     * @return
     */
    List<Velocity> initialBallVelocities();

    /**
     * the paddle speed.
     * @return paddle speed
     */
    int paddleSpeed();

    /**
     * the paddle width.
     * @return paddle width
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return level name
     */
    String levelName();
    // Returns a sprite with the background of the level

    /**
     * Get the background.
     * @return the background
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return the blocks for the level
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size().
     * @return the number of blocks to remove to finish the level.
     */
    int numberOfBlocksToRemove();
}