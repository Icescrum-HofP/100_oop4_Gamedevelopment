package game;

import server.Client;
import server.Server;

import java.util.Scanner;


public class Game {



    public Game(int h, int w) {

        System.out.println(" client und server");
        Scanner in = new Scanner(System.in);
        String input = in.next();

        System.out.println(input);

        if(input.equals("client")) {
            startclient(h, w);
        }
        if(input.equals("server")){
            startserver();
        }


    }

    private void startclient(int h,int w) {
        Client client = new Client(h,w);
        client.start();
    }

    private void startserver(){

        Server server = new Server();
        server.start();

    }





}
