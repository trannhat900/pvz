import java.awt.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class Pea {

    private int posX;
    protected GamePanel gp;
    private int myLane;

    public Pea(GamePanel parent, int lane, int startX) {
        this.gp = parent;
        this.myLane = lane;
        posX = startX;
    }
    
    public void advance() {
    	 Rectangle pRect = new Rectangle(getPosX(), 130 + getMyLane() * 120, 28, 28);
         for (int i = 0; i < gp.getLaneZombies().get(getMyLane()).size(); i++) {
             Zombie z = gp.getLaneZombies().get(getMyLane()).get(i);
             Rectangle zRect = new Rectangle(z.getPosX(), 109 + getMyLane() * 120, 400, 120);
             if (pRect.intersects(zRect)) {
                 z.setHealth(z.getHealth() - 20);
                 
                 boolean exit = false;
                 if (z.getHealth() < 0) {
                     System.out.println("ZOMBIE DIE");
                     GamePanel.setProgress(10);
                     gp.getLaneZombies().get(getMyLane()).remove(i);
                     exit = true;
                 }
                 gp.getLanePeas().get(getMyLane()).remove(this);
                 if (exit) break;
             }
         
        }
      
        posX += 15;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getMyLane() {
        return myLane;
    }

    public void setMyLane(int myLane) {
        this.myLane = myLane;
    }
}
