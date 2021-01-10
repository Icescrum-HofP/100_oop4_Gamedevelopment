package world;

import world.Vektor;

import java.awt.*;

public class HitBox extends Colide {


    public HitBox(double x_, double y_, double h_, double w_) {
        super(x_, y_, h_, w_);
    }


    @Override
    public String toString() {
        return getW() + "|" + getH() + "|" + getX() + "|" + getY();
    }
}
