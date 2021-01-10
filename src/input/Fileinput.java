package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Fileinput {

    private BufferedReader read;
    private String filepath;
    private ArrayList<String> list;

    public Fileinput(String path) {
        filepath = path;
        list = new ArrayList<String>();
        fileread();
    }

    // File reader
    private void fileread() {
        String in = "";

        try {
            read = new BufferedReader(new FileReader(new File(filepath)));
            String s;
            while ((s = read.readLine()) != null) {
                list.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getter

    public String getFilepath() {
        return filepath;
    }

    public ArrayList<String> getList() {
        return list;
    }
}
