// ID: 315126433
package levels;

import biuoop.DrawSurface;
import geometry.Point;
import objects.Block;
import objects.Sprite;

import java.awt.Color;

/**
 * @author Adi Yadlin
 * This class contains the background for the forth level.
 */
public class Background4 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        java.awt.Color color = new Color(23, 135, 207);
        new Block(new geometry.Rectangle(new Point(0, 0), 800, 600),
                color).drawOn(d);
        d.setColor(new Color(186, 186, 186, 255));
        //lines
        d.drawLine(100, 400, 70, 600);
        d.drawLine(108, 400, 78, 600);
        d.drawLine(116, 400, 86, 600);
        d.drawLine(124, 400, 94, 600);
        d.drawLine(132, 400, 102, 600);
        d.drawLine(140, 400, 110, 600);
        d.drawLine(148, 400, 118, 600);
        d.drawLine(156, 400, 126, 600);
        d.drawLine(164, 400, 134, 600);
        d.drawLine(172, 400, 142, 600);
        //cloud1
        d.setColor(new Color(186, 186, 186, 255));
        d.fillCircle(90, 400, 20);
        d.fillCircle(110, 420, 22);
        d.setColor(new Color(174, 177, 177, 255));
        d.fillCircle(125, 390, 25);
        d.setColor(new Color(153, 155, 156));
        d.fillCircle(160, 400, 27);
        d.fillCircle(140, 420, 20);
        //lines
        d.setColor(new Color(186, 186, 186, 255));
        d.drawLine(610, 510, 580, 600);
        d.drawLine(618, 510, 588, 600);
        d.drawLine(626, 510, 596, 600);
        d.drawLine(634, 510, 604, 600);
        d.drawLine(642, 510, 612, 600);
        d.drawLine(650, 510, 620, 600);
        d.drawLine(658, 510, 628, 600);
        d.drawLine(666, 510, 636, 600);
        d.drawLine(674, 510, 644, 600);
        d.drawLine(682, 510, 652, 600);
        //cloud2
        d.setColor(new Color(186, 186, 186, 255));
        d.fillCircle(600, 500, 20);
        d.fillCircle(620, 530, 22);
        d.setColor(new Color(174, 177, 177, 255));
        d.fillCircle(638, 510, 25);
        d.setColor(new Color(153, 155, 156));
        d.fillCircle(675, 520, 27);
        d.fillCircle(650, 525, 20);
    }

    @Override
    public void timePassed() {

    }
}
