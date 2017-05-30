import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Ramtin on 2016-07-07.
 */
public class GamePanel extends JPanel implements Runnable,KeyListener{

    public static int WIDTH = 600;
    public static int HEIGHT = 600;
    private Thread thread;
    private boolean running;
    private BufferedImage image;
    private Graphics2D g;
    private int FPS = 60;
    private double averageFPS;
    public static Player player;
    private static double FPSCONTROLL = 1/10;

    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies;
    public static ArrayList<PowerUp> powerUps;
    public static ArrayList<Explosion> explosions;
    public static ArrayList<Text> texts;

    private long waveStartTimer;
    private long waveStartTimerDiff;
    private int waveNumber;
    private boolean waveStart;
    private int waveDelay = 2000;

    private long slowDownTimer;
    private long slowDownTimerDiff;
    private int slowDownLength = 6000;


    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify(); // this basically says to the panel class that it is ready to do some action
        if(thread == null){
            thread = new Thread(this);
            thread.start();
        }
        addKeyListener(this);

    }

    @Override
    public void run() {
        running = true;
        image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


        long startTime;
        long URDTimeMillis;
        long waitTime;
        long totalTime = 0;
        long targetTime = 1000/FPS;
        int frameCount = 0;
        int maxFrameCount =60;

        player = new Player();
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        powerUps = new ArrayList<>();
        explosions = new ArrayList<>();
        texts = new ArrayList<>();

        waveStartTimer = 0;
        waveStartTimerDiff = 0;
        waveStart = true;
        waveNumber = 0;

        while(running){
            startTime = System.nanoTime();

            gameUpdate();
            gameRender();
            gameDraw();

            URDTimeMillis = (System.nanoTime() - startTime) / 1000000;

            waitTime = targetTime - URDTimeMillis;

            try{
                Thread.sleep((long)(waitTime - FPSCONTROLL)); // -1 to get slightly above 60 fps
            }catch (Exception e){
            }
            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == maxFrameCount){
                averageFPS = 1000.0 / ((totalTime / frameCount)/ 1000000);
                frameCount = 0;
                totalTime = 0;
            }
            if(player.getLives() == 0){
                running = false;
            }

        }
        g.setColor(new Color(0, 100, 255));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        String s = "G A M E   O V E R";
        int length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
        g.drawString(s, (WIDTH - length) /2, HEIGHT /2);
        s = "Final Score: " + player.getScore();
        length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
        g.drawString(s, (WIDTH - length) /2, HEIGHT /2 + 30);
        gameDraw();
    }

    private void gameUpdate(){

        // New wave
        if(waveStartTimer == 0 && enemies.size() == 0){
            waveNumber++;
            waveStart = false;
            waveStartTimer = System.nanoTime();
        }else{
            waveStartTimerDiff = (System.nanoTime() -waveStartTimer) / 1000000;
            if(waveStartTimerDiff > waveDelay){
                waveStart = true;
                waveStartTimer = 0;
                waveStartTimerDiff =0;
            }
        }
        // create Enemies
        if(waveStart && enemies.size() == 0){
            createNewEnemies();

        }
        // text update
        for(int i = 0; i < texts.size(); i++){
            boolean remove = texts.get(i).update();
            if(remove){
                texts.remove(i);
                i--;
            }
        }

        // player update
        player.update();
        // Bullet update
        for(int i = 0; i <bullets.size(); i++){
            boolean remove = bullets.get(i).update();
            if(remove){
                bullets.remove(i);
                i--;
            }
        }

        // enemy update
        for(int i = 0; i<enemies.size();i++){
            enemies.get(i).update();
        }
        // powerup update
        for(int i = 0; i< powerUps.size(); i++){
            boolean remove = powerUps.get(i).update();
            if(remove){
                powerUps.remove(i);
                i--;
            }
        }
        // Explosion update
        for(int i = 0; i < explosions.size(); i++){
            boolean remove = explosions.get(i).update();
            if(remove){
                explosions.remove(i);
                i--;
            }
        }

        //Bullet enemy collision
        for(int i = 0; i< bullets.size(); i++){
            Bullet b = bullets.get(i);
            double bx = b.getx();
            double by = b.gety();
            double br = b.getr();

            for(int j = 0; j<enemies.size(); j++){
                Enemy e = enemies.get(j);
                double ex = e.getx();
                double ey = e.gety();
                double er = e.getr();

                double dx = bx - ex;
                double dy = by - ey;
                double dist = Math.sqrt(dx * dx + dy*dy);

                if(dist < br + er){
                    e.hit();
                    bullets.remove(i);
                    i--;
                    break;
                }

            }

        }
        // Check dead enemies
        for(int i = 0; i < enemies.size(); i++){
            if(enemies.get(i).isDead()){
                Enemy e = enemies.get(i);

                // Chance for powerUp
                double rand = Math.random();
                if(rand < 0.001) powerUps.add(new PowerUp(1,e.getx(),e.gety()));
                else if(rand < 0.020) powerUps.add(new PowerUp(3, e.getx(),e.gety()));
                else if(rand < 0.120)powerUps.add(new PowerUp(2, e.getx(), e.gety()));
                else if(rand < 0.130) powerUps.add(new PowerUp(4, e.getx(), e.gety()));
                player.addScore(e.getRank() + e.getType());
                enemies.remove(i);
                i--;

                e.explode();
                explosions.add(new Explosion(e.getx(), e.gety(), e.getr(), e.getr()+30));
            }
        }
        // player enemy collision
        if(!player.isRecovering()){
            int px = player.getx();
            int py = player.gety();
            int pr = player.getr();
            for(int i = 0; i<enemies.size(); i++){

                Enemy e = enemies.get(i);
                double ex = e.getx();
                double ey = e.gety();
                double er = e.getr();

                double dx = px - ex;
                double dy = py - ey;
                double dist = Math.sqrt(dx * dx + dy * dy);

                if(dist < pr + er){
                    player.loseLife();
                }

            }
        }
        //Player powerup collision
        int px = player.getx();
        int py = player.gety();
        int pr = player.getr();
        for(int i = 0; i < powerUps.size(); i++){
            PowerUp pu = powerUps.get(i);
            double pux = pu.getx();
            double puy = pu.gety();
            double pur = pu.getr();

            double dx = px - pux;
            double dy = py - puy;
            double dist = Math.sqrt(dx* dx + dy*dy);
            // Collecting powerup
            if(dist < pr + pur){
                int type = pu.getType();
                if(type == 1){
                    player.gainLife();
                    texts.add(new Text(player.getx(), player.gety(), 2000,"Extra Life"));

                }
                if(type == 2){
                    player.increasePower(1);
                }
                if(type == 3){
                    player.increasePower(2);
                }
                if(type == 4){
                    slowDownTimer = System.nanoTime();
                    for(int j = 0; j<enemies.size(); j++){
                        enemies.get(j).setSlow(true);
                    }
                    texts.add(new Text(player.getx(), player.gety(), 2000,"Slow Down"));
                }
                powerUps.remove(i);
                i--;
            }

        }
        // slowdown update
        if(slowDownTimer !=0){
            slowDownTimerDiff = (System.nanoTime() - slowDownTimer) / 1000000;
            if(slowDownTimerDiff > slowDownLength){
                slowDownTimer = 0;
                for(int j = 0; j<enemies.size(); j++){
                    enemies.get(j).setSlow(false);
                }
            }
        }
    }

    private void gameRender(){ // draws to the ofscreen image, ie the buffered image
        // Background
        g.setColor(new Color(0, 100, 255));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.drawString("FPS: "+ averageFPS, 300, 30);

        // draw slowdown background
        if(slowDownTimer != 0){
            g.setColor(new Color(255, 255, 255, 64));
            g.fillRect(0, 0, WIDTH, HEIGHT);
        }

        // player
        player.draw(g);

        //Bullet render
        for(int i =0; i < bullets.size(); i++){
            bullets.get(i).draw(g);
        }

        // enemy render
        for(int i = 0; i<enemies.size();i++){
            enemies.get(i).draw(g);
        }

        // powerup render
        for(int i = 0; i < powerUps.size(); i ++){
            powerUps.get(i).draw(g);
        }
        // draw explosion
        for(int i = 0; i < explosions.size(); i++){
            explosions.get(i).draw(g);
        }
        // draw text
        for(int i = 0; i < texts.size(); i++){
            texts.get(i).draw(g);
        }

        //draw wave Number
        if(waveStartTimer != 0){
            g.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            String s = "- W A V E   " + waveNumber + "   -";
            int length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
            int alpha = (int) (255 * Math.sin(3.14 * waveStartTimerDiff / waveDelay));
            if(alpha > 255) alpha = 255;
            g.setColor(new Color(255, 255, 255, alpha));
            g.drawString(s,WIDTH/2 -length/2, HEIGHT/2);
        }
        // draw player lives
        for(int i = 0; i<player.getLives(); i++){
            g.setColor(Color.WHITE);
            g.fillOval(20 + (20 * i), 20, player.getr()*2,player.getr()*2);
            g.setStroke(new BasicStroke(3));
            g.setColor(Color.WHITE.darker());
            g.drawOval(20 + (20 * i), 20, player.getr()*2,player.getr()*2);
            g.setStroke(new BasicStroke(1));

        }
        // draw player power
        g.setColor(Color.YELLOW);
        g.fillRect(20, 40, player.getPower()*8, 8);
        g.setColor(Color.YELLOW.darker());
        g.setStroke(new BasicStroke(2));
        for(int i = 0; i < player.getRequiredPower(); i++){
            g.drawRect(20 + 8*1, 40 ,8 , 8);
        }
        g.setStroke(new BasicStroke(1));

        //draw player score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        g.drawString("Score: " + player.getScore(), 200, 30);

        // slow down meter
        if(slowDownTimer != 0){
            g.setColor(Color.WHITE);
            g.drawRect(20, 60, 100, 8);
            g.fillRect(20, 60, (int)(100- 100.0*slowDownTimerDiff/slowDownLength), 8);
        }
    }

    private void gameDraw(){ // this method draws to screen
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();

    }
    private void createNewEnemies(){
        enemies.clear();
        Enemy e;
        int enemyCount = 3;
        int maxType = 2;
        int maxRank = 2;
        int type = 2;
        int rank = 2;

        if(waveNumber < 10){
            enemyCount = waveNumber * 3;
            maxType = 2;
            maxRank = 2;
        } else if (waveNumber <20){
            enemyCount = waveNumber * 6;
            maxType = 3;
            maxRank = 3;
        } else if (waveNumber <30){
            enemyCount = waveNumber * 9;
            maxType = 3;
            maxRank = 4;
        } else {
            enemyCount = waveNumber * 18;
            maxType = 3;
            maxRank = 4;
        }

        boolean canSpawn = true;
        while(canSpawn){


            if(enemyCount >= 0 ){
                for (int i = 0; i < maxType; i++){
                    type = 1 + i;
                    rank = 1 + (int)(Math.random()*maxRank);
                    int cost = type * rank * 2;
                    if(enemyCount - cost >= -4 && cost != 0){
                        enemyCount = enemyCount - cost;
                        enemies.add(new Enemy(type, rank));
                    }
                }


            }else{
                canSpawn = false;
            }

        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_A){
            player.setLeft(true);
        }
        if(key == KeyEvent.VK_D){
            player.setRight(true);
        }
        if(key == KeyEvent.VK_S){
            player.setDown(true);
        }
        if(key == KeyEvent.VK_W){
            player.setup(true);
        }
        if(key == KeyEvent.VK_K){
            player.setFiring(true);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_A){
            player.setLeft(false);
        }
        if(key == KeyEvent.VK_D){
            player.setRight(false);
        }
        if(key == KeyEvent.VK_S){
            player.setDown(false);
        }
        if(key == KeyEvent.VK_W){
            player.setup(false);
        }
        if(key == KeyEvent.VK_K){
            player.setFiring(false);
        }

    }
}
