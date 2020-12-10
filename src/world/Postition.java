package world;

public abstract class Postition {
    private double x;
    private double y;

    public Postition(Double x_ , Double y_){
        x = 0.0;
        y = 0.0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setpos (double x_ , double y_){
        x= x_;
        y= y_;
    }

    public void addpos (double x_ , double y_){
        x = x_ + x;
        y = y_ + y;
        System.out.println(x +"|" +y);
    }

    public void setppos(Postition p){
        this.x = p.x;
        this.y = p.y;
    }

    public void move(boolean u, boolean d, boolean r, boolean l, double speed){
        double up = 0;
        double down = 0;
        double right = 0;
        double left= 0;

        if(u){
         up = -1;
        }
        if(d){
            down = 1;
        }
        if(r){
            right = 1;
        }
        if(l){
            left = -1;
        }

        x = x + (left* speed);
        x = x + (right * speed);
        y = y + (up * speed);
        y = y + (down * speed);

    }





    public String toString(){
        return x+","+y;
    }
}
