package world;

import world.input.MapReader;

import java.util.ArrayList;

public class Maplayer {

    private ArrayList<MapReader> maps;

    public Maplayer(){
        maps = new ArrayList<MapReader>();

        for(int i = 0; i<= 1;i++) {
            MapReader next = new MapReader(i);
            maps.add(next);
        }
    }

    public MapReader getmap(int mapid){
        return maps.get(mapid);
    }

}
