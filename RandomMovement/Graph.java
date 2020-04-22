import java.awt.*;
import javax.swing.*;
import java.util.ListIterator;
import java.util.Observer;
import java.util.Observable;

@SuppressWarnings("serial")
public class Graph extends JPanel implements Observer {

    private Points data = new Points();

    public Graph(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.DARK_GRAY);
    }

    public void update(Observable o, Object arg) {
        if (o instanceof Points) {
            Points pts = (Points) o;
            this.data = pts;
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int scale =1;
        g.setColor(Color.WHITE);
        String total = "Particles: " + data.getTot();
        g.drawString(total, 0, 20);
        // g.setColor(getBackground());
        // g.fillRect(0, 0, 100, 800);
        g.setColor(Color.YELLOW);
        for (int i = 0; i < data.getDistY().length; i++) {
            g.setColor(Color.YELLOW);
            g.fillRect(0, i, data.getDistX()[i]/scale, 1);
            // g.setColor(Color.WHITE);
            // g.drawString(""+data.getDistX()[i], (80*i)%800, i);
        }
        g.setColor(Color.YELLOW);
        for (int i = 0; i < data.getDistX().length; i++) {
            g.fillRect(i, 800 - data.getDistX()[i]/scale, 1, data.getDistX()[i]/scale);
        }

        g.setColor(Color.white);
        g.drawOval(395, 395, 10, 10);
        ListIterator<Point> litr = data.getPoints().listIterator(0);
        while (litr.hasNext()) {
            Point p = litr.next();
            g.setColor(p.getColor());

            //draw particle at its position
            g.drawOval(p.getX()-1, p.getY()-1, 2, 2);

            //draw lines when moving
            //g.drawLine(p.getPrevX(), p.getPrevY(), p.getX(), p.getY());

        }

    }

}