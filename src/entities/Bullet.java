package entities;

import entities.Player;
import world.Pos;
import world.Postition;

import java.awt.*;

public class Bullet extends Postition {

    private double lenghttarget;
    private double xdiv;
    private double ydiv;
    private int count;
    private double defauldspeed = 2;

    public Bullet(Player player, Pos postition) {
        super(player.getX(), player.getY());
        getlenght(player, postition);
        System.out.println("bullet made "+ postition.toString() );
    }

    private void getlenght(Player player, Pos postition){
        xdiv= (player.getX() - postition.getX());
        ydiv = (player.getY() - postition.getY());
        System.out.println(xdiv + "," + ydiv);
        lenghttarget = Math.sqrt(((ydiv*ydiv) + (xdiv*xdiv)));
        System.out.println(xdiv +" | " + ydiv);
    }

    private void move(){
        double xspeed = ((xdiv*xdiv)/2)/100;
        double yspeed = ((ydiv*ydiv)/2)/100;

        System.out.println(((xdiv/((xdiv*xdiv)/2))));
        System.out.println(yspeed*(ydiv/((ydiv*ydiv)/2)));
        super.setX(super.getX()+(xspeed*(xdiv/((xdiv*xdiv)/2))*-1));
        super.setY(super.getY()+(yspeed*(ydiv/((ydiv*ydiv)/2))*-1));

    }

    public void paintComponent(Graphics g) {
        count ++;
        move();
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN);
        g2.drawRect((int) super.getX(), (int) super.getY(), 10, 10);
    }

    public boolean hasfinished (){
        if(count == 100){
            return true;
        }
        return false;
    }

}
