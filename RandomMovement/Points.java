import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;

public class Points extends Observable {

    private LinkedList<Point> points = new LinkedList<>();
    private int[] distX = new int[800];
    private int[] distY = new int[800];
    private int tot;

    public void addPoint(Point p) {
        points.add(p);
        distX[p.getX()]++;
        distY[p.getY()]++;
    }

    public void setTot(int t) {
        tot = t;
    }

    public int getTot() {
        return tot;
    }

    public void countTot() {
        setTot(0);
        for (int i = 0; i < distX.length; i++)
            setTot(getTot() + distX[i]);
    }

    public void movePoints() {
        ListIterator<Point> litr = points.listIterator(0);
        while (litr.hasNext()) {
            int step = 1;
            int x, y;
            int w, h;
            Point p = litr.next();
            // x movement
            if (Math.random() < 0.5) {
                w = p.getX();
                if (w <= 1) {
                    continue;
                } else {
                    distY[w]--;
                    distY[w - 1]++;
                    x = -1 * step;
                }

            } else {
                w = p.getX();
                if (w >= 798) {
                    continue;
                } else {
                    distY[w]--;
                    distY[w + 1]++;
                    x = 1 * step;
                }
            }
            // y movement
            if (Math.random() < 0.5) {
                h = p.getY();
                if (h <= 1) {
                    continue;
                } else {
                    distX[h]--;
                    distX[h - 1]++;
                    y = -1 * step;
                }
            } else {
                h = p.getY();
                if (h >= 798) {
                    continue;
                } else {
                    distX[h]--;
                    distX[h + 1]++;
                    y = 1 * step;
                }
            }
            p.setX(x);
            p.setY(y);
        }
        countTot();
        setChanged();
        notifyObservers();
    }

    public int[] getDistX() {
        return distX;
    }

    public int[] getDistY() {
        return distY;
    }

    public LinkedList<Point> getPoints() {
        return points;
    }
}