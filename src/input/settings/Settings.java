package input.settings;

import input.Fileinput;

import java.net.URL;
import java.util.ArrayList;

public class Settings extends Fileinput {

    private static String path = "pics/Settings";
    private String ip;
    private int port;
    private String avatarpath;
    private String[] avatarparameters;
    private ArrayList<String> inputlist;
    private double playerspeed;
    private String spritesheedpath;
    private String[] spritesheedparameters;

    public Settings() {
        super(path);
        inputlist = new ArrayList<String>();
        inputlist = getList();
        process();
    }

    private void process() {

        for (String s : inputlist) {

            if (s.contains("ip:")) {
                ip = s.substring(3);
            }

            if (s.contains("port:")) {
                port = Integer.parseInt(s.substring(5));
            }

            if (s.contains("avatar:")) {
                avatarpath = s.substring(7);
            }

            if (s.contains("avatarparameters:")) {
                String m = s.substring(17);
                avatarparameters = m.split(",");
            }

            if (s.contains("playerspeed:")) {
                playerspeed = Double.parseDouble(s.substring(12));

            }

            if (s.contains("spritesheed:")) {
                spritesheedpath = s.substring(12);
            }

            if (s.contains("spritesheedparameters:")) {
                String m = s.substring(22);
                spritesheedparameters = m.split(",");
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

    public String getSpritesheedpath() {
        return spritesheedpath;
    }

    public String[] getSpritesheedparameters() {
        return spritesheedparameters;
    }


//    public static void main(String[] args) {
//        Settings s = new Settings();
//        System.out.println(s.ip);
//    }

}
