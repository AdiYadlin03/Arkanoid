// ID: 315126433

package geometry;

import objects.Ball;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adi Yadlin
 * this class represents a rectangle.
 */
public class Rectangle {
    private final double width;
    private final double height;
    private Point upperLeft;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft the upper lest corner of the rectangle
     * @param width the rectangle's width
     * @param height the rectangle's height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line the line to check intersections with
     * @return the list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // up
        Line line1 = new Line(this.getUpperLeft(),
                new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY()));
        // left
        Line line2 = new Line(this.getUpperLeft(),
                new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY()  + this.getHeight()));
        // bottom
        Line line3 = new Line(new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY()  + this.getHeight()),
                new Point(this.getUpperLeft().getX() + this.getWidth(),
                        this.getUpperLeft().getY()  + this.getHeight()));
        // right
        Line line4 = new Line(new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY()),
                new Point(this.getUpperLeft().getX() + this.getWidth(),
                        this.getUpperLeft().getY()  + this.getHeight()));
        Point intersection1 = line.intersectionWith(line1);
        Point intersection2 = line.intersectionWith(line2);
        Point intersection3 = line.intersectionWith(line3);
        Point intersection4 = line.intersectionWith(line4);
        List<Point> pointList = new ArrayList<>();
        if (intersection1 != null && this.notInList(intersection1, pointList)) {
            pointList.add(intersection1);
        }
        if (intersection2 != null && this.notInList(intersection2, pointList)) {
            pointList.add(intersection2);
        }
        if (intersection3 != null && this.notInList(intersection3, pointList)) {
            pointList.add(intersection3);
        }
        if (intersection4 != null && this.notInList(intersection4, pointList)) {
            pointList.add(intersection4);
        }
        return pointList;
    }

    /**
     * this method checks if the point is already on the list.
     * @param point the point to search for
     * @param pointList the list to search in
     * @return false if found, otherwise true
     */
    private boolean notInList(Point point, List<Point> pointList) {
        for (Point value : pointList) {
            if (value.equals(point)) {
                return false;
            }
        }
        return true;
    }

    /**
     * getter for the width of the rectangle.
     * @return the rectangle's width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getter for the height of the rectangle.
     * @return the rectangle's height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * a getter for the upper-left point of the rectangle.
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }


    /**
     * a setter for the upper left point of the rectangle.
     * @param point the current upper left corner
     */
    public void setUpperLeft(Point point) {
        this.upperLeft = point;
    }

    /**
     * checks if the ball is in the rectangle.
     * @param ball the ball to check
     * @return true if the ball is in the rectangle, otherwise false.
     */
    public boolean isBallInRect(Ball ball) {
        return this.getUpperLeft().getX() < ball.getX()
                && this.getUpperLeft().getX() + this.getWidth() > ball.getX()
                && this.getUpperLeft().getY() < ball.getY()
                && this.getUpperLeft().getY() + this.getHeight() > ball.getX();
    }

    /**
     * checks if the point is on the rectangle.
     * @param point the point to check
     * @return true if the point is on the rectangle, otherwise false
     */
    public boolean isPointOnRect(Point point) {
        if ((this.getUpperLeft().getX() <= point.getX())
                && (this.getUpperLeft().getX() + this.getWidth() >= point.getX())
                && (this.getUpperLeft().getY() <= point.getY())
                && (this.getUpperLeft().getY() + this.getHeight() >= point.getY())) {
            return true;
        }
        return false;
    }
}