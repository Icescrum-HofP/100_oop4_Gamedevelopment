package entities;

import world.Postition;

import java.awt.*;

public class Player extends Postition {

    private String name;
    private int id;

    public Player(String name_, int id_){
        super(100.0,100.0);

        name = name_;
        id = id_;
    }


    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.drawRect((int) super.getX(), (int) super.getY(), 40, 40);

        g2.setColor(Color.green);
        g2.drawString(name, (int) super.getX(), (int) super.getY()-10);
    }



}
