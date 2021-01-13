package entities;

import world.Pos;
import world.Colide;
import world.Vektor;

import java.awt.*;

public class Bullet extends Colide {

    private double lenghttarget;
    private double xdiv;
    private double ydiv;
    private static double h = 5.0;
    private static double w = 5.0;
    private int count;
    private double defauldspeed = 2;
    private Player player;
    private boolean ennemy;

    public Bullet(Player player_, Pos postition) {
        super((player_.getX() + player_.getW() / 2), (player_.getY() + player_.getH() / 2), h, w);
        player = player_;
        getlenght(postition);
        ennemy = false;
    }


    public Bullet(Pos pos) {
        super(pos.getX(), pos.getY(), h, w);
        ennemy = true;
    }

    private void getlenght(Pos postition) {
        xdiv = (player.getX() - postition.getX());
        ydiv = (player.getY() - postition.getY());
        lenghttarget = Math.sqrt(((ydiv * ydiv) + (xdiv * xdiv)));
    }

    private void move() {
        double xspeed = ((xdiv * xdiv) / 2) / 100;
        double yspeed = ((ydiv * ydiv) / 2) / 100;
        super.setX(super.getX() + (xspeed * (xdiv / ((xdiv * xdiv) / 2)) * -1));
        super.setY(super.getY() + (yspeed * (ydiv / ((ydiv * ydiv) / 2)) * -1));

    }

    public void paintComponent(Graphics g) {
        if (!ennemy) {
            count++;
            move();
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
//        g2.drawRect((int) super.getX(), (int) super.getY(), 10, 10);
        g2.fillRect((int) super.getX(), (int) super.getY(), 10, 10);
    }

    public boolean check(Player p) {
        if (p.getId() != player.getId()) {
            return super.check(p);
        }
        return false;
    }

    public boolean checks(Vektor s) {
        return super.check(s);
    }

    public boolean hasfinished() {
        if (count >= 100) {
            return true;
        }
        return false;
    }

}
