package world.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapReader {

    private BufferedReader read;
    private int mapid;
    private int map[][];
    private ArrayList<String[]> mapinput;

    public MapReader(int mapid_) {
        mapid = mapid_;
        try {
            read = new BufferedReader(new FileReader(new File("src/map/map"+ mapid_)));
            mapinput = new ArrayList<String[]>();

            String s;
            while ((s = read.readLine()) != null) {
                mapinput.add(s.split(""));
            }
            process();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process() {
        map = new int[mapinput.size()][mapinput.get(0).length];

        for (int i = 0; i < mapinput.size(); i++) {

            String in[] = mapinput.get(i);

            for (int j = 0; j < mapinput.get(i).length; j++) {

                switch (in[j]) {
                    case "x":
                        map[i][j] = 11;
                        break;
                    case "h":
                        map[i][j] = 12;
                        break;
                    case "j":
                        map[i][j] = 13;
                        break;
                    case "o":
                        map[i][j] = 14;
                        break;
                }
            }
        }
    }

    public int[][] getMap() {
        return map;
    }

    public static void main(String[] args) {
        MapReader mapReader = new MapReader(0);
        int[][] map = mapReader.getMap();

        for(int i = 0; i< map.length; i++ ){
            for(int j = 0; j < map[i].length;j++){
                System.out.println(map[i][j]);
            }
        }
    }
}
