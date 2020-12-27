package entities;

import world.Colide;

import java.awt.*;

public class Player extends Colide {

    private String name;
    private int helth;
    private double h_;
    private double w_;
    private int id;

    public Player(String name_, int id_){
        super(100.0,100.0,40.0,40.0);
        h_ = 40;
        w_ = 40;
        name = name_;
        id = id_;
    }


    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawRect((int) super.getX(), (int) super.getY(), (int)h_, (int)w_);
        g2.setColor(Color.green);
        g2.drawString(name, (int) super.getX(), (int) super.getY()-10);
    }


    public double getH_() {
        return h_;
    }

    public double getW_() {
        return w_;
    }

    public void setSize(double high, double with){
        h_ = high;
        w_ = with;
    }
}
