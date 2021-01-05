package server.packet;

import entities.Bullet;
import world.Pos;

import java.net.Socket;
import java.util.ArrayList;

public class User {

    private String name;
    private Pos lastpos =new Pos(0.0,0.0);
    private int id;
    private ArrayList<Pos> pos;
    private ArrayList<ArrayList<Bullet>> bullets;
    private Socket socket;
    private boolean go = false;

    public User(){
        pos = new ArrayList<>();
        bullets = new ArrayList<>();
    }

    public void addnewPos(String pos_){
        String[] s = pos_.split(",");
        pos.add(new Pos(Double.parseDouble(s[0]),Double.parseDouble(s[1])));
    }

    public void addBullet(String bullets_){
        String[] s = bullets_.split(",");
        ArrayList<Bullet> bulletArrayList = new ArrayList<>();
        for(int i = 0 ;i<s.length;i++){
            bulletArrayList.add(new Bullet(new Pos(Double.parseDouble(s[i*2]),Double.parseDouble(s[1+(i*2)]))));
        }
        bullets.add(bulletArrayList);
    }

    //getter && setter

    public boolean isGo() {
        return go;
    }

    public void setId(int id) {
        this.id = id;
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
}
