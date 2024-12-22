import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {

    private Clip clip;

    /**
     * Load a sound file.
     * @param soundFilePath The path to the sound file.
     */
    public void loadSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            if (!soundFile.exists()) {
                throw new IOException("Sound file not found: " + soundFilePath);
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.err.println("Error loading sound: " + e.getMessage());
        }
    }

    /**
     * Play the sound from the beginning.
     */
    public void play() {
        if (clip != null) {
            stop(); // Stop any sound currently playing
            clip.setFramePosition(0); // Rewind to the beginning
            clip.start();
        } else {
            System.err.println("No sound loaded to play.");
        }
    }

    /**
     * Loop the sound continuously.
     */
    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            System.err.println("No sound loaded to loop.");
        }
    }

    /**
     * Stop the currently playing sound.
     */
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * Close the sound player and release resources.
     */
    public void close() {
        if (clip != null) {
            stop();
            clip.close();
        }
    }

    /**
     * Check if the sound is currently playing.
     * @return True if the sound is playing, false otherwise.
     */
    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

    public static void main(String[] args) {
        // Example usage
        
    }
}
