package modele;

public class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot(int x, int y){
        this.x = x;
        this.y = y;
        this.direction = Direction.BAS;
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public Direction getDirection(){return direction;}

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setDirection(Direction d){
        this.direction = d;
    }

}