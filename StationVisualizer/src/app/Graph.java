package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class Graph extends JPanel implements Observer {

    private double[][][] d = {};
    private Polylines p;

    public Graph(int width, int height, double[][][] data, Polylines pl) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.DARK_GRAY);
        d = data;
        p = pl;
    }

    public void update(Observable o, Object arg) {
        if (o instanceof Polylines) {
            Polylines pts = (Polylines) o;
            this.d = pts.getData();
            this.p = pts;
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension size = getSize();

        Polylines pl = p;
        // size of the stations and zones
        int w = size.width;
        int h = size.height;
        int zones = d.length; // total amount of zones
        int x = w / (zones + 1); // individual size between zones
        if (p == null) {
            return;
        }
        int length = p.getStations().length; // size of the polyline
        int[] stations = p.getStations();

        // draw stations
        for (int i = 0; i < zones; i++)
            for (int j = 0; j < d[i].length; j++) {
                int y = h / (d[i].length + 1); // individual size between stations
                g.setColor(Color.cyan);
                g.drawOval(x * (i + 1), y * (j + 1), 25, 25);
                g.setColor(Color.white);
                g.drawString("S:" + (j + 1), x * (i + 1), y * (j + 1));

            }

        // draw lines between stations
        for (int i = 0; i < zones - 1; i++) // go through all the zones except the last one
            for (int j = 0; j < d[i].length; j++) { // go thorugh all the stations in each zone
                int y = h / (d[i].length + 1);
                int y1 = h / (d[i + 1].length + 1);
                for (int k = 0; k < d[i + 1].length; k++) { // go thorugh the stations in the next zone with respect to
                                                            // the current station
                    if (i < length && length > 0)
                        if (stations[i] == k && i == 0) { // initial station
                            g.setColor(Color.green);
                            g.drawLine(x * (i + 1) + 12, y * (j + 1) + 12, x * (i + 2) + 12, y1 * (k + 1) + 12);
                        } else if (stations[i] == k && stations[i - 1] == j) {
                            g.setColor(Color.green);
                            g.drawLine(x * (i + 1) + 12, y * (j + 1) + 12, x * (i + 2) + 12, y1 * (k + 1) + 12);
                        } else {
                            g.setColor(new Color((int) (200 * d[i][j][k]), 0, 0));
                            g.drawLine(x * (i + 1) + 12, y * (j + 1) + 12, x * (i + 2) + 12, y1 * (k + 1) + 12);
                        }
                    else { // regular case
                        g.setColor(new Color((int) (200 * d[i][j][k]), 0, 0));
                        g.drawLine(x * (i + 1) + 12, y * (j + 1) + 12, x * (i + 2) + 12, y1 * (k + 1) + 12);
                    }
                }
            }
            String message = "Min distance from A to B: " + pl.getLength();
            String stat="Stations path: 1, ";
            for(int station : pl.getStations()){
                stat += station+1 + ", ";
            }
            g.setColor(Color.WHITE);
            g.drawString(message, 0, h-15);
            g.drawString(stat, 0, h-2);
    }

}
