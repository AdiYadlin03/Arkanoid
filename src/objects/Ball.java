package objects;
import game.GameEnvironment;
import collision.Velocity;
import collision.CollisionInfo;
import game.GameLevel;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import logic.HitListener;
import logic.HitNotifier;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adi Yadlin
 * This class represents a ball.
 */
public class Ball implements Sprite, HitNotifier {
    public static final int PADDLE_Y = 575;
    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;
    public static final int SIDES_SIZE = 10;
    private Point center;
    private final int radius;
    private final java.awt.Color color;
    private Velocity velocity;
    private static final int MAX_RADIUS = 50;
    private static final int MIN_RADIUS = 5;
    private GameEnvironment gameEnvironment;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * constructor.
     * @param center the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = this.handleRadius(r);
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * constructor.
     * @param x the x of the center of the ball
     * @param y the y of the center of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = this.handleRadius(r);
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * a getter for x.
     * @return the x of the center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * a getter for y.
     * @return the y of the center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * a getter for the radius.
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * a getter for the color.
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draws the ball on the surface.
     * @param surface the surface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * a setter for velocity.
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * a setter for velocity.
     * @param dx the dx of the new velocity
     * @param dy the dy of the new velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * a getter for velocity.
     * @return the balls velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * moves the ball according to the game environment.
     */
    public void moveOneStep() {
        Point tempPoint = new Point(this.getX(), this.getY());
        CollisionInfo info = this.getCollisionInfo();
        int counter = 0;
        while (info != null && counter < 3) {
            //checks for the number of rectangles in the collision
            int numOfRectsInCollision = this.countRects(info.collisionPoint());
            if (numOfRectsInCollision == 3) {
                this.velocity = new Velocity(this.velocity.getDx() * (-1), this.velocity.getDy() * (-1));
                break;
            } else if (numOfRectsInCollision == 2) {
                // there are 2 rectangles involved in the collision
                if ((info.collisionPoint().getY() == (HEIGHT - SIDES_SIZE))
                        || (info.collisionPoint().getY() == SIDES_SIZE)
                        || (info.collisionPoint().getX() == (WIDTH - SIDES_SIZE))
                        || (info.collisionPoint().getX() == SIDES_SIZE)) {
                    //on the sides
                    this.velocity = new Velocity(this.velocity.getDx() * (-1), this.velocity.getDy() * (-1));
                } else {
                    //2 corner of two blocks
                    this.velocity = new Velocity(this.velocity.getDx(), this.velocity.getDy() * (-1));
                }
                break;
            } else {
                //regular hit in a block
                this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
                this.center = this.velocity.applyToPoint(this.center);
                info = this.getCollisionInfo();
                counter++;
            }
        }
        if (counter == 0) {
            this.center = this.velocity.applyToPoint(this.center);
        } else if (counter == 3) {
            this.velocity = new Velocity(this.velocity.getDx() * (-1), this.velocity.getDy() * (-1));
            this.center = this.velocity.applyToPoint(tempPoint);
        }
        if (info != null) {
            this.getOut(info.collisionObject().getCollisionRectangle());
        }
    }

    /**
     * counts the number of rectangles involved in the collision.
     * @param collisionPoint the point of collision
     * @return the number of rectangles involved in the collision
     */
    private int countRects(Point collisionPoint) {
        int counter = 0;
        for (int i = 0; i < this.gameEnvironment.getCollidables().size(); i++) {
            if (this.gameEnvironment.getCollidables().get(i).getCollisionRectangle().isPointOnRect(collisionPoint)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * this method gets the collision information.
     * @return collision information
     */
    private CollisionInfo getCollisionInfo() {
        Velocity radiusVelocity = getVelocityWithRadius();
        Point futurePoint = radiusVelocity.applyToPoint(this.center);
        Line trajectory = new Line(this.center, futurePoint);
        return this.gameEnvironment.getClosestCollision(trajectory);
    }

    /**
     * calculates the velocity with the radius included.
     * @return the velocity with the radius
     */
    private Velocity getVelocityWithRadius() {
        Velocity radiusVelocity;
        if (this.velocity.getDx() > 0) {
            radiusVelocity = new Velocity(this.velocity.getDx() + this.radius, this.velocity.getDy());
        } else {
            radiusVelocity = new Velocity(this.velocity.getDx() - this.radius, this.velocity.getDy());
        }
        if (this.velocity.getDy() > 0) {
            radiusVelocity = new Velocity(radiusVelocity.getDx(), this.velocity.getDy() + this.radius);
        } else {
            radiusVelocity = new Velocity(radiusVelocity.getDx(), this.velocity.getDy() - this.radius);
        }
        return radiusVelocity;
    }

    /**
     * a getter for the center of the ball.
     * @return the center point of the ball
     */
    public Point getCenter() {
        return center;
    }

    /**
     * changes the radius to default values if needed.
     * @param r the wanted radius
     * @return the new radius
     */
    private int handleRadius(int r) {
        if (r <= 0) {
            return MIN_RADIUS;
        } else if (r > MAX_RADIUS) {
            return MAX_RADIUS;
        }
        return r;
    }

    /**
     * a setter for the center point of the ball.
     * @param point the new center
     */
    public void setCenter(Point point) {
        this.center = point;
    }

    /**
     * A getter for the game environment.
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
        //this.getOut();
    }

    /**
     * adds the ball to he gameLevel and sets the gameLevel environment for the ball.
     * @param gameLevel the gameLevel to add to
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        this.gameEnvironment = gameLevel.getEnvironment();
    }

    /**
     * if the ball is inside a rectangle, this method needs to get it out.
     * @param rectangle the rectangle to check
     */
    public void getOut(Rectangle rectangle) {
        if (rectangle.isBallInRect(this) && this.center.getY() > PADDLE_Y) {
            this.center = new Point(this.center.getX(), PADDLE_Y - 1.5 * this.radius);
            this.velocity = new Velocity(this.velocity.getDx(), this.velocity.getDy() * (-1));
            this.center = this.velocity.applyToPoint(this.center);
        }
    }

    /**
     * a setter for the game environment.
     * @param gameEnv the new game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnv) {
        this.gameEnvironment = gameEnv;
    }

    /**
     * This method removes the Ball from the gameLevel.
     * @param gameLevel the gameLevel to remove the block from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
}
