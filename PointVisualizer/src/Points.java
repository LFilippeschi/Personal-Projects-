import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Points extends Observable {

	private LinkedList<Point> points = new LinkedList<>();
	
	public void addPoint(Point p) {
		points.add(p);
		setChanged();
	}
	
	public List<Point> getPoints(){
		return points;
	}
}
