package game;

import world.Player;
import world.Postition;

import java.awt.*;

public class Game {

    GamePanel panel;
    Postition player = new Player("hi",12);

    public Game(int h, int w) {

        panel = new GamePanel(h, w);

    }

    public void start() {

        while (true) {
            update();
        }
    }


    private void update() {

        switch (panel.getKeypressd()) {

            case "up":
            case "left":
            case "right":
               //palyer

                break;


        }

    }


}
