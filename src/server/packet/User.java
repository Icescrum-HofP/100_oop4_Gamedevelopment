package server.packet;

import entities.Bullet;
import world.Pos;

import java.net.Socket;
import java.util.ArrayList;

public class User {

    private String name;
    private Pos lastpos = new Pos(0.0, 0.0);
    private int id;
    private ArrayList<Pos> pos;
    private ArrayList<ArrayList<Bullet>> bullets;
    private ArrayList<Bullet> bullet;
    private Socket socket;
    private boolean go = false;
    private String direction;

    public User() {
        pos = new ArrayList<>();
        bullets = new ArrayList<>();
        ArrayList<Bullet> b = new ArrayList<Bullet>();
        b.add(new Bullet(new Pos(0.0,0.0)));
        bullets.add(b);
        direction = "down";
    }

    public void addnewPos(String pos_) {
        String[] s = pos_.split(",");
        if (pos.size() > 5) {
            pos.remove(0);
        }
        pos.add(new Pos(Double.parseDouble(s[0]), Double.parseDouble(s[1])));
    }

    public void addBullet(String bullets_) {
        String[] s = bullets_.split(",");
        ArrayList<Bullet> bulletArrayList = new ArrayList<>();
//        System.out.println(s.length);
        for (int i = 0; i < s.length; i += 2) {
            bulletArrayList.add(new Bullet(new Pos(Double.parseDouble(s[i]), Double.parseDouble(s[1 + i]))));
        }
        if (bullets.size() > 10) {
            bullets.remove(0);
        }
        bullets.add(bulletArrayList);
    }

    public void setDirection(String direction_) {
        direction = direction_;
    }

    public String next() {
        String msg = name + ";" + id + ";";

        if (direction != "") {
            msg += direction + ";";
        } else {
            msg += "down;";
        }

        if (pos.size() == 0) {
            msg += lastpos.toString();
        } else {
            msg += pos.get(0).toString();
            lastpos = pos.get(0);
            pos.remove(0);
        }


        if (bullets.size() > 0) {
            int m = bullets.get(0).size() + 1;
            msg += ";" + m + ";";
            msg += lastpos.toString() + ",";
//            System.out.println("b size: "+bullets.get(0).size());
            for (int i = 0; i < bullets.get(0).size(); i++) {
                if (i < bullets.get(0).size() - 1) {
                    msg += bullets.get(0).get(i).toString() + ",";
                } else {
                    msg += bullets.get(0).get(i).toString();
                }
            }
            bullet = bullets.get(0);
           bullets.remove(0);
        } else {
            int m = bullet.size();
            msg += ";"+m+";";
            for (int i = 0; i < bullet.size(); i++) {
                if (i < bullet.size() - 1) {
                    msg += bullet.get(i).toString() + ",";
                } else {
                    msg += bullet.get(i).toString();
                }
            }
        }

//        System.out.println(msg);
        return msg;
    }

    //getter && setter
    public void go() {
        go = true;
    }

    public boolean isGo() {
        return go;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getName() {
        return name;
    }
}
