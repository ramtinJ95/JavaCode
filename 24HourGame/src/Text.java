import java.awt.*;

/**
 * Created by Ramtin on 2016-07-07.
 */
public class Text {

    private double x;
    private double y;
    private long time;
    private String s;
    private long start;

    public Text(double x, double y, long time, String s ){
        this.x = x;
        this.y = y;
        this.time = time;
        this.s = s;
        start = System.nanoTime();
    }

    public boolean update(){
        long elapsed = (System.nanoTime() - start) / 1000000;
        if(elapsed > time){
            return true;
        }
        return false;

    }
    public void draw(Graphics2D g){
        g.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        g.setColor(Color.WHITE);
        g.drawString(s, (int) x, (int) y);

    }
}
