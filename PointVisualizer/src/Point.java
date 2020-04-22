import java.awt.Color;

public class Point {

	private Color color;
	private int x;
	private int y;

	public Point() {
		color = new Color(0,0,0);
		x = 0;
		y = 0;
	}

	public Point(Color c, int x, int y) {
		this.color = c;
		this.x = x;
		this.y = y;
	}

	public Point(Point p) {
		this.color = p.color;
		this.x = p.x;
		this.y = p.y;
	}

	public Color getColor() {
		return color;
	}

	public int getX() {
		return x;

	}

	public int getY() {
		return y;
	}
}
