package server;

import entities.Enenmy;
import entities.Player;
import game.GamePanel;
import input.settings.Settings;
import server.packet.Packets;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    //number of player~name;id;Direction;pos;bulletsize;bullets~name;id;Direction;pos;bulletsize;bullets

    private GamePanel panel;
    private Player player;
    private ArrayList<Enenmy> enemypos;
    private Socket client;
    private int id;
    private Settings settings;
    private double speed;
    private InputStream in;
    private OutputStream out;
    private String name;
    private String ip;
    private boolean play = true;
    private boolean escape = false;
    private boolean online = false;

    public Client(int h, int w, boolean online_, String name_,String ip_) {
        settings = new Settings();
        name = name_;
        ip = ip_;
        player = new Player(name, settings);
        panel = new GamePanel(h, w);
        player.setpos(100.0, 100.0);
        panel.addPlayer(player);
        enemypos = new ArrayList<Enenmy>();
        speed = settings.getPlayerspeed();
        this.online = online_;


    }

    public void play() {
        try {
            if (online) {
                login();
            }
            while (play&&!escape) {
                escape = panel.isEscape();
                if (panel.isDeath()) {
                    dead();
                }
                if (!escape) {
                    Thread.sleep(10);
                    panel.playerupdate(speed);
                    panel.enemyupdate(enemypos);
                    panel.checkcollide();
                } else {
                    logout();
                }
                panel.repaint();

            }
            panel.repaint();
            Thread.sleep(30);
            panel.close();
//            panel.setVisible(false);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void login() {
        try {
            client = new Socket(ip, settings.getPort());
            Packets pack = new Packets("welcome", name, processid(name));
            out = client.getOutputStream();
            PrintWriter print = new PrintWriter(out);
//            System.out.println(pack.getMerge());
            Thread.sleep(20);
            print.write(pack.getMerge());
            print.flush();
//            System.out.println("message send");
            sendDefauld();
            reviceDefauld();
            System.out.println("has Logd in");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendDefauld() {
        new Thread(() -> {
            try {
                while (play) {
                    Thread.sleep(20);
                    out = client.getOutputStream();
                    PrintWriter print = new PrintWriter(out);
//                    System.out.println(panel.getBullets().size());
                    Packets pack = new Packets("default", player, panel.getBullets());
                    print.write(pack.getMerge());
//                    System.out.print(pack.getMerge());
                    print.flush();
                    Thread.yield();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void reviceDefauld() {
        new Thread(() -> {
            try {
                while (play) {
                    in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String s = null;
                    while ((s = reader.readLine()) != null) {
//                        System.out.println(s);
                        if (!s.equals("0")) {
                            String posall[] = s.split("~");

                            if (Integer.parseInt(posall[0]) > enemypos.size()) {
                                for (int i = 1; i < posall.length; i++) {
                                    String[] enemy = posall[i].split(";");
                                    int counter = 0;
                                    for (int j = 0; j < enemypos.size(); j++) {
                                        if (enemypos.get(j).getId() != Integer.parseInt(enemy[1])) {
                                            counter++;
                                        }
                                    }
                                    if (counter != enemypos.size() - 1) {
                                        Enenmy enenmy = new Enenmy(enemy[0], Integer.parseInt(enemy[1]), Packets.bullets(enemy[4], enemy[5]));
                                        enemypos.add(enenmy);
                                    }
                                }
                            }

                            for (int i = 1; i < posall.length; i++) {
                                String[] enemy = posall[i].split(";");
                                for (int j = 0; j < enemypos.size(); j++) {
                                    if (enemypos.get(j).getId() == Integer.parseInt(enemy[1])) {
                                        String[] pos = enemy[3].split(",");
                                        enemypos.get(j).setpos(Double.parseDouble(pos[0]), Double.parseDouble(pos[1]));
                                        enemypos.get(j).setDirection(enemy[2]);
//                                    System.out.println(enemy[2]);
                                        enemypos.get(j).setBullets(Packets.bullets(enemy[4], enemy[5]));
                                    }
                                }
                            }

//                         Logout funktion neu mahcen funktioniert nicht
                            if (Integer.parseInt(posall[0]) < enemypos.size()) {
                                ArrayList<Enenmy> enemynew = new ArrayList<Enenmy>();
                                for (int i = 1; i < posall.length; i++) {
                                    String[] enemy = posall[i].split(";");
                                    for (int j = 0; j < enemypos.size(); j++) {
                                        if (enemypos.get(j).getId() == Integer.parseInt(enemy[1])) {
                                            enemynew.add(enemypos.get(j));
                                        }
                                    }
                                }
                                enemypos = enemynew;
                            }
                        } else {
                            enemypos.removeAll(enemypos);
                        }
                    }
                    Thread.yield();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // muss ich auch noch machen
    private void logout() {
        try {
            if(online) {
                play = false;
                out = client.getOutputStream();
                PrintWriter print = new PrintWriter(out);
                Packets logout = new Packets("leave", id);
                Thread.sleep(20);
                print.write(logout.getMerge());
                print.flush();
//                System.out.println("left");
                play = false;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void dead() {
        System.out.println("Dead");
        panel.setDead(true);
        panel.repaint();
            logout();
    }

    private String processid(String s) {
        String jump = s;
        String out = "1";

        char[] sy = jump.toCharArray();
        for (int i = 0; i < sy.length; i += 3) {
            out += (int) sy[i];
        }

        if (out.length() > 5) {
            out = "1" + out.substring(out.length() - 5);
        }

        if (out.length() < 5) {
            for (int i = out.length(); i < 6; i++) {
                out += i;
            }
        }
        return out;
    }




}
