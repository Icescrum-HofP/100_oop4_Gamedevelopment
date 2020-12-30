package entities;

import input.settings.Settings;
import world.Colide;
import world.input.Tiles;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends Colide {

    private String name;
    private int helth;
    private Tiles avatar;
    private Settings settings;
    private String[] parameters;
    private int angle;

    public Player(String name_, Settings settings_){
        super(100.0,100.0,40.0,40.0);
        settings=settings_;
        parameters = settings.getAvatarparameters();
        setHW(Double.parseDouble(parameters[0]),Double.parseDouble(parameters[1]));
        avatar = new Tiles(settings.getAvatarpath(),Integer.parseInt(parameters[0]),Integer.parseInt(parameters[1]),Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3]));

        name = name_;
    }


    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //g2.drawRect((int) super.getX(), (int) super.getY(), (int)h_, (int)w_);
        g2.setColor(Color.green);
        g2.drawString(name, (int) super.getX(), (int) super.getY()-10);
        g2.drawImage(avatar.getTieles(0,0),(int) super.getX(), (int) super.getY(),null );

    }

    public AffineTransform transform(Graphics2D graphics) {
        AffineTransform transform = new AffineTransform();
        transform = AffineTransform.getTranslateInstance((getX()+ getW()/2), (getY() + getH()/2));

        transform.setToRotation(angle,(getX()+ getW()/2),(getY() + getH()/2));
        graphics.transform(transform);

        transform.translate(getX(), getY());
        graphics.transform(transform);

        return transform;
    }

    private void getangle(){

        if(){


        }else


    }

}
