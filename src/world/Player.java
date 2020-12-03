package world;

public class Player extends Postition{

    private String name;
    private int id;

    public Player(String name_, int id_){
        name = name_;
        id = id_;

    }


    public void move(String direction, double speed){

        switch(direction){
            case "right":
                super.addpos(1*speed, 0.0);
                break;
            case "left":
                super.addpos(-1*speed,0.0);
                break;
            case "up":
                super.addpos(0.0,-1*speed);
                break;
            case "down":
                super.addpos(0.0,1*speed);
                break;
        }


    }










}
