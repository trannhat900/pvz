import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class GameWindow extends JFrame {

    enum PlantType {
        None,
        Sunflower,
        Peashooter,
        FreezePeashooter
    }

    static SoundPlayer soundPlayerMenu;
    static SoundPlayer soundPlayerGame;
// Static sound player for game music

    static GameWindow gw;

    public GameWindow() {
        setSize(1012, 785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel sun = new JLabel("SUN");
        sun.setLocation(37, 80);
        sun.setSize(60, 20);

        GamePanel gp = new GamePanel(sun);
        gp.setLocation(0, 0);
        getLayeredPane().add(gp, new Integer(0));

        setResizable(false);
        setVisible(true);

        // Stop menu music if playing
        if (soundPlayerMenu != null) {
            soundPlayerMenu.stop();
        }

        // Initialize and play game music
        soundPlayerGame = new SoundPlayer();
        soundPlayerGame.loadSound("sound/main-sound.wav");
        soundPlayerGame.loop(); // Loop the music

        PlantCard sunflower = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_sunflower.png")).getImage());
        sunflower.setLocation(110, 8);
        sunflower.setAction((ActionEvent e) -> {
            gp.setActivePlantingBrush(PlantType.Sunflower);
        });
        getLayeredPane().add(sunflower, new Integer(3));

        PlantCard peashooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_peashooter.png")).getImage());
        peashooter.setLocation(175, 8);
        peashooter.setAction((ActionEvent e) -> {
            gp.setActivePlantingBrush(PlantType.Peashooter);
        });
        getLayeredPane().add(peashooter, new Integer(3));

        PlantCard freezepeashooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_freezepeashooter.png")).getImage());
        freezepeashooter.setLocation(240, 8);
        freezepeashooter.setAction((ActionEvent e) -> {
            gp.setActivePlantingBrush(PlantType.FreezePeashooter);
        });
        getLayeredPane().add(freezepeashooter, new Integer(3));

        getLayeredPane().add(sun, new Integer(2));
        setResizable(false);
        setVisible(true);
    }

    public GameWindow(boolean isMenu) {
        if (isMenu) {
            Menu menu = new Menu();
            menu.setLocation(0, 0);
            setSize(1012, 785);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            getLayeredPane().add(menu, new Integer(0));
            menu.repaint();
            setResizable(false);
            setVisible(true);
        }
    }

    public static void begin() {
        gw.dispose();
        gw = new GameWindow();
    }

    public static void main(String[] args) {
        // Initialize menu music
        soundPlayerMenu = new SoundPlayer();
        soundPlayerMenu.loadSound("src/sound/background-music.wav");
        soundPlayerMenu.loop(); // Loop the music

        // Launch the menu
        gw = new GameWindow(true);
    }
}
