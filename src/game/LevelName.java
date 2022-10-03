package game;

import biuoop.DrawSurface;
import objects.Sprite;

import java.awt.*;

/**
 * @author Adi Yadlin
 * Score indicator shows the name of the level to the player.
 */
public class LevelName implements Sprite {
    private String name;

    /**
     * Constructor.
     * @param name the name of the level
     */
    public LevelName(String name) {
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(500, 15, "Level Name: " + this.name, 15);
    }

    @Override
    public void timePassed() {

    }

    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
