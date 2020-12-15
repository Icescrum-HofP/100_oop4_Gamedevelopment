package entities;

import world.Postition;

import java.awt.*;

public class Player extends Postition {

    private String name;
    private double h;
    private double w;
    private int id;

    public Player(String name_, int id_){
        super(100.0,100.0);
        h = 40;
        w = 40;
        name = name_;
        id = id_;
    }


    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawRect((int) super.getX(), (int) super.getY(), (int)h, (int)w);
        g2.setColor(Color.green);
        g2.drawString(name, (int) super.getX(), (int) super.getY()-10);
    }


    public double getH() {
        return h;
    }

    public double getW() {
        return w;
    }

    public void setSize(double high, double with){
        h = high;
        w = with;
    }
}
