package server;

import entities.Enenmy;
import game.GamePanel;
import entities.Player;
import input.settings.Settings;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private GamePanel panel;
    private Player player;
    private ArrayList<Enenmy> enemypos;
    private Socket client;
    private int packages;
    private Settings settings;
    private double speed;
    private InputStream in;
    private OutputStream out;

    public Client(int h, int w) {
        try {
            settings = new Settings();
            player =new Player("Icescrum",settings);
            client = new Socket(settings.getIp(), settings.getPort());;
            panel = new GamePanel(h, w);
            panel.addPlayer(player);
            player.setpos(100.0,100.0);
            panel.addPlayer(player);
            enemypos = new ArrayList<Enenmy>();
            packages = 1;
            speed = settings.getPlayerspeed();
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
                       // System.out.println(s);
                        if (posall.length >= 3) {
                            if (Integer.parseInt(posall[0]) != enemypos.size() && enemypos.size() < Integer.parseInt(posall[0])) {
                                for (int i = enemypos.size(); i < Integer.parseInt(posall[0]); i++) {
                                    Enenmy neu = new Enenmy();
                                    enemypos.add(neu);
                                }
                            }
                            for (int i = 0; i < enemypos.size(); i++) {
                             //   System.out.println(posall[(1 * (i + 1))]);
                                // System.out.println(posall[2 * (i + 1)]);
                                enemypos.get(i).setpos(Double.parseDouble(posall[(1 * (i + 1))]), Double.parseDouble(posall[2 * (i + 1)]));
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
