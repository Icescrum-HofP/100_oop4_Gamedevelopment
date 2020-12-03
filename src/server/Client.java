package server;

import game.GamePanel;
import world.Player;

import java.io.IOException;
import java.net.Socket;

public class Client {

    GamePanel panel;
    Player player = new Player("hi", 12);


    public Client(int h, int w) {

        try {
            Socket client = new Socket("localhost", 5555);
            System.out.println("client start");
            panel = new GamePanel(h, w);



        }catch (IOException e){

            e.printStackTrace();
            System.out.println("fehler");

        }
        System.out.println("client startet");
    }

    public void start() {
        while (true) {


            update();
        }
    }


    private void update() {

        player.move(panel.getKeypressd(), 10.0);
        panel.playerupdate(player);
        panel.setKeypressdnull();
        panel.repaint();


    }


}
