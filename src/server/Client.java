package server;

import game.GamePanel;
import world.Player;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.server.LogStream;

public class Client {

    private GamePanel panel;
    private Player player = new Player("hi", 12);
    private Socket client;

    public Client(int h, int w) {

        try {
            client = new Socket("localhost", 5556);
            System.out.println("client start");
            panel = new GamePanel(h, w);
            panel.addPlayer(player);

            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            PrintWriter print = new PrintWriter(out);
            print.write("hey iam connectet");
            print.flush();
            print.close();



        }catch (IOException e){

            e.printStackTrace();
            System.out.println("fehler");

        }
        System.out.println("client startet");
    }

    public void start() {
        while (true) {
            update();
            serverupdate();
        }
    }

    public void serverupdate(){

        try{
            OutputStream out = client.getOutputStream();
            PrintWriter print = new PrintWriter(out);
            print.write("my location is behind you" + player.toString());
            print.flush();
            print.close();
        }catch ( IOException e){e.printStackTrace();}





    }


    private void update() {

        player.move(panel.getKeypressd(), 10.0);
        panel.playerupdate(player);
        panel.setKeypressdnull();
        panel.repaint();


    }


}
