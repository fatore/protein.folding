/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipeline.component.parameter;

import vispipeline.component.method.ComponentMethod;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public abstract class Parameter implements Serializable {

    public Parameter(ComponentMethod method, Class type) {
        this.method = method;
        this.type = type;
        this.position = new Point();
        this.output = false;
        this.selected = false;
        this.showtype = true;
    }

    public void draw(Graphics2D g2) {
        if (selected) {
            g2.setColor(Color.YELLOW);
        } else {
            g2.setColor(Color.GREEN);
        }
        g2.fillRoundRect(position.x, position.y, SIZE, SIZE, Parameter.SPACE, Parameter.SPACE);

        g2.setColor(Color.BLACK);
        g2.drawRoundRect(position.x, position.y, SIZE, SIZE, Parameter.SPACE, Parameter.SPACE);

        if (selected && showtype) {
            String name = toString();

            //Getting the label size
            FontMetrics metrics = g2.getFontMetrics();
            int width = metrics.stringWidth(name);
            int height = metrics.getAscent();

            g2.setPaint(java.awt.Color.WHITE);
            g2.fill(new java.awt.Rectangle(position.x - 2 + (SIZE / 2),
                    position.y - height - (SIZE / 2), width + 4, height + 4));

            g2.setColor(java.awt.Color.BLACK);
            g2.drawRect(position.x - 2 + (SIZE / 2),
                    position.y - height - (SIZE / 2), width + 4, height + 4);

            //Drawing the label
            g2.drawString(name, position.x + (SIZE / 2), position.y - (SIZE / 2));
        }
    }

    public void setPosition(Point position) {
        if (position != null) {
            this.position = position;
        }
    }

    public Point getPosition() {
        return position;
    }

    public Class getType() {
        return type;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isInside(Point pos) {
        if ((pos.x >= position.x && pos.y >= position.y) &&
                (pos.x <= position.x + SIZE && pos.y <= position.y + SIZE)) {
            return true;
        }

        return false;
    }

    public ComponentMethod getParentMethod() {
        return method;
    }

    public static final long serialVersionUID = 1L;
    public static final int SPACE = 4;
    public static final int SIZE = 8;
    protected Class type;
    private Point position;    
    private boolean selected;
    private boolean showtype;
    private boolean output;
    private ComponentMethod method;
}
