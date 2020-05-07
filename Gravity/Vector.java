import java.awt.Point;

public class Vector {
    private double x, y;

    public static Vector sub(Vector v1, Vector v2) {
        Vector tmp1 = new Vector(v1);
        tmp1.x -= v2.x;
        tmp1.y -= v2.y;
        return tmp1;
    }

    public static Vector randomVector() {
        Vector rand = new Vector(0, 0);
        int degree = (int) (Math.random() * 360);
        rand.setX(Math.cos(degree));
        rand.setY(Math.sin(degree));
        return rand;
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    public Vector(Vector v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector mul(double m) {
        this.x *= m;
        this.y *= m;
        return this;
    }

    public void limit(double l) {
        double tmp = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        this.div(tmp);
        this.mul(l);
    }

    public void normalize() {
        double tmp = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        this.div(tmp);
    }

    public void div(double d) {
        if (d == 0)
            return;
        this.x /= d;
        this.y /= d;
    }

    public Vector add(Vector v) {
        this.x += v.getX();
        this.y += v.getY();
        return this;
    }

    public String toString() {
        String message = "x: " + x + "y; " + y;
        return message;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}