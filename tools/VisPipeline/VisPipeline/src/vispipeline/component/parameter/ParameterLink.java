/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipeline.component.parameter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.CubicCurve2D;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ParameterLink {

    public ParameterLink(Parameter param) {
        this.param = param;
        this.freeposition = new Point();
    }

    public void draw(Graphics2D g2) {
        g2.setStroke(new BasicStroke(3.0f));
        g2.setColor(Color.ORANGE);

        float p1x = param.getPosition().x + (Parameter.SIZE / 2);
        float p1y = param.getPosition().y + (Parameter.SIZE / 2);
        float p2x = freeposition.x + (Parameter.SIZE / 2);
        float p2y = freeposition.y + (Parameter.SIZE / 2);

        //draw a spline curve
        CubicCurve2D curve = new CubicCurve2D.Float(p1x, p1y,
                p1x, p1y + (p2y - p1y) / 2,
                p2x, p1y + (p2y - p1y) / 2,
                p2x, p2y);
        g2.draw(curve);

        g2.setStroke(new BasicStroke(1.0f));
    }

    public Point getFreePosition() {
        return freeposition;
    }

    public void setFreePosition(Point freeposition) {
        if (freeposition != null) {
            this.freeposition = freeposition;
        }
    }

    public Parameter getParameter() {
        return param;
    }

    private Parameter param;
    private Point freeposition;
}
