import java.awt.*;

/**
 * Created by Ramtin on 2016-07-07.
 */
public class Player {
    private int x,y,r; // r är radie, inge sprites så player är en cirkel
    private int dx;
    private int dy;
    private int speed;
    private int lives;
    private boolean left,right,up,down,firing;
    private long firingTimer;
    private long firingDelay;
    private Color color1;
    private Color color2;
    private boolean recovering;
    private long recoveryTimer;
    private int score;
    private int powerLevel;
    private int power;
    private int[] requiredPower = {1, 2, 3, 4, 5};

    public Player(){
        x = GamePanel.WIDTH;
        y = GamePanel.HEIGHT;
        r = 5;

        dx =0;
        dy =0;
        speed = 5;
        lives = 3;

        firing = false;
        firingTimer = System.nanoTime();
        firingDelay = 200;

        color1 = Color.WHITE;
        color2 = Color.RED;
        recovering = false;
        recoveryTimer = 0;
        score = 0;

    }

    public void update(){
        if(left){
            dx = -speed;
        }
        if(right){
            dx = speed;
        }
        if(up){
            dy = -speed;
        }
        if(down){
            dy = speed;
        }
        x += dx;
        y += dy;

        if(x < r) x = r;
        if(y < r) y = r;
        if(x > GamePanel.WIDTH - r) x = GamePanel.WIDTH - r;
        if(y >GamePanel.HEIGHT - r) y = GamePanel.HEIGHT -r;

        dx = 0;
        dy = 0;
        if(firing){
            long elapsed = (System.nanoTime() - firingTimer) / 1000000;
            if(elapsed > firingDelay){
                firingTimer = System.nanoTime();

                if(powerLevel <2 ){
                    GamePanel.bullets.add(new Bullet(270, x, y));
                }
                else if(powerLevel < 4){
                    GamePanel.bullets.add(new Bullet(270, x +5, y));
                    GamePanel.bullets.add(new Bullet(270, x -5, y));
                }else{
                    GamePanel.bullets.add(new Bullet(270, x, y));
                    GamePanel.bullets.add(new Bullet(265, x -5, y));
                    GamePanel.bullets.add(new Bullet(275, x +5, y));
                }
            }
        }
        if(recovering){
            long elapsed = (System.nanoTime() - recoveryTimer) / 1000000;
            if(elapsed > 2000){
                recovering = false;
                recoveryTimer = 0;
            }
        }


    }
    public void draw(Graphics2D g){

        if(recovering){
            g.setColor(color2);
            g.fillOval(x-r, y-r, 2*r, 2*r);

            g.setStroke(new BasicStroke(3));
            g.setColor(color2.darker());
            g.drawOval(x-r, y-r, 2*r, 2*r);
            g.setStroke(new BasicStroke(1));
        }else{
            g.setColor(color1);
            g.fillOval(x-r, y-r, 2*r, 2*r);

            g.setStroke(new BasicStroke(3));
            g.setColor(color1.darker());
            g.drawOval(x-r, y-r, 2*r, 2*r);
            g.setStroke(new BasicStroke(1));
        }


    }
    public void loseLife(){
        lives --;
        recovering = true;
        recoveryTimer = System.nanoTime();
    }
/////////////////////////////////////////////////////////////////
    // Setters

    public void setLeft(boolean b){left = b; }
    public void setRight(boolean b){right = b; }
    public void setDown(boolean b){down = b; }
    public void setup(boolean b){up = b; }
    public void setFiring(boolean b) {firing = b; }
    public void addScore(int i){ score += i;}

///////////////////////////////////////////////////////////////

    // Getters
    public int getLives() { return lives; }
    public boolean isRecovering(){ return recovering;}
    public int getx(){ return x; }
    public int gety(){ return y; }
    public int getr(){ return r; }
    public int getScore(){ return score;}
    public int getPowerLevel(){ return powerLevel;}
    public int getPower(){ return power;}
    public int getRequiredPower(){ return requiredPower[powerLevel]; }

////////////////////////////////////////////////////////////////
    public void gainLife() { lives ++; }

    public void increasePower(int i) {
        power += i;
        if(powerLevel == 4){
            if(power > requiredPower[powerLevel]){
                power = requiredPower[powerLevel];
            }
            return;
        }
        if(power >= requiredPower[powerLevel]){
            power -= requiredPower[powerLevel];
            powerLevel++;
        }
    }




}
