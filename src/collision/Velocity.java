package collision;
import geometry.Point;

/**
 * @author Adi Yadlin
 * this class represents velocity.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     * @param dx the dx of the velocity
     * @param dy the dy of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * a getter for dx.
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * a getter for dy.
     * @return dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p point to change
     * @return new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * this method converts from speed and angle to dx, dy (to override the first constructor).
     * @param angle the wanted angle
     * @param speed the wanted speed
     * @return the new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }
}
