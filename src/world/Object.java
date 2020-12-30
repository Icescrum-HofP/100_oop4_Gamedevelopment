package world;

import world.Vektor;

import java.awt.*;

public class Object extends Vektor {

    public Object(){
        super(11.1,11.1);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.CYAN);
        g2.drawRect((int)getX(),(int)getY(),100,100);
    }
}
