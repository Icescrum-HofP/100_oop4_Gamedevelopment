package world.input;

import world.Vektor;

import java.awt.image.BufferedImage;

public class Tile extends Vektor {

    private BufferedImage img;
    public Tile(double x_, double y_, BufferedImage img_) {
        super(x_, y_);
        img = img_;
    }

    //Getter
    public BufferedImage getImg() {
        return img;
    }
}
