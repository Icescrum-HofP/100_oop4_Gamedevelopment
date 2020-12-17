package entities;

import java.awt.*;

public class Enenmy extends Player {

    private static int nameid =1 ;
    private static String name = "ENEMY " + nameid;

    public Enenmy() {
        super(name, nameid);
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
