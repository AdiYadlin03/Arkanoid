// ID: 315126433

package objects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.Collidable;
import collision.Velocity;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;

/**
 * @author Adi Yadlin
 * This class represents a paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private int speed;

    /**
     * constructor.
     * @param k the keyboard sensor
     * @param r the rectangle that gives
     */
    public Paddle(biuoop.KeyboardSensor k, Rectangle r, int speed) {
        this.keyboard = k;
        this.rect = r;
        this.speed = speed;
    }

    /**
     * move the paddle to the left.
     */
    public void moveLeft() {
        Velocity v = new Velocity(-this.speed, 0);
        Point p = v.applyToPoint(this.rect.getUpperLeft());
        if (p.getX() > 0) {
            this.rect.setUpperLeft(p);
        }
    }

    /**
     * move the paddle to the right.
     */
    public void moveRight() {
        Velocity v = new Velocity(this.speed, 0);
        Point p = v.applyToPoint(this.rect.getUpperLeft());
        if (p.getX() < 800 - this.getCollisionRectangle().getWidth()) {
            this.rect.setUpperLeft(p);
        }
    }

    /**
     * move the paddle according to the users input.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * draws the paddle.
     * @param surface the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.ORANGE);
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * returns the rectangle that is inside the paddle.
     * @return the rectangle that is inside the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * this method updates the velocity after the hit.
     * @param collisionPoint the point of collision
     * @param currentVelocity the velocity before hit
     * @param hitter the ball that hit the paddle
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = collisionPoint.getX() - this.rect.getUpperLeft().getX();
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        Point rightCorner = new Point(this.rect.getUpperLeft().getX() + this.rect.getWidth(),
                this.rect.getUpperLeft().getY());
        if (x == 0 && !this.rect.getUpperLeft().equals(collisionPoint)) {
            return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
        } else if (x <= this.rect.getWidth() / 5) {
            //section 1
            return Velocity.fromAngleAndSpeed(300, speed);
        } else if (x >= this.rect.getWidth() / 5 && x <= (this.rect.getWidth() / 5) * 2) {
            //section 2
            return Velocity.fromAngleAndSpeed(330, speed);
        } else if (x >= (this.rect.getWidth() / 5) * 2 && x <= (this.rect.getWidth() / 5) * 3) {
            //section 3
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
        } else if (x >= (this.rect.getWidth() / 5) * 3 && x <= (this.rect.getWidth() / 5) * 4) {
            //section 4
            return Velocity.fromAngleAndSpeed(30, speed);
        } else if (x >= (this.rect.getWidth() / 5) * 4 && x < this.rect.getWidth()
                || rightCorner.equals(collisionPoint)) {
            //section 5
            return Velocity.fromAngleAndSpeed(60, speed);
        } else if (x == this.rect.getWidth()) {
            return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
        }
        return null;
    }

    /**
     * Add this paddle to the game.
     * @param g the game to add to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * sets the keyboard sensor.
     * @param sensor the sensor from the gui
     */
    public void setKeyboard(KeyboardSensor sensor) {
        this.keyboard = sensor;
    }
}