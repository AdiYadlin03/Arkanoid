package objects;
import collision.Collidable;
import collision.Velocity;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import logic.HitListener;
import logic.HitNotifier;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adi Yadlin
 * This class represents blocks in the game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private java.awt.Color color;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * constructor.
     * @param rect the rectangle that is the base of the block
     * @param color the block's color
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.color = color;
        this.rect = rect;
    }

    /**
     * A getter for the rectangle of the block.
     * @return the rectangle of the block
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * this method updates the velocity after the hit.
     * @param collisionPoint the point of collision
     * @param currentVelocity the velocity before hit
     * @param hitter the ball that hit the object
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // up
        Line line1 = new Line(this.rect.getUpperLeft(),
                new Point(this.rect.getUpperLeft().getX() + this.rect.getWidth(), this.rect.getUpperLeft().getY()));
        // left
        Line line2 = new Line(this.rect.getUpperLeft(),
                new Point(this.rect.getUpperLeft().getX(), this.rect.getUpperLeft().getY()  + this.rect.getHeight()));
        // bottom
        Line line3 = new Line(new Point(this.rect.getUpperLeft().getX(),
                this.rect.getUpperLeft().getY()  + this.rect.getHeight()),
                new Point(this.rect.getUpperLeft().getX() + this.rect.getWidth(),
                        this.rect.getUpperLeft().getY()  + this.rect.getHeight()));
        // right
        Line line4 = new Line(new Point(this.rect.getUpperLeft().getX() + this.rect.getWidth(),
                this.rect.getUpperLeft().getY()),
                new Point(this.rect.getUpperLeft().getX() + this.rect.getWidth(),
                        this.rect.getUpperLeft().getY()  + this.rect.getHeight()));
        if (line1.checkPointOnLine(collisionPoint) && currentVelocity.getDy() > 0) {
            currentVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
        } else if (line3.checkPointOnLine(collisionPoint) && currentVelocity.getDy() < 0) {
            currentVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
        }
        if (line2.checkPointOnLine(collisionPoint) && currentVelocity.getDx() > 0) {
            currentVelocity = new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
        } else if (line4.checkPointOnLine(collisionPoint) && currentVelocity.getDx() < 0) {
            currentVelocity = new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * this method draws the block on the given surface.
     * @param surface to draw the block on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        //color the sides of the block black
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * this method adds the block to the gameLevel.
     * @param gameLevel the gameLevel to add to
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * This method removes the Block from the gameLevel.
     * @param gameLevel the gameLevel to remove the block from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies the listener that a block was hit.
     * @param hitter the ball that hit the block
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
