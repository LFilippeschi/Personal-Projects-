import java.awt.Point;

public class Object {
    private double weight;
    private Vector pos, vel, acc, force;

    public Object() {
        weight = (Math.random() * 15);

        // Single spwan
        // pos = new Vector(600, 450);
        // vel = new Vector(Math.random() * 50, 50);

        // vertical
        if (Math.random() < 0.5)
            pos = new Vector(600 + Math.random() * 100, 540);
        else
            pos = new Vector(1000 - Math.random() * 100, 540);
        if (Math.random() < 0.5)
            vel = new Vector(0, Math.random() * 100);
        else
            vel = new Vector(0, -(Math.random() * 100));

        // Random
        // pos = Vector.randomVector().add(Vector.randomVector().mul(500));
        // vel = new Vector(Vector.randomVector().mul(100));

        // Big Bang
        // pos = new Vector(801, 539);
        // vel = new Vector(Vector.randomVector().mul(10));

        acc = new Vector(0, 0);
        force = new Vector(0, 0);
    }

    public void move(Point p) {
        Vector mouse = new Vector(p);

        // update force, needs to be tuned
        // real equation

        force = Vector.sub(mouse, pos);
        force.normalize();
        double attraction = Main.CURSORWEIGHT * weight * Main.G;
        double distance = Math.sqrt(Math.pow(pos.getX() - p.getX(), 2) + Math.pow(pos.getY() - p.getY(), 2));
        attraction /= distance;
        force.mul(attraction);
        if ((force.getX() < 5 && force.getX() > 0) || (force.getY() > -5 && force.getY() < 0)) {
            force.normalize();
            force.mul(Main.FORCELIMMIN);
        }

        force.limit(Main.FORCELIMMAX);

        // update acc
        acc.add(force);
        acc.limit(Main.ACCLIM);
        acc.div(weight);        
        
        //acc.div(Main.ACCLIM);

        // update vel
        vel.add(acc);
        vel.limit(Main.VELLIM);
        //vel.div(Main.VELLIM);

        // update pos
        pos.add(vel);

    }

    public void attract(Object o){

        // gravitational force between objects

        force = Vector.sub(o.getPos(), pos);
        force.normalize();
        double attraction = o.weight * weight * Main.G;
        double distance = Math.sqrt(Math.pow(pos.getX() - o.getPos().getX(), 2) + Math.pow(pos.getY() - o.getPos().getY(), 2));
        attraction /= distance;
        force.mul(attraction);
        if ((force.getX() < 5 && force.getX() > 0) || (force.getY() > -5 && force.getY() < 0)) {
            force.normalize();
            force.mul(Main.FORCELIMMIN);
        }

        force.limit(Main.FORCELIMMAX);

        // update acc
        acc.add(force);
        acc.limit(Main.ACCLIM);
        acc.div(weight);        
        
        //acc.div(Main.ACCLIM);

        // update vel
        vel.add(acc);
        vel.limit(Main.VELLIM);
        //vel.div(Main.VELLIM);

        // update pos
        pos.add(vel);

    }

    public Vector getVel() {
        return vel;
    }

    public void setVel(Vector vel) {
        this.vel = vel;
    }

    public Vector getAcc() {
        return acc;
    }

    public void setAcc(Vector acc) {
        this.acc = acc;
    }

    public int getPosX() {
        return (int) pos.getX();
    }

    public int getPosY() {
        return (int) pos.getY();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Vector getForce() {
        return force;
    }

    public void setForce(Vector force) {
        this.force = force;
    }

    public Vector getPos() {
        return pos;
    }

    public void setPos(Vector pos) {
        this.pos = pos;
    }

}