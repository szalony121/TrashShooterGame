import javax.sound.sampled.*;
import java.io.File;

public class SoundPlayer {
    Clip normalClip;
    Clip bossClip;

    public SoundPlayer(String normalPath, String bossPath) {
        try {
            normalClip = AudioSystem.getClip();
            bossClip = AudioSystem.getClip();

            AudioInputStream normalStream = AudioSystem.getAudioInputStream(new File(normalPath));
            AudioInputStream bossStream = AudioSystem.getAudioInputStream(new File(bossPath));

            normalClip.open(normalStream);
            bossClip.open(bossStream);

            normalClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToBossMusic() {
        normalClip.stop();
        bossClip.setFramePosition(0);
        bossClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void switchToNormalMusic() {
        bossClip.stop();
        normalClip.setFramePosition(0);
        normalClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}