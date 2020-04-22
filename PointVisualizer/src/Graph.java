import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class Graph extends JPanel implements Observer {

	private Points pts = new Points();// 
	private String title = "";

	public Graph(int width, int height, String title) {
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.DARK_GRAY);
		this.title = title;
	}

	public void update(Observable o, Object arg) {
		if (o instanceof Points) {
			Points pts = (Points) o;
			this.pts = pts;
			repaint();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension size = getSize();

		// creates the window with margins
		int leftX = (int) 0.1 * size.width;
		int topY = (int) 0.1 * size.height;
		int w = size.width - 2 * leftX;
		int h = size.height - 2 * topY;

		// Title
		g.setColor(Color.white);
		g.drawString(title, size.width / 2, 14);
		g.drawString("Y", 14, 14);
		g.drawString("X", size.height - 14, size.height - 14);

		// data
		List<Point> points = pts.getPoints();

		if (points.isEmpty()) {
			g.setColor(Color.red);
			g.drawString("NO POINTS", size.width / 2, size.height / 2);
			return;// ??
		}

		// draw x and y diagram axis
		g.setColor(Color.green);
		g.drawLine(1, 1 + topY, 1, h - topY); // y
		g.drawLine(1, h - 1, 1 + w, h - 1);// x

		// draw data
		g.setColor(Color.yellow);
		ListIterator<Point> litr = points.listIterator(0);
		ListIterator<Point> litrNext = points.listIterator(1);

		while (litrNext.hasNext()) {
			Point p = litr.next();
			Point pN = litrNext.next();
			int x = p.getX();
			int y = p.getY();
			int xN = pN.getX();
			int yN = pN.getY();
			g.setColor(Color.white);
			g.drawOval(x-2, y-2, 5, 5);// draw point
			g.drawOval(xN-2, yN-2, 5, 5);// draw point
			g.setColor(p.getColor());
			g.drawLine(x, y, xN, yN); // draw line

		}

	}

}
