package world;

import world.Vektor;


public class Colide extends Vektor {

    public Colide(double x_, double y_, double h_, double w_) {
        super(x_,y_,h_,w_);
    }


    // ABBA Colliede

    public boolean check(Vektor ob) {
        boolean status = false;
        if(getX() < ob.getX() + ob.getW() && getX() +getW() > ob.getX() && getY() < ob.getY() + ob.getH() && getY() + getH() > ob.getY()){
            status = true;
        }

        return status;
    }
}
