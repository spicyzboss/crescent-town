package main;

import javax.sound.sampled.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class SoundManager {
    private static Clip audioPlayer;
    private static HashMap<String, File> sounds;

    public SoundManager() {
        sounds = new HashMap<>();
        this.loadSounds();
    }

    private void loadSounds() {
        this.addSound("select");
        this.addSound("change");
        this.addSound("cancel");
        this.addSound("beach_house");
    }

    public void addSound(String name) {
        File soundFile = new File("res/sound/" + name + ".wav");
        if (soundFile.exists()) {
            sounds.put(name, soundFile);
            System.out.println("\u001B[32m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - LOADED: sound " + name + "\u001B[0m");
        } else {
            System.out.println("\u001B[31m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - ERROR: sound " + name + " not found" + "\u001B[0m");
        }
    }

    public void setSound(String name) {
        try {
            audioPlayer = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(this.getSound(name));
            audioPlayer.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playSound() {
        audioPlayer.start();
    }

    public void loopSound() {
        audioPlayer.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopSound() {
        audioPlayer.stop();
    }

    public File getSound(String soundName) {
        return sounds.get(soundName);
    }
}
