package world;

public abstract class Vektor {

    private double x;
    private double y;
    private double h;
    private double w;

    // Constuctors

    public Vektor(double x_ , double y_){
        x = x_;
        y = y_;
    }

    public Vektor(double x_ , double y_,double h_,double w_){
        x = x_;
        y = y_;
        h = h_;
        w = w_;
    }

    // Other

    public String toString(){
        return x+","+y;
    }



    public void addpos (double x_ , double y_){
        x = x_ + x;
        y = y_ + y;
        System.out.println(x +"|" +y);
    }

    public void setppos(Vektor p){
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

    // Setter

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setpos (double x_ , double y_){
        x= x_;
        y= y_;
    }

    // Getter

    public double getH() {
        return h;
    }

    public double getW() {
        return w;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }



}
