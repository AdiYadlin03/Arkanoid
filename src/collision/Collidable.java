package collision;
import geometry.Rectangle;
import geometry.Point;
import objects.Ball;


/**
 * @author Adi Yadlin
 * This interface represents all objects that can be collided with.
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint the point of collision
     * @param currentVelocity the velocity before hit
     * @param hitter the ball that hit the object
     * @return the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}