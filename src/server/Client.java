package server;

import game.GamePanel;
import world.Player;
import world.Postition;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private GamePanel panel;
    private Player player = new Player("hi", 12);
    private ArrayList<Player> enemypos;
    private Socket client;
    InputStream in;
    OutputStream out;

    public Client(int h, int w) {
        try {
            client = new Socket("localhost", 5556);
            panel = new GamePanel(h, w);
            panel.addPlayer(player);
            enemypos = new ArrayList<Player>();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fehler");
        }
        System.out.println("client startet");
    }

    public void start() {
        recive();
        while (true) {
            update();
            serverupdate();
        }
    }

    public void serverupdate() {
        try {
            out = client.getOutputStream();
            PrintWriter print = new PrintWriter(out);
            print.write(player.toString() + "\n");
            print.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recive() {
        new Thread(() -> {
            try {
                in = client.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String s = null;
                while ((s = reader.readLine()) != null) {
                    String posall[] = s.split("|");
                    if (Integer.parseInt(posall[0]) == enemypos.size()) {
                        for (int i = enemypos.size(); i <= Integer.parseInt(posall[0]); i++) {
                            enemypos.add(new Player("enemy " + i, i));
                        }
                    }
                    for (int i = 0; i < enemypos.size(); i++) {
                        enemypos.get(i).setpos(Double.parseDouble(posall[(1 * (i + 1))]), Double.parseDouble(posall[2 * (i + 1)]));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void update() {
        player.move(panel.getKeypressd(), 10.0);
        panel.playerupdate(player);
        panel.setKeypressdnull();
        panel.enemyupdate(enemypos);
        panel.repaint();
    }
}
