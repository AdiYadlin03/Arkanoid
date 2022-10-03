package collision;
import geometry.Point;

/**
 * @author Adi Yadlin
 * this class holds the collision information.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObjet;

    /**
     * constructor.
     * @param point the point of collision
     * @param collidable the object to collide with
     */
    public CollisionInfo(Point point, Collidable collidable) {
        this.collisionPoint = point;
        this.collisionObjet = collidable;
    }

    /**
     * the point at which the collision occurs.
     * @return the point at which the collision occurs
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObjet;
    }
}