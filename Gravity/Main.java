import java.awt.Color;
import javax.swing.JFrame;

public class Main {

    public static final int OBJECTS = 10;
    public static final double G = .000000001;
    public static final double CURSORWEIGHT = 50;
    public static final long TIME = 3;
    public static final int FRAMES = 1;
    public static final double FORCELIMMAX = 5000;
    public static final double FORCELIMMIN = .01;
    public static final double ACCLIM = .001;
    public static final double VELLIM = .1;

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Gravity simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.DARK_GRAY);
        frame.setSize(1600, 1080);
        frame.setResizable(false);
        Graph g = new Graph();
        for (int i = 0; i < OBJECTS; i++)
            g.addObject();
        frame.add(g);
        frame.setVisible(true);
        while (true) {
            for (int i = 0; i < FRAMES; i++) {
                try {
                    Thread.sleep(TIME);
                } catch (InterruptedException iox) {
                }
                g.repaint();
            }
        }

    }
}