package changes;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import static view.menu.color.Colors.colorPrintln;

public class Sound {
    public static void backgroundMusic() {
        String filename = "EndOfTime.wav";
        AudioInputStream audioInputStream = null;
        try { audioInputStream = AudioSystem.getAudioInputStream(new File(filename)); }
        catch (UnsupportedAudioFileException ignored) { }
        catch (IOException e) { colorPrintln("Something went wrong."); }
        Clip clip = null;
        try { clip = AudioSystem.getClip(); }
        catch (LineUnavailableException e) { colorPrintln("Something went wrong."); }
        try { Objects.requireNonNull(clip).open(audioInputStream); }
        catch (LineUnavailableException e) { colorPrintln("Something went wrong."); }
        catch (IOException e) { colorPrintln("Something went wrong"); }
        Objects.requireNonNull(clip).start();
    }
}
