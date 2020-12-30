package input.settings;

import input.Fileinput;

import java.util.ArrayList;

public class Settings extends Fileinput {

    private String ip;
    private int port;
    private String avatarpath;
    private String[] avatarparameters;
    private ArrayList <String> inputlist;
    private double playerspeed;

    public Settings(String path) {
        super(path);
        inputlist = new ArrayList<String>();
        inputlist = getList();
        process();
    }

    private void process(){
        System.out.println("hier");
        for(String s : inputlist){
            System.out.println(s);
            System.out.println("hier2");
            if(s.contains("ip:")) {
                ip = s.substring(3);
                System.out.println(ip);
            }

            if(s.contains("port:")){
                port = Integer.parseInt(s.substring(5));
                System.out.println(port);
            }

            if(s.contains("avatar:")){
                avatarpath = s.substring(7);
                System.out.println(avatarpath);
            }

            if(s.contains("avatarparameters:")){
                String m= s.substring(17);
                avatarparameters = m.split(",");
                System.out.println(m);
            }

            if(s.contains("playerspeed:")){
                playerspeed = Double.parseDouble(s.substring(12));

            }

        }
    }

    // Getter

    public double getPlayerspeed() {
        return playerspeed;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getAvatarpath() {
        return avatarpath;
    }

    public String[] getAvatarparameters() {
        return avatarparameters;
    }
}
