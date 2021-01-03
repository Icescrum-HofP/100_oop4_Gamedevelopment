package entities;

import input.settings.Settings;
import world.Colide;
import world.Pos;
import world.Vektor;
import world.input.Tiles;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Vector;

public class Player extends Colide {

    private String name;
    private  int id = 0;
    private int helth;
    private Tiles avatar;
    private Settings settings;
    private String[] parameters;
    private String direction;
    private Pos positionbevor;
    private int damage;
    private int angle;
    private boolean life;

    public Player(String name_, Settings settings_){
        super(100.0,100.0,40.0,40.0);
        settings=settings_;
        name = name_;
        setParameters();
    }

    public Player(String name_, Settings settings_,int id_){
        super(100.0,100.0,40.0,40.0);
        id = id_;
        settings=settings_;
        name = name_;
        setParameters();
    }

    private void setParameters (){
        positionbevor = new Pos(100.0,100.0);
        helth = 100;
        life = true;
        damage = 10;
        direction="down";
        parameters = settings.getAvatarparameters();
        setHW(Double.parseDouble(parameters[0]),Double.parseDouble(parameters[1]));
        avatar = new Tiles(settings.getAvatarpath(),Integer.parseInt(parameters[0]),Integer.parseInt(parameters[1]),Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3]));
    }


    public void paintComponent(Graphics g) {
        if(life) {
            Graphics2D g2 = (Graphics2D) g;
            //g2.drawRect((int) super.getX(), (int) super.getY(), (int)h_, (int)w_);
            g2.setColor(Color.green);
            g2.drawString(name, (int) super.getX(), (int) super.getY() - 10);
            drawTank(g2);
            //g2.drawImage(avatar.getTieles(0,0),(int) super.getX(), (int) super.getY(),null );
        }
    }

    public void drawTank(Graphics2D graphics) {
        Graphics2D graphics2 = (Graphics2D) graphics.create();
        AffineTransform latch = graphics2.getTransform();
        graphics.drawImage(
                avatar.getTieles(0,0),
                transform(graphics2),
                null
        );
        graphics.setTransform(latch);
       // graphics.dispose();
    }

    public AffineTransform transform(Graphics2D graphics) {
        AffineTransform transform;
        transform = AffineTransform.getTranslateInstance((getX()+ getW()/2), (getY() + getH()/2));
        getangle();
        transform.setToRotation(Math.toRadians(angle),(getX()+ getW()/2),(getY() + getH()/2));
        graphics.transform(transform);

        transform.translate(getX(), getY());
        graphics.transform(transform);

        return transform;
    }

    private void getangle(){

        direction=getMovedirection();

        switch(direction){
            case"up":
                angle=180;
                break;
            case"upright":
                angle=225;
                break;
            case"upleft":
                angle=135;
                break;
            case"down":
                angle=0;
                break;
            case"downleft":
                angle=45;
                break;
            case"downright":
                angle=315;
                break;
            case"right":
                angle=270;
                break;
            case"left":
                angle=90;
                break;
        }
    }

    public Pos getPositionbevor() {
        return positionbevor;
    }

    public Pos getPos(){
        return new Pos(getX(),getY());
    }

    public void setPositionbevor(Pos positionbevor) {
        this.positionbevor = positionbevor;
    }

    public void gethit(){
        helth = helth -damage;
        if(helth <= 0 ){
            life = false;
            setX(0.0);
            setY(0.0);
        }
    }

    public int getId() {
        return id;
    }
}
