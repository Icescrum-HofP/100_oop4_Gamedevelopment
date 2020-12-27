package world.input;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class Tiles {
    private Image[][] tieles;
    private int x;
    private int y;
    private int pixel;

    public Tiles(String name , int pix)  {
        pixel = pix;
        try {
            String way = "src\\tilese\\" + name + ".png";

            BufferedImage image = ImageIO.read(new File(way));

            this.x= image.getHeight(null)/pix;
            this.y = image.getWidth(null)/pix;

            tieles = new Image[x][y];

            for(int i=0; i < tieles.length ;i++){
                for(int j=0; j < tieles[i].length ;j++){
                    RenderedImage imgr = image.getSubimage((j*pix),(i*pix),pix,pix);
                    tieles[i][j]=(Image)imgr;
                }
            }
        }catch( IOException e){
        }

    }

    public int getPixel() {
        return pixel;
    }

    public Image getTieles(int inx, int iny) {
        return tieles[inx][iny] ;
    }


    public void paint(Graphics g){
        Graphics2D object = (Graphics2D) g;
        object.drawImage(tieles[0][1],500,500,null);
        object.drawImage(tieles[0][2],400,400,null);

    }
}
