package server;

import game.GamePanel;
import entities.Player;
import input.settings.Serversettings;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private GamePanel panel;
    private Player player = new Player("Player", 12);
    private ArrayList<Player> enemypos;
    private Socket client;
    private int packages;
    private Serversettings settings;
    private double speed;
    private InputStream in;
    private OutputStream out;

    public Client(int h, int w) {
        try {
            client = new Socket( settings = new Serversettings("test");
            client = new Socket(settings.getIp(), settings.getPort()););
            panel = new GamePanel(h, w);
            panel.addPlayer(player);
            player.setpos(100.0,100.0);
            panel.addPlayer(player);
            enemypos = new ArrayList<Player>();
            packages = 1;
            speed = 2.3;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fehler");
        }
        System.out.println("client startet");
    }

    public void start(){
        recive();
        serverupdate();
        try{
        while (true) {
            Thread.sleep(10);
            update();
        }}catch (InterruptedException e){e.printStackTrace();}
    }

    public void serverupdate() {
        new Thread(() -> {
            try {
                while (true) {
                    if (packages > 21) {
                        Thread.sleep(50);
                    }
                    packages++;
                    out = client.getOutputStream();
                    PrintWriter print = new PrintWriter(out);
                    print.write(player.toString() + "\n");
                    print.flush();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();
        }).start();
    }

    public void recive() {
        new Thread(() -> {
            try {
                while (true) {
                    in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String s = null;
                    while ((s = reader.readLine()) != null) {
                        String posall[] = s.split(",");
                        System.out.println(s);
                        if (posall.length >= 3) {
                            if (Integer.parseInt(posall[0]) != enemypos.size() && enemypos.size() < Integer.parseInt(posall[0])) {
                                for (int i = enemypos.size(); i < Integer.parseInt(posall[0]); i++) {
                                    enemypos.add(new Player("enemy " + i, i));
                                    System.out.println("ich bin im erzeuger");
                                }
                            }
                            for (int i = 0; i < enemypos.size(); i++) {
                                System.out.println(posall[(1 * (i + 1))]);
                                System.out.println(posall[2 * (i + 1)]);
                                enemypos.get(i).setpos(Double.parseDouble(posall[(1 * (i + 1))]), Double.parseDouble(posall[2 * (i + 1)]));
                               // System.out.println(enemypos.get(i).toString() + " ///");
                            }
                        }
                    }
                    Thread.yield();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void update() {
        panel.playerupdate(speed);
        panel.enemyupdate(enemypos);
        panel.repaint();
    }
}
