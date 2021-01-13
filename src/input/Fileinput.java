package input;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Fileinput {

    private BufferedReader read;
    private URL filepath;
    private ArrayList<String> list;

    public Fileinput(String path) {
        filepath = getClass().getClassLoader().getResource(path);;
        list = new ArrayList<String>();
        fileread();
    }

    // File reader
    private void fileread() {
        String in = "";

        try {
            read = new BufferedReader(new InputStreamReader(filepath.openStream()));
            String s;
            while ((s = read.readLine()) != null) {
                list.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getter

    public ArrayList<String> getList() {
        return list;
    }
}
