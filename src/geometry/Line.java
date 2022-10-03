package geometry;

/**
 * @author Adi Yadlin
 * This class represents a line.
 */
public class Line {
    private final Point start;
    private final Point end;
    private final double m;

    /**
     * this is the constructor for two points.
     * @param start the start point of the line
     * @param end the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.m = (this.end().getY() - this.start().getY()) / (this.end().getX() - this.start().getX());
    }

    /**
     * this is the constructor for 2 pairs of x,y values.
     * @param x1 the x value for the first point
     * @param y1 the y value for the first point
     * @param x2 the x value for the second point
     * @param y2 the y value for the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.m = (this.end().getY() - this.start().getY()) / (this.end().getX() - this.start().getX());
    }

    /**
     * this method calculates the length of the line.
     * @return distance between the start and end point
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * calculates the middle point of the line.
     * @return middle point of the line
     */
    public Point middle() {
        double middleX = (this.end().getX() + this.start().getX()) / 2;
        double middleY =  (this.end().getY() + this.start().getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * gets the start point of the line.
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * gets the end point of the line.
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * gets the slope of the line.
     * @return the end point of the line
     */
    public double getM() {
        return this.m;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     * @param other the line to compare with
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     * @param other - the second line to compare
     * @return the intersection point or null if there isn't any intersection
     */
    public Point intersectionWith(Line other) {
        if (other == null) {
            return null;
        }
        double m1 = this.getM();
        double m2 = other.getM();
        // find b from y = mx + b
        double b1 = this.start().getY() - m1 * this.start.getX();
        double b2 = other.start().getY() - m2 * other.start.getX();
        if (m1 == m2) {
            return getPoint(other);
        }
        Point perpendicular = checkPerpendicular(other, b1, b2);
        if (perpendicular != null) {
            return perpendicular;
        }
        double intersectionX = (b2 - b1) / (m1 - m2);
        double intersectionY = m1 * intersectionX + b1;
        // check if the lines are in the form of x = b
        if (this.start().getX() - this.end().getX() == 0 && other.start().getX() - other.end().getX() == 0) {
            return getPoint(other);
        }
        // check if the intersection is on both of the lines
        if (this.checkPointOnLine(new Point(intersectionX, intersectionY))
                && other.checkPointOnLine(new Point(intersectionX, intersectionY))) {
            return new Point(intersectionX, intersectionY);
        }
        // intersection wasn't found
        return null;
    }

    /**
     * this method looks for an intersection in perpendicular lines.
     * @param other the line to check intersection with
     * @param b1 the b of this line from y = mx + b
     * @param b2 the b of the other line from y = mx + b
     * @return the intersection point if there is one, else null
     */
    private Point checkPerpendicular(Line other, double b1, double b2) {
        Point perpendicular;
        if (this.start().getX() - this.end().getX() == 0 && other.start().getX() - other.end().getX() != 0) {
            // this is x = b and other is not
            perpendicular = new Point(this.start().getX(), (other.m * this.start().getX()) + b2);
            if (this.checkPointOnLine(perpendicular) && other.checkPointOnLine(perpendicular)) {
                return perpendicular;
            }
        } else if (this.start().getX() - this.end().getX() != 0 && other.start().getX() - other.end().getX() == 0) {
            // other is x = b and this is not
            perpendicular = new Point(other.start().getX(), (this.m * other.start().getX()) + b1);
            if (other.checkPointOnLine(perpendicular) && this.checkPointOnLine(perpendicular)) {
                return perpendicular;
            }
        }
        return null;
    }

    /**
     * this method checks if the point is on the line.
     * @param point point to check for
     * @return true if it is on the line, else false
     */
    public boolean checkPointOnLine(Point point) {
        return (point.getX() >= this.start().getX() && point.getX() <= this.end().getX()
                || point.getX() <= this.start().getX() && point.getX() >= this.end().getX())
                && ((point.getY() >= this.start().getY() && point.getY() <= this.end().getY())
                || (point.getY() <= this.start().getY() && point.getY() >= this.end().getY()));
    }

    /**
     * checks if the start or end of the two lines are equal.
     * @param other the line to compare with
     * @return the equal point if one was found, null otherwise
     */
    private Point getPoint(Line other) {
        if (this.start().equals(other.start()) && !this.checkPointOnLine(other.end())
                && !other.checkPointOnLine(this.end())) {
            return this.start();
        }
        if (this.start().equals(other.end()) && !this.checkPointOnLine(other.start())
                && !other.checkPointOnLine(this.end())) {
            return this.start();
        }
        if (this.end().equals(other.end()) && !this.checkPointOnLine(other.start())
                && !other.checkPointOnLine(this.start())) {
            return this.end();
        }
        if (this.end().equals(other.start()) && !this.checkPointOnLine(other.end())
                && !other.checkPointOnLine(this.start())) {
            return this.end();
        }
        return null;
    }

    /**
     * this method checks if the two lines are equals.
     * @param other the line to compare with
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (start.equals(other.start()) && end.equals(other.end()))
                || (start.equals(other.end()) && end.equals(other.start()));
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect the rectangle to check intersections with
     * @return returns the intersection point closest to start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> pointList = rect.intersectionPoints(this);
        return getClosestPoint(pointList);
    }

    /**
     * finds the point closest to start of the line.
     * @param pointList the list of points to check
     * @return the point closest to upperLeft
     */
    private Point getClosestPoint(java.util.List<Point> pointList) {
        double[] distances = new double[pointList.size()];
        for (int i = 0; i < pointList.size(); i++) {
            distances[i] = this.start().distance(pointList.get(i));
        }
        double min = 10000000000000.0;
        int indexOfMin = -1;
        for (int i = 0; i < pointList.size(); i++) {
            if (distances[i] < min) {
                min = distances[i];
                indexOfMin = i;
            }
        }
        if (indexOfMin != -1) {
            return pointList.get(indexOfMin);
        }
        // not found
        return null;
    }
}