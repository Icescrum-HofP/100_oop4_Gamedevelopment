package server.packet;

import entities.Bullet;
import entities.Player;
import world.Pos;

import java.util.ArrayList;

public class Packets {

    private String msg;
    private String typ;
    private String[] processed;
    private String merg ="";

    public Packets(String msg_) {
        msg = msg_;
        process();
    }

    public Packets(String typ_, String name, String id) {
        typ = typ_;
        String[] s = new String[2];
        s[0] = name;
        s[1] = id;
        merge(s);
    }

    public Packets(String typ_, Player p, ArrayList<Bullet> bullets) {
        typ = typ_;
        merger(p,bullets);
    }

    private void process() {
        processed = msg.split(";");
    }

    private void merger(Player p, ArrayList<Bullet> b){
        merg += p.toStrings()+";";
        merg += b.size()+";";

        for(int i=0;i<b.size();i++) {
            if (i < b.size() - 1) {
                merg += b.get(i).toString() + ",";
            } else {
                merg += b.get(i).toString();
            }
        }
        if(b.size() == 0){
            merg += p.toString();
        }
    }

    private void merge(String[] s) {
        merg += s[0]+";";
        merg += s[1];

    }

    public String[] getProcessed() {
        return processed;
    }

    public String getMerge() {
        return merg+"\n";
    }

    public static ArrayList<Bullet> bullets(String number,String bullets){
        ArrayList<Bullet> b = new ArrayList<Bullet>();
        String[] in = bullets.split(",");
        if(Integer.parseInt(number) == in.length){
            System.out.println("fehler bullet number zu groß --->" + Integer.parseInt(number));
        }
        for(int i = 0;i<in.length;i+= 2){
            b.add(new Bullet(new Pos(Double.parseDouble(in[i]),Double.parseDouble(in[i+1]))));

        }

        return b;
    }
}
