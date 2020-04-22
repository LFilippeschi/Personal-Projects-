package app;

import java.util.Observable;

public class Polylines extends Observable {
	private double[][][] tot = {};
	private Polyline[] pol = {}; // array of all the polylines
	private int[] indices = {};
	private double least;

	public Polylines() {
	}

	public Polylines(Polyline[] p, double[][] d, double[][][] tot) {// [next stations][distance to next station]
		this.tot = tot;
		Polyline[] rtrn;
		if (p.length == 0)
			rtrn = new Polyline[d[0].length]; // initial case where empty
		else
			rtrn = new Polyline[p.length * d[0].length]; // creates a new array based on the previous length
															// multiplied by the amount of new paths
		// for (int i = 0; i < p.length; i++)// copies all the previous polylines
		// rtrn[i] = p[i];

		if (p.length == 0)
			for (int i = p.length; i < 1; i++) { // creates the new polylines saving the index and length
				for (int j = 0; j < d[i].length; j++)
					rtrn[j] = new Polyline(j, d[i][j]);
			}
		else {
			int j = 0;
			for (Polyline po : p) {
				for (int i = 0; i < d[0].length; i++) {
					rtrn[j++] = new Polyline(po, i, d[po.getStations()[po.getStations().length - 1]][i]);
				}
			}
		}

		pol = rtrn;
		least = this.leastDistance();
		setChanged();

	}

	public double getLength() {
		return least;
	}

	public Polyline[] getPolylines() {
		return pol;
	}

	public int[] getStations() {
		return indices;
	}

	public double[][][] getData() {
		return tot;
	}

	// return the shortest path in the total array and saves the indices of that
	// polyline
	public double leastDistance() {
		double tmp = pol[0].getLength();
		for (Polyline p : pol)
			if (p.getLength() < tmp) {
				tmp = p.getLength();
				indices = p.getStations();
			}
		return tmp;
	}

}
