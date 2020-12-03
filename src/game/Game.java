package game;

import world.Player;
import world.Postition;

import java.awt.*;
import java.awt.desktop.ScreenSleepEvent;

public class Game {

    GamePanel panel;
    Player player = new Player("hi",12);

    public Game(int h, int w) {

        panel = new GamePanel(h, w);

    }

    public void startclient() {

        while (true) {



            update();
        }
    }


    private void update() {

        player.move(panel.getKeypressd(),10.0);
        panel.playerupdate(player);
        panel.setKeypressdnull();
        panel.repaint();



    }


}
