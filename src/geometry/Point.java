package geometry;

/**
 * @author Adi Yadlin
 * This class represents a point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * this is the building method of the point.
     * @param x is the x value of the point
     * @param y is the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * this method calculates the distance between the points.
     * @param other the point to calculate the distance from
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        if (other == null) {
            return 100000000000000.0;
        }
        return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * this method checks if the two points have equal x and y values.
     * @param other the point to compare
     * @return true if the other point and this point have equal values and false if they dont
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        double epsilon = Math.pow(10, -15);
        return Math.abs(this.x - other.getX()) < epsilon && Math.abs(this.y - other.getY()) < epsilon;
    }

    /**
     * this function returns the X value of the point.
     * @return x value of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * this function returns the Y value of the point.
     * @return y value of the point
     */
    public double getY() {
        return this.y;
    }
}
