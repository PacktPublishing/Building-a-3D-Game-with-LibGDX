package com.deeep.spaceglad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by scanevaro on 18/08/2015.
 */
public class Settings {
    public static boolean Paused;
    public static boolean soundEnabled = true;
    public static int[] highscores = new int[]{1000, 800, 500, 300, 100};
    public final static String file = ".spaceglad";

    public static void load() {
        try {
            FileHandle filehandle = Gdx.files.external(file);
            String[] strings = filehandle.readString().split("\n");
            soundEnabled = Boolean.parseBoolean(strings[0]);
            for (int i = 0; i < 5; i++) highscores[i] = Integer.parseInt(strings[i + 1]);
        } catch (Throwable e) {
        }
    }

    public static void save() {
        try {
            FileHandle filehandle = Gdx.files.external(file);
            filehandle.writeString(Boolean.toString(soundEnabled) + "\n", false);
            for (int i = 0; i < 5; i++) filehandle.writeString(Integer.toString(highscores[i]) + "\n", true);
        } catch (Throwable e) {
        }
    }

    public static void addScore(int score) {
        for (int i = 0; i < 5; i++) {
            if (highscores[i] < score) {
                for (int j = 4; j > i; j--) highscores[j] = highscores[j - 1];
                highscores[i] = score;
            }
        }
    }
}