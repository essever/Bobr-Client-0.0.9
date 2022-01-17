//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.util;

import java.io.*;
import javax.sound.sampled.*;

public class Sound implements AutoCloseable
{
    private boolean released;
    private AudioInputStream stream;
    private Clip clip;
    private FloatControl volumeControl;
    private boolean playing;
    
    public Sound(final File f) {
        this.released = false;
        this.stream = null;
        this.clip = null;
        this.volumeControl = null;
        this.playing = false;
        try {
            this.stream = AudioSystem.getAudioInputStream(f);
            (this.clip = AudioSystem.getClip()).open(this.stream);
            this.clip.addLineListener(new Listener());
            this.volumeControl = (FloatControl)this.clip.getControl(FloatControl.Type.MASTER_GAIN);
            this.released = true;
        }
        catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex2) {
            final Exception ex;
            final Exception exc = ex;
            exc.printStackTrace();
            this.released = false;
            this.close();
        }
    }
    
    public boolean isReleased() {
        return this.released;
    }
    
    public boolean isPlaying() {
        return this.playing;
    }
    
    public void play(final boolean breakOld) {
        if (this.released) {
            if (breakOld) {
                this.clip.stop();
                this.clip.setFramePosition(0);
                this.clip.start();
                this.playing = true;
            }
            else if (!this.isPlaying()) {
                this.clip.setFramePosition(0);
                this.clip.start();
                this.playing = true;
            }
        }
    }
    
    public void play() {
        this.play(true);
    }
    
    public void stop() {
        if (this.playing) {
            this.clip.stop();
        }
    }
    
    @Override
    public void close() {
        if (this.clip != null) {
            this.clip.close();
        }
        if (this.stream != null) {
            try {
                this.stream.close();
            }
            catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }
    
    public void setVolume(float x) {
        if (x < 0.0f) {
            x = 0.0f;
        }
        if (x > 1.0f) {
            x = 1.0f;
        }
        final float min = this.volumeControl.getMinimum();
        final float max = this.volumeControl.getMaximum();
        this.volumeControl.setValue((max - min) * x + min);
    }
    
    public float getVolume() {
        final float v = this.volumeControl.getValue();
        final float min = this.volumeControl.getMinimum();
        final float max = this.volumeControl.getMaximum();
        return (v - min) / (max - min);
    }
    
    public void join() {
        if (!this.released) {
            return;
        }
        synchronized (this.clip) {
            try {
                while (this.playing) {
                    this.clip.wait();
                }
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public static Sound playSound(final String path) {
        final File f = new File(path);
        final Sound snd = new Sound(f);
        snd.play();
        return snd;
    }
    
    private class Listener implements LineListener
    {
        @Override
        public void update(final LineEvent ev) {
            if (ev.getType() == LineEvent.Type.STOP) {
                Sound.this.playing = false;
                synchronized (Sound.this.clip) {
                    Sound.this.clip.notify();
                }
            }
        }
    }
}
