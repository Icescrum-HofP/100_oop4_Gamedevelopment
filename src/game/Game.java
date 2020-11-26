package game;

import java.awt.*;

public class Game {

    GamePanel panel;

    public Game(int h,int w){

        panel = new GamePanel(h,w);

    }

    public void start(){

        while (true) {
            Graphics g = null;
            panel.paintComponent(g);
        }
    }








}
