package world;

public abstract class Postition {
    private Double x;
    private Double y;

    public Postition(){
        x = 0.0;
        y = 0.0;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public void setpos (Double x_ , Double y_){
        x= x_;
        y= y_;
    }

    public void addpos (Double x_ , Double y_){
        x = x_ + x;
        y = y_ + y;
    }
}
