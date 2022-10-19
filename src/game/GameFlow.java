package game;

import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

import java.util.List;

public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score = new Counter(0);

    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel (levelInfo, this.keyboardSensor,
                    this.animationRunner, this.score);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
            // add the score from the level
            //this.score.increase(this.score.getValue() + level.getScore().getValue());
            // if there are no more balls in the game
            if (level.isNoMoreBalls()) {
                break;
            }

        }
    }

    /**
     * return the score in the end of the game.
     * @return the score
     */
    public int getScore() {
        return this.score.getValue();
    }
}
