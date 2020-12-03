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
}
