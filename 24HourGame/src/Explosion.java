import java.awt.*;

/**
 * Created by Ramtin on 2016-07-07.
 */
public class Explosion {

    private double x;
    private double y;
    private int r;
    private int maxRadius;

    public Explosion(double x, double y, int r, int max){
        this.x = x;
        this.y = y;
        this.r = r;
        maxRadius = max;
    }

    public boolean update(){
        r++;
        if(r >= maxRadius){
            return true;
        }
        return false;
    }
    public void draw(Graphics2D g){
        g.setColor(new Color(255, 255, 255,128));
        g.drawOval((int)(x-r), (int)(y -r), 2*r, 2*r);

    }
}
