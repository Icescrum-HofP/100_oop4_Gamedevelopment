package input.settings;

import input.Fileinput;

import java.util.ArrayList;

public class Serversettings extends Fileinput {

    private String ip;
    private int port;
    ArrayList <String> inputlist;

    public Serversettings(String path) {
        super(path);
        inputlist = new ArrayList<String>();
        inputlist = getList();
        process();
    }

    private void process(){

        for(String s : inputlist){
            if(s.contains("ip:")) {
                ip = s.substring(3);
            }

            if(s.contains("port:")){
                port = Integer.parseInt(s.substring(5));
            }

        }
    }

    // Getter


    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
