package server;

import entities.Bullet;
import entities.Enenmy;
import entities.Player;
import game.GamePanel;
import input.settings.Settings;
import server.packet.Packets;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Clientnew {

    //number of player~name;id;Direction;pos;bulletsize;bullets~name;id;Direction;pos;bulletsize;bullets

    private GamePanel panel;
    private Player player;
    private ArrayList<Enenmy> enemypos;
    private Socket client;
    private int id;
    private int packages;
    private Settings settings;
    private double speed;
    private InputStream in;
    private OutputStream out;
    private String name;

    public Clientnew(int h, int w, boolean online, String name_) {
        settings = new Settings();
        name = name_;
        player = new Player(name, settings);
        panel = new GamePanel(h, w);
        player.setpos(100.0, 100.0);
        panel.addPlayer(player);
        enemypos = new ArrayList<Enenmy>();
        packages = 1;
        speed = settings.getPlayerspeed();

        if (online) {
            login();
        }
        play();

    }


    private void play() {
        try {
            while (true) {
                Thread.sleep(10);
                panel.playerupdate(speed);
                panel.enemyupdate(enemypos);
                panel.checkcollide();
                panel.repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    private void login() {
        try {

            client = new Socket(settings.getIp(), settings.getPort());
            Packets pack = new Packets("welcome", name, processid(name));
            out = client.getOutputStream();
            PrintWriter print = new PrintWriter(out);

            System.out.println(pack.getMerge());

            Thread.sleep(20);

            print.write(pack.getMerge());
            print.flush();
            System.out.println("message send");
            sendDefauld();
            reviceDefauld();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendDefauld() {
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(20);
                    out = client.getOutputStream();
                    PrintWriter print = new PrintWriter(out);
                    Packets pack = new Packets("default", player, panel.getBullets());
                    print.write(pack.getMerge());
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
                while (true) {
                    in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String s = null;
                    while ((s = reader.readLine()) != null) {
                        System.out.println(s);
                        String posall[] = s.split("~");

                        if(Integer.parseInt(posall[0]) > enemypos.size()){
                            for(int i=1; i<posall.length;i++){
                                String[] enemy = posall[i].split(";");
                                int counter =0 ;
                                for(int j=0;j<enemypos.size();j++){
                                    if(enemypos.get(j).getId() != Integer.parseInt(enemy[1])){
                                        counter ++;
                                    }
                                }
                                if(counter != enemypos.size()-1){
                                    Enenmy enenmy = new Enenmy(enemy[0],Integer.parseInt(enemy[1]),Packets.bullets(enemy[4],enemy[5]));
                                    enemypos.add(enenmy);
                                }
                            }
                        }

                        for(int i=1;i<posall.length;i++){
                            String[] enemy = posall[i].split(";");
                            for(int j=0;j<enemypos.size();j++){
                                if(enemypos.get(j).getId() == Integer.parseInt(enemy[1])){
                                    String[] pos = enemy[3].split(",");
                                    enemypos.get(j).setpos(Double.parseDouble(pos[0]),Double.parseDouble(pos[1]));
                                    enemypos.get(j).setDirection(enemy[2]);
                                    enemypos.get(j).setBullets(Packets.bullets(enemy[4],enemy[5]));
                                }
                            }
                        }

                        // Logout funktion neu mahcen funktioniert nicht
                        if(Integer.parseInt(posall[0]) < enemypos.size()){
                            for(int i=1; i<posall.length;i++){
                                String[] enemy = posall[i].split(";");
                                int index;
                                int counter =0 ;
                                for(int j=0;j<enemypos.size();j++){
                                    if(enemypos.get(j).getId() != Integer.parseInt(enemy[1])){
                                        counter ++;
                                    }
                                }
                                if(counter == 0){
                                    index=i;
                                }
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
// muss ich auch noch machen
    private void Logout() {


    }

    private String processid(String s) {
        String jump = s;
        String out = "1";

        if(s.length()>10){
            jump=jump.substring(s.length()-(s.length()-7));
        }
        char[] sy = jump.toCharArray();
        for (int i = 0; i < sy.length; i+= 3) {
            out += (int) sy[i];
        }

        return out;

    }


}
