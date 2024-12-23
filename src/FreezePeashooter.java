import javax.swing.*;
import java.awt.event.ActionEvent;


public class FreezePeashooter extends Plant {

    private Timer shootTimer;


    public FreezePeashooter(GamePanel parent, int x, int y) {
        super(parent, x, y,100);
        shootTimer = new Timer(1000, (ActionEvent e) -> {
            //System.out.println("SHOOT");
            if (getGp().getLaneZombies().get(y).size() > 0) {
                getGp().getLanePeas().get(y).add(new FreezePea(getGp(), y, 103 + this.getX() * 100));
            }
        });
        shootTimer.start();
    }

    @Override
    public void stop() {
        shootTimer.stop();
    }

}