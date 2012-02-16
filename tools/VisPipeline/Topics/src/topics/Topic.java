/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package topics;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;
import topics.model.TopicProjectionInstance;
import topics.model.TopicProjectionModel;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class Topic {

    public Topic(TopicProjectionModel model, ArrayList<TopicProjectionInstance> instances,
            ArrayList<StringBox> boxes) {
        this.model = model;
        this.boxes = boxes;
        this.show = false;
        this.instances = instances;
    }

    public void drawTopic(Graphics2D g2, Font font) {
        this.rectangle = calculateRectangle(instances);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setFont(font);
        g2.setStroke(new BasicStroke(1.3f));
        g2.setColor(Color.GRAY);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g2.setStroke(new BasicStroke(1.0f));

        if (show || model.isShowTopics()) {
            //draw the first label
            if (boxes.size() > 0) {
                java.awt.Point position = new java.awt.Point();
                position.x = rectangle.x + rectangle.width / 2;
                position.y = rectangle.y;
                Rectangle rect = boxes.get(0).draw(g2, position, font);

                //draw all the other ones
                for (int i = 1; i < boxes.size(); i++) {
                    position = new java.awt.Point();
                    position.x = rectangle.x + rectangle.width / 2;
                    position.y = rect.y + rect.height + 6;
                    rect = boxes.get(i).draw(g2, position, font);
                }
            }
        }
    }
       
    public boolean isInside(Point point) {
        return (point.x > rectangle.x && point.x < (rectangle.x + rectangle.width) &&
                point.y > rectangle.y && point.y < (rectangle.y + rectangle.height));
    }

    public float weightDistance(Point point) {
        if (isInside(point)) {
            int cx = rectangle.x + rectangle.width / 2;
            int cy = rectangle.y + rectangle.height / 2;
            return (float) (Math.sqrt((cx - point.x) * (cx - point.x) + (cy - point.y) *
                    (cy - point.y)) * (rectangle.width));
        } else {
            return -1;
        }
    }

    public boolean isShowTopic() {
        return show;
    }

    public void setShowTopic(boolean show) {
        this.show = show;
        model.setChanged();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    private Rectangle calculateRectangle(ArrayList<TopicProjectionInstance> instances) {
        Rectangle rect = new java.awt.Rectangle();

        if (instances.size() > 0) {
            int maxx = (int) instances.get(0).getX();
            int minx = (int) instances.get(0).getX();
            int maxy = (int) instances.get(0).getY();
            int miny = (int) instances.get(0).getY();

            for (int i = 1; i < instances.size(); i++) {
                int x = (int) instances.get(i).getX();
                int y = (int) instances.get(i).getY();

                if (x > maxx) {
                    maxx = x;
                } else if (x < minx) {
                    minx = x;
                }

                if (y > maxy) {
                    maxy = y;
                } else if (y < miny) {
                    miny = y;
                }
            }

            rect.x = minx;
            rect.y = miny;
            rect.width = maxx - minx;
            rect.height = maxy - miny;
            return rect;
        } else {
            rect.x = -1;
            rect.y = -1;
            rect.width = 0;
            rect.height = 0;
            return rect;
        }
    }

    private TopicProjectionModel model;
    private ArrayList<StringBox> boxes;
    private ArrayList<TopicProjectionInstance> instances;
    private Rectangle rectangle;
    private boolean show;
}
