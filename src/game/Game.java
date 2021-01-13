package game;

import Serverold.Clientold;
import Serverold.Serverold;

import java.util.Scanner;


public class Game {
// old version

    public Game(int h, int w) {

        System.out.println(" client und server");
        Scanner in = new Scanner(System.in);
        String input = in.next();

        System.out.println(input);

        if (input.equals("client")) {
            startclient(h, w);
        }
        if (input.equals("server")) {
            startserver();
        }

    }

    private void startclient(int h, int w) {
        Clientold client = new Clientold(h, w);
        client.start();
    }

    private void startserver() {

        Serverold server = new Serverold();
        server.start();

    }

}
