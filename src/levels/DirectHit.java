// ID: 315126433
package levels;

import biuoop.DrawSurface;
import collision.Velocity;
import geometry.Point;
import geometry.Rectangle;
import objects.Block;
import objects.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adi Yadlin
 * This is the first level- Direct Hit.
 */
public class DirectHit implements LevelInformation{

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(new Velocity(0, -8));
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Background1();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        blockList.add(new Block(new Rectangle(new Point(385, 140), 30, 30), Color.RED));
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
