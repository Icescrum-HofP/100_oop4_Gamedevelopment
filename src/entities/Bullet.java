package entities;

import entities.Player;
import world.Postition;

public class Bullet extends Postition {

    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;


    public Bullet(Player p, String direction) {
        super(p.getX(), p.getY());

        switch(direction){
            case "up":
                up = true;
                break;
            case "down":
                down = true;
                break;
            case "right":
                right = true;
                break;
            case "left":
                left = true;
                break;
        }
    }
}
