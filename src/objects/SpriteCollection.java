package objects;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adi Yadlin
 * This class represents a collection of sprites.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<>();

    /**
     * add a sprite to the list.
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d the surface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite: this.sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * This method removes the sprite c from the game.
     * @param s the sprite to remove
     */
    public void remove(Sprite s) {
        this.sprites.remove(s);
    }
}