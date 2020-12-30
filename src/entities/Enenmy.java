package entities;

import input.settings.Settings;

import java.awt.*;

public class Enenmy extends Player {

    private static Settings settings = new Settings("src\\pics\\Settings") ;
    private static int nameid = 1;
    private static String name = "ENEMY " + nameid;

    public Enenmy() {
        super(name,settings);
    }


   /* @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        g2.drawRect((int) super.getX(), (int) super.getY(), 40, 40);
        g2.setColor(Color.pink);
        g2.drawString(name, (int) super.getX(), (int) super.getY()-10);
    }
*/


}
