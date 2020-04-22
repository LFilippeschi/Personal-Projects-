import java.awt.Color;
import javax.swing.JFrame;

public class Demo {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setSize(800, 838);
        window.setBackground(Color.DARK_GRAY);
        window.setTitle("Random movement visualizer");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        Points pts = new Points();
        for (int i = 0; i < 15000; i++) {
            pts.addPoint(new Point());
        }
        Graph g = new Graph(window.getWidth(), window.getHeight());
        pts.addObserver(g);
        window.add(g);
        while (true) {
            pts.movePoints();
            try {
                Thread.sleep(1);
            } catch (InterruptedException iox) {
            }
        }

        // graph.getGraphics().drawLine(data[0].getX(), data[0].getY(), 10, 15);

    }
}