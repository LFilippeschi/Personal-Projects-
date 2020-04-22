import java.awt.Color;

public class Point {

    private int x;
    private int y;
    private Color color;
    private int previousX;
    private int previousY;

    public Point() {
        this.color = new Color((int) (255 * Math.random()), (int) (255 * Math.random()), (int) (255 * Math.random()));
        this.x = 400;
        this.y = 400;
    }

    public Color getColor() {
        return this.color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPrevX() {
        return previousX;
    }

    public int getPrevY() {
        return previousY;
    }

    public void setX(int x) {
        previousX = this.x;
        this.x += x;
    }

    public void setY(int y) {
        previousY = this.y;
        this.y += y;
    }
}