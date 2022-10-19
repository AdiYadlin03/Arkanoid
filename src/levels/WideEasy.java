package levels;

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
 * This is the second level- Wide Easy.
 */
public class WideEasy implements LevelInformation{
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(Velocity.fromAngleAndSpeed(10, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(50, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(40, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(30, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(20, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(-10, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(-50, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(-40, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(-30, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(-20, 5));
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background2();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        blockList.add(new Block(new Rectangle(new Point(10, 250), 52, 25), Color.RED));
        blockList.add(new Block(new Rectangle(new Point(62, 250), 52, 25), Color.RED));
        blockList.add(new Block(new Rectangle(new Point(114, 250), 52, 25), Color.ORANGE));
        blockList.add(new Block(new Rectangle(new Point(166, 250), 52, 25), Color.ORANGE));
        blockList.add(new Block(new Rectangle(new Point(218, 250), 52, 25), Color.YELLOW));
        blockList.add(new Block(new Rectangle(new Point(270, 250), 52, 25), Color.YELLOW));
        blockList.add(new Block(new Rectangle(new Point(322, 250), 52, 25), Color.green));
        blockList.add(new Block(new Rectangle(new Point(374, 250), 52, 25), Color.green));
        blockList.add(new Block(new Rectangle(new Point(426, 250), 52, 25), Color.green));
        blockList.add(new Block(new Rectangle(new Point(478, 250), 52, 25), Color.blue));
        blockList.add(new Block(new Rectangle(new Point(530, 250), 52, 25), Color.blue));
        blockList.add(new Block(new Rectangle(new Point(582, 250), 52, 25), Color.pink));
        blockList.add(new Block(new Rectangle(new Point(634, 250), 52, 25), Color.pink));
        blockList.add(new Block(new Rectangle(new Point(686, 250), 52, 25), Color.cyan));
        blockList.add(new Block(new Rectangle(new Point(738, 250), 52, 25), Color.cyan));
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public int scoreToWinLevel() {
        return (this.numberOfBlocksToRemove() * 5 + POINTS_FOR_PASSING_LEVEL);
    }
}
