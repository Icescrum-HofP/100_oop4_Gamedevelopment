package entities;

import input.settings.Settings;

import java.awt.*;
import java.util.ArrayList;

public class Enenmy extends Player {

    private static Settings settings = new Settings() ;
    private static int nameid = 1;
    private static String name = "ENEMY " + nameid;
    private ArrayList<Bullet> bullets;
    private String direction;

    public Enenmy() {
        super(name,settings,nameid);
    }

    public Enenmy(String name_, int id, ArrayList<Bullet> bullets_){
        super(name_,settings,id);
        bullets = bullets_;

    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        g2.drawRect((int) super.getX(), (int) super.getY(), 40, 40);
        g2.setColor(Color.pink);
        g2.drawString(name, (int) super.getX(), (int) super.getY()-10);
    }



}
