import java.awt.*;

/**
 * Created by Ramtin on 2016-07-07.
 */
public class PowerUp {

    private double x;
    private double y;
    private int r;
    private Color color1;

    // Power types will be:
    // 1 -- +1 life
    // 2 -- +1 power
    // 3 -- +2 power
    // 4 -- slow down time

    private int type;

    public PowerUp(int type, double x, double y){
        this.type = type;
        this.x = x;
        this.y = y;

        if(type == 1 ){
            color1 = Color.PINK;
            r = 3;

        }
        if(type ==2){
            color1 = Color.YELLOW;
            r = 3;
        }
        if(type == 3){
            color1 = Color.YELLOW;
            r = 5;
        }
        if(type == 4){
            color1 = Color.WHITE;
            r= 3;
        }

    }
    public boolean update(){
        y += 2;
        if(y > GamePanel.HEIGHT + r){
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g){
        g.setColor(color1);
        g.fillRect((int) (x-r), (int)(y-r), 2*r, 2*r);

        g.setStroke(new BasicStroke(3));
        g.setColor(color1.darker());
        g.drawRect((int) (x-r), (int)(y-r), 2*r, 2*r);
        g.setStroke(new BasicStroke(1));
    }

    // Getters
    public double getx() { return x;}
    public double gety(){ return y;}
    public int getr(){ return r;}
    public int getType(){ return type;}
}
