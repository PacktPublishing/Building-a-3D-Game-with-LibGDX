package com.deeep.spaceglad.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.deeep.spaceglad.Core;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int) Core.VIRTUAL_WIDTH;
        config.height = (int) Core.VIRTUAL_HEIGHT;
        new LwjglApplication(new Core(), config);
    }
}
