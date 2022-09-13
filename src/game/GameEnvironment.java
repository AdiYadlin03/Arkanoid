// ID: 315126433
package game;

import collision.Collidable;
import geometry.Point;
import geometry.Line;
import collision.CollisionInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adi Yadlin
 * This class represents a game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<>();

    /**
     * add the given collidable to the environment.
     * @param c the collidable to add to the list.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory the line the object is moving on
     * @return the collision information
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double[] distances = new double[this.collidables.size()];
        Point[] intersections = new Point[this.collidables.size()];
        for (int i = 0; i < this.collidables.size(); i++) {
            Point point = trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle());
            intersections[i] = point;
            distances[i] = trajectory.start().distance(point);
        }
        boolean flag = false;
        for (Point point: intersections) {
            if (point != null) {
                flag = true;
            }
        }
        if (!flag) {
            return null;
        }
        double min = 10000000000000.0;
        int indexOfMin = -1;
        for (int i = 0; i < intersections.length; i++) {
            if (distances[i] < min) {
                min = distances[i];
                indexOfMin = i;
            }
        }
        if (indexOfMin != -1) {
            return new CollisionInfo(intersections[indexOfMin], this.collidables.get(indexOfMin));
        }
        return null;
    }

    /**
     * a getter for the collidables.
     * @return the collidables
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * This method removes the collidable c from the game.
     * @param c the collidable to remove
     */
    public void remove(Collidable c) {
        this.collidables.remove(c);
    }
}