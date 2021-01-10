package world.input;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class Tiles {

    private BufferedImage[][] tieles;
    private int x;
    private int y;
    private int high;
    private int with;
    private int xoffset;
    private int yoffset;

    public Tiles(String path_, int high_, int with_, int xoffset_, int yoffset_) {
        high = high_;
        with = with_;
        xoffset = xoffset_;
        yoffset = yoffset_;
        try {
            String path = path_;

            BufferedImage image = ImageIO.read(new File(path));

            this.x = image.getHeight(null) / high;
            this.y = image.getWidth(null) / with;
//            System.out.println(x + "|" + y);
            tieles = new BufferedImage[x][y];

            for (int i = 0; i < tieles.length; i++) {
                for (int j = 0; j < tieles[i].length; j++) {
                    RenderedImage imgr = image.getSubimage((j * (high + yoffset)), (i * (with + xoffset)), with, high);
                    tieles[i][j] = (BufferedImage) imgr;
                }
            }
        } catch (IOException e) {
        }
    }

    public BufferedImage getTieles(int inx, int iny) {
        return tieles[inx][iny];
    }

    public BufferedImage[][] getTilearray() {
        return tieles;
    }

    ;

    public void paint(Graphics g) {
        Graphics2D object = (Graphics2D) g;
        object.drawImage(tieles[0][1], 500, 500, null);
        object.drawImage(tieles[0][2], 400, 400, null);

    }

    //getter

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHigh() {
        return high;
    }

    public int getWith() {
        return with;
    }

    public int getXoffset() {
        return xoffset;
    }

    public int getYoffset() {
        return yoffset;
    }
}
