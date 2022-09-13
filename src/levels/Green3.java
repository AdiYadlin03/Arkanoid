package levels;

import collision.Velocity;
import geometry.Point;
import objects.Block;
import objects.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Green3 implements LevelInformation{
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(Velocity.fromAngleAndSpeed(40, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(-40, 5));
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Background3();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        int width = 52, height = 20;
        java.awt.Color[] colors = {Color.WHITE, Color.BLUE, Color.YELLOW, Color.red, Color.GRAY};
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6 + i; j++) {
                Block block = new Block(new geometry.Rectangle(new Point(790 - width - (width * j),
                        250 - (height * i)), width, height), colors[i]);
                blockList.add(block);
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
