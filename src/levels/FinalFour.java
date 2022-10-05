package levels;

import collision.Velocity;
import geometry.Point;
import objects.Block;
import objects.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FinalFour implements LevelInformation{
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(Velocity.fromAngleAndSpeed(40, 3));
        velocityList.add(Velocity.fromAngleAndSpeed(-30, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(0, 4));
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Background4();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        int width = 52, height = 20;
        java.awt.Color[] colors = {Color.cyan, Color.pink, Color.white, Color.green, Color.yellow, Color.red,
                Color.GRAY};
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Block block = new Block(new geometry.Rectangle(new Point(790 - width - (width * j),
                        250 - (height * i)), width, height), colors[i]);
                blockList.add(block);
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
