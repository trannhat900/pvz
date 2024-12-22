import java.awt.Rectangle;

import javax.swing.*;


public class Zombie {

    private int health;
    private double speed = 0.75;
    private int dmg;

    private GamePanel gp;

    private int posX = 1000;
    private int myLane;
    private boolean isMoving = true;
    private boolean isAttacking = false;
    private long lastAttackTime = 0; // Track last attack time
   

    public Zombie(GamePanel parent, int lane) {
    	
        this.gp = parent;
        myLane = lane;
    }
    

    public void advance() {
        if (isMoving) {
            boolean isCollides = false;
            Collider collided = null;
            for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {
                if (gp.getColliders()[i].assignedPlant != null && gp.getColliders()[i].isInsideCollider(posX)) {
                    isCollides = true;
                    collided = gp.getColliders()[i];
                }
            }
            if (!isCollides) {
            	isAttacking = false;
                if (slowInt > 0) {
                    if (slowInt % 2 == 0) {
                        posX-=speed;
                    }
                    slowInt--;
                } else {
                    posX -=speed;
                    
                }
            } 
            else {
            	// Zombie eat plant each 1.5s
            	isAttacking = true;
            	long currentTime = System.currentTimeMillis();
                if (currentTime - lastAttackTime >= 1500) {
                    Plant plant = collided.assignedPlant;
                    plant.setHealth(plant.getHealth() - dmg);
                   
                    lastAttackTime = currentTime;
                    if (plant.getHealth() <= 0) {
                        collided.removePlant();
                        isAttacking = true;
                    }
                   
                }
            }
            if (posX < 0) {
                isMoving = false;
                JOptionPane.showMessageDialog(gp, "ZOMBIES ATE YOUR BRAIN !" + '\n' + "Starting the level again");
                GameWindow.gw.dispose();
                GameWindow.gw = new GameWindow();
            }
        }
    }
   

    int slowInt = 0;

    public void slow() {
        slowInt = 1000;
    }

    public static Zombie getZombie(String type, GamePanel parent, int lane) {
        Zombie z = new Zombie(parent, lane);
        switch (type) {
            case "NormalZombie":
                z = new NormalZombie(parent, lane);
                break;
            case "ConeHeadZombie":
                z = new ConeHeadZombie(parent, lane);
                break;
           
        }
        return z;
    }
   
    public int getDmg() {
        return dmg;
    }
    public void setDmg(int dmg) {
    	this.dmg = dmg;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
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

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public int getSlowInt() {
        return slowInt;
    }

    public void setSlowInt(int slowInt) {
        this.slowInt = slowInt;
    }

	public void stop() {
		this.isMoving = false;
		
	}


	public boolean isAttacking() {
		return isAttacking;
	}


	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}
}
