// ID: 315126433
package game;

import animation.*;
import biuoop.KeyboardSensor;
import collision.BallRemover;
import collision.BlockRemover;
import levels.LevelInformation;
import logic.ScoreTrackingListener;
import objects.Paddle;
import objects.SpriteCollection;
import collision.Collidable;
import objects.Block;
import objects.Ball;
import geometry.Rectangle;
import geometry.Point;
import objects.Sprite;
import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;

/**
 * @author Adi Yadlin
 * This class represents a game.
 */
public class GameLevel implements Animation {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int RADIUS = 5;
    public static final int PADDLE_HEIGHT = 15;
    public static final int FRAME_SIZE = 10;
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Paddle paddle;
    private Counter counterOfBlocks = new Counter(0);
    private Counter counterOfBalls = new Counter(0);
    private ScoreIndicator scoreIndicator;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks, AnimationRunner ar, Counter score) {
        this.levelInformation = levelInformation;
        this.keyboardSensor = ks;
        this.runner = ar;
        this.score = score;
    }

    /**
     * this is a getter for the game environment.
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * this method adds to the list of collidables.
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * this method adds to the list of sprites.
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle).
     * and add them to the game.
     */
    public void initialize() {
        //this.runner = new AnimationRunner(60, new GUI("Arkanoid", WIDTH, HEIGHT));
        //score board
        this.scoreIndicator = new ScoreIndicator(score);
        this.sprites.addSprite(this.levelInformation.getBackground());
        scoreIndicator.addToGame(this);
        LevelName levelName = new LevelName(this.levelInformation.levelName());
        levelName.addToGame(this);
        //initialize level
        //paddle
        this.paddle = new Paddle(this.keyboardSensor,
                new Rectangle(new Point((double) (WIDTH / 2) - (double) (this.levelInformation.paddleWidth() / 2),
                600 - PADDLE_HEIGHT - FRAME_SIZE), this.levelInformation.paddleWidth(), PADDLE_HEIGHT),
                this.levelInformation.paddleSpeed());
        this.paddle.addToGame(this);
        //balls
        buildBalls();
        //sides
        buildSides();
        //blocks
        buildBlocks();
        //the next thing to happen is the run of the game
        this.running = true;
    }

    /**
     * this method creates the blocks for the game.
     */
    private void buildBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, this.counterOfBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        for (int i = 0; i < this.levelInformation.blocks().size(); i++) {
            Block block = this.levelInformation.blocks().get(i);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            block.addToGame(this);
            this.counterOfBlocks.increase(1);
        }
    }

    /**
     * This method creates the 3 balls and adds them to the game.
     */
    private void buildBalls() {
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point((double) (WIDTH / 2), 600 - PADDLE_HEIGHT - 2 * FRAME_SIZE),
                    RADIUS, Color.WHITE);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
            this.counterOfBalls.increase(1);
        }
    }

    /**
     * This method creates the walls for the game and adds them to the game.
     */
    private void buildSides() {
        BallRemover ballRemover = new BallRemover(this, this.counterOfBalls);
        Block top = new Block(new Rectangle(new Point(0, FRAME_SIZE * 2), WIDTH, FRAME_SIZE), Color.GRAY);
        Block left = new Block(new Rectangle(new Point(0, FRAME_SIZE * 2), FRAME_SIZE, HEIGHT), Color.GRAY);
        Block bottom = new Block(new Rectangle(new Point(FRAME_SIZE, HEIGHT + FRAME_SIZE), WIDTH, FRAME_SIZE),
                Color.GRAY);
        bottom.addHitListener(ballRemover);
        Block right = new Block(new Rectangle(new Point(WIDTH - FRAME_SIZE, FRAME_SIZE * 2), FRAME_SIZE, HEIGHT),
                Color.GRAY);
        left.addToGame(this);
        bottom.addToGame(this);
        right.addToGame(this);
        top.addToGame(this);
    }

    /**
     * Run the game- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * This method removes the collidable c from the game.
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.remove(c);
    }

    /**
     * This method removes the sprite c from the game.
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * a getter for the counter of blocks.
     * @return the number of blocks in the game.
     */
    public Counter getCounterOfBlocks() {
        return this.counterOfBlocks;
    }

    /**
     * a getter for the counter of balls.
     * @return the number of balls in the game.
     */
    public Counter getCounterOfBalls() {
        return counterOfBalls;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //pausing
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                    new PauseScreen(this.keyboardSensor)));
        }
        //background
        this.levelInformation.getBackground().drawOn(d);
        //objects in the game
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.counterOfBlocks.getValue() == 0 || this.counterOfBalls.getValue() == 0) {
            this.running = false;
            if (this.counterOfBlocks.getValue() == 0) {
                this.score.increase(100);
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * score getter.
     * @return the current score
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * return true is there are no more balls in the game. else otherwise.
     * @return true is there are no more balls in the game. else otherwise.
     */
    public boolean isNoMoreBalls() {
        return this.counterOfBalls.getValue() == 0;
    }
}