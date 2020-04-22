package app;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StationVisualizer {

	public static void main(String[] args) {

		int width = 1600;
        int height = 800;
        
        int numZones = (int) (Math.random() * 12 + 2); // creates the number of zones
														// distances[zones][stations][distance to next stations]
		double[][][] distances = new double[numZones][][];

		// creates a random amount of stations in each zone
		for (int i = 0; i < numZones; i++) {
			if (i == 0 || i == numZones - 1)
				distances[i] = new double[1][];
			else // first and last zone has 1 station
				distances[i] = new double[(int) ( 10 * Math.random() + 1)][];
		}

		// tells how many possible paths station j in zone i has
		for (int i = 0; i < numZones; i++) {
			for (int j = 0; j < distances[i].length; j++) {
				if (i == (numZones - 1))
					continue;
				else
					distances[i][j] = new double[distances[i + 1].length];
			}
		}

		// fill each distance with a random value
		for (int i = 0; i < distances.length - 1; i++) {
			for (int j = 0; j < distances[i].length; j++) {
				for (int k = 0; k < distances[i][j].length; k++) {
					distances[i][j][k] = 0.8*Math.random();
					System.out.println(distances[i][j][k] + ", ");

				}
			}
        }
        
        // height and width based on the zones and stations
		int w = 150 * (numZones + 1);
		int h = 0;
		for (int i = 0; i < distances.length; i++)
			if (distances[i].length > h)
				h = 400 * (distances[i].length + 1);

		// create jpanel
		//JPanel pane = new JPanel();
		//pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS)); // set layout

		Polylines pl = new Polylines(); // create list of polylines (observable)
		Graph g = new Graph(w, h, distances,pl); // create graph with height and width (observer)
		pl.addObserver(g); // assign observer to observable
		//g.add(g); // jpanel takes graph to draw

		JFrame frame = new JFrame("Shortest path visualizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(g);
		frame.pack();
		frame.setVisible(true);

		// populate the polylines based on the stations and zones 
		for (int i = 0; i < (distances.length-1); i++){
            pl = new Polylines(pl.getPolylines(), distances[i], distances);
            Graph gr = new Graph(w, h, distances,pl);
            frame.getContentPane().add(gr);
            frame.pack();
            //frame.setVisible(true);
            //frame.repaint();

            
            //pl.notifyObservers();
			try {
				Thread.sleep(500);
			} catch (InterruptedException iox) {
			}
        }
		System.out.println("min distance is: " + pl.getLength());
		for(int station : pl.getStations()){
			System.out.println(station+1 + ", ");
        }
    

	}

}
