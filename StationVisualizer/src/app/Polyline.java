package app;

public class Polyline {
	private double length; // total length
	private int[] stations = {}; // saves the index of every station it goes through

	public Polyline() {
	}

	public Polyline(int index, double l) {
		length = length + l;
		int[] rtrn = new int[stations.length + 1];
		if (stations.length != 0)
			for (int i = 0; i < rtrn.length; i++) // populate the new array
				rtrn[i] = stations[i];
		rtrn[rtrn.length - 1] = index;
		stations = rtrn;
	}

	public Polyline(Polyline p, int index, double l) { // give the previous polyline plus the next point
		length = p.length + l;
		int[] rtrn = new int[p.stations.length + 1];
		for (int i = 0; i < rtrn.length - 1; i++) // populate the new array
			rtrn[i] = p.stations[i];
		rtrn[rtrn.length - 1] = index;
		stations = rtrn;
	}

	public Polyline(Polyline p) {
		length = p.getLength();
		stations = p.getStations();
	}

	public double getLength() {
		return length;
	}

	public int[] getStations() {
		return stations;
	}
}
