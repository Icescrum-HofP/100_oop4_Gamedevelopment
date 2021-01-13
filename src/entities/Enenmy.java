package entities;

import input.settings.Settings;

import java.awt.*;
import java.util.ArrayList;

public class Enenmy extends Player {

    private static Settings settings = new Settings();
    private static int nameid = 1;
    private ArrayList<Bullet> bullets;
    private String direction;
    private String name;

    //old builder
    public Enenmy() {
        super("olav", settings, nameid);
    }

    //
    public Enenmy(String name_, int id, ArrayList<Bullet> bullets_) {
        super(name_, settings, id);
        name = name_;
        bullets = bullets_;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void setDirection(String direction) {
        this.direction = direction;
        super.setDirection(this.direction);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.drawTank(g2);
        g2.setColor(Color.red);
        g2.drawString(name, (int) super.getX(), (int) super.getY() - 10);
    }

}
