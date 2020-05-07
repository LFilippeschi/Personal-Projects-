import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graph extends JPanel {
    Point cursor = new Point(800, 540);
    private LinkedList<Object> lst = new LinkedList<>();

    public Graph() {

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                ListIterator<Object> litr = lst.listIterator(0);
                Object o;
                while (litr.hasNext()) {
                    o = litr.next();
                    Vector force = Vector.sub(new Vector(o.getPos()), new Vector(me.getPoint()));
                    force.div(o.getWeight());
                    o.getVel().add(force);
                }
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                cursor = e.getPoint();
                repaint();
            }

        });

    }

    public void paint(Graphics g) {
        super.paint(g);
        ListIterator<Object> litr = lst.listIterator(0);
        ListIterator<Object> litr1;

        Object o = litr.next();
        o.move(cursor);
        String particles = "Particles: " + lst.size();
        String message1 = "cursor posX: " + cursor.getX() + " posY: " + cursor.getY();
        g.setColor(Color.green);
        g.drawString(particles, 0, 20);
        g.drawString(o.getPos().toString(), 0, 35);
        g.drawString(message1, 0, 50);
        g.drawString("Force: " + o.getForce().toString(), 0, 65);
        g.drawString("Vel: " + o.getVel().toString(), 0, 80);
        g.drawString("Acc: " + o.getAcc().toString(), 0, 95);
       
        while (litr.hasNext()) {
            litr1 = lst.listIterator(0);
            o = litr.next();
            o.move(cursor);
            while (litr1.hasNext()) {
                Object o1 = litr1.next();
                if (o != o1)
                    o.attract(o1);
            }
        }
        setBackground(Color.DARK_GRAY);
        g.setColor(Color.YELLOW);
        if (cursor != null)
            g.fillOval((int) cursor.getX() - ((int) Main.CURSORWEIGHT / 2),
                    (int) cursor.getY() - ((int) Main.CURSORWEIGHT / 2), (int) Main.CURSORWEIGHT,
                    (int) Main.CURSORWEIGHT);
        litr = lst.listIterator(0);
        while (litr.hasNext()) {
            o = litr.next();
            g.setColor(new Color((int) o.getWeight() * 17, 128, 0));
            g.fillOval(o.getPosX() - ((int) o.getWeight() / 2), o.getPosY() - ((int) o.getWeight() / 2),
                    (int) o.getWeight(), (int) o.getWeight());
        }
    }

    public LinkedList<Object> getLst() {
        return lst;
    }

    public void addObject() {
        lst.add(new Object());
    }

};
