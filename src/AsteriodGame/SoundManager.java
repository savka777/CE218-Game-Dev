package AsteriodGame;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class SoundManager {
    private static final String filePath = "src/GameSounds/";

    private static Clip playerSootClip;
    private static Clip PlayerDeadClip;
    private static Clip asteroidExplosionClip;
    private static Clip mainMusicClip;

   static {
       playerSootClip = loadClipSound("ShootingSound");
       PlayerDeadClip = loadClipSound("PlayerDeadSound");
       asteroidExplosionClip = loadClipSound("AsteriodExplosition");
       mainMusicClip = loadClipSound("MainGameMusic");
   }

    private static Clip loadClipSound(String file) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath + file + ".wav"));
            clip.open(audioInputStream);
            if(file.equalsIgnoreCase("ShootingSound")
            || file.equalsIgnoreCase("PlayerDeadSound")
            || file.equalsIgnoreCase("AsteriodExplosition")){
                setVolume(clip,10.f);
            }

            if(file.equalsIgnoreCase("MainGameMusic")){
                setVolume(clip,-10.0f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clip;
    }

    public static void setVolume(Clip clip, float decibels) {
        if (clip == null) return;

        try {
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // The valid range of decibels depends on your system;
            // typically from around -80.0f (extremely quiet) to +6.0f or so (louder).
            // For example, a value of 0.0f is "no change," +5.0f is a bit louder, -10.0f is quieter.
            gainControl.setValue(decibels);
        } catch (IllegalArgumentException e) {
            // If MASTER_GAIN is not supported, this exception will be thrown.
            System.err.println("MASTER_GAIN not supported by this clip.");
        }
    }


    private static void playClipSound(Clip clip) {
        if (clip == null) return;
        clip.setFramePosition(0);
        clip.start();
    }

    private static void loopClipSound(Clip clip) {
        if (clip == null) return;
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private static void stopClipSound(Clip clip) {
        if (clip == null) return;
        clip.stop();
        clip.setFramePosition(0);
    }

    public static void playShootingSound() {
        playClipSound(playerSootClip);
    }

    public static void playPlayerDeadSound() {
        playClipSound(PlayerDeadClip);
    }

    public static void playAsteroidExplosionSound() {
        playClipSound(asteroidExplosionClip);
    }

    public static void startMainMusic() {
        loopClipSound(mainMusicClip);
    }

    public static void stopMainMusic() {
        stopClipSound(mainMusicClip);
    }
}
