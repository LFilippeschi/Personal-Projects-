import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PointsDemo {

	public static void main(String[] args) {

		int width = 800;
		int height = 800;

		// create jpanel
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS)); // set layout

		Points pt = new Points(); // create list of points (observable)
		Graph g = new Graph(width, height, "Points"); // create graph with height and width (observer)
		pt.addObserver(g); // assign observer to observable
		pane.add(g); // jpanel takes graph to draw

		JFrame frame = new JFrame("Points Visualization Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(pane);
		frame.pack();
		frame.setVisible(true);

		// increment i forever and create points
		for (int i = 0; 0 <= i; i++) {
			pt.addPoint(new Point(
					new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)),
					(int) (Math.random() * 800), (int) (Math.random() * 800)));
			pt.notifyObservers();
			try {
				Thread.sleep(100);
			} catch (InterruptedException iox) {
			}
		}

	}

}
