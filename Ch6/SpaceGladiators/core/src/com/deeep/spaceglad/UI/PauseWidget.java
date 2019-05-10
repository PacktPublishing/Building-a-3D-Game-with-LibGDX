package com.deeep.spaceglad.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.deeep.spaceglad.Assets;
import com.deeep.spaceglad.Core;
import com.deeep.spaceglad.Settings;
import com.deeep.spaceglad.screens.GameScreen;

/**
 * Created by scanevaro on 04/08/2015.
 */
public class PauseWidget extends Actor {
    private Core game;
    private Window window;
    private TextButton closeDialog, restartButton, quitButton;
    private Stage stage;

    public PauseWidget(Core game, Stage stage) {
        this.game = game;
        this.stage = stage;
        setWidgets();
        configureWidgets();
        setListeners();
    }

    private void setWidgets() {
        window = new Window("Pause", Assets.skin);
        closeDialog = new TextButton("X", Assets.skin);
        restartButton = new TextButton("Restart", Assets.skin);
        quitButton = new TextButton("Quit", Assets.skin);
    }

    private void configureWidgets() {
        window.getTitleTable().add(closeDialog).height(window.getPadTop());
        window.add(restartButton);
        window.add(quitButton);
    }

    private void setListeners() {
        super.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    handleUpdates();
                    return true;
                }
                return false;
            }
        });
        closeDialog.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                handleUpdates();
            }
        });
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    private void handleUpdates() {
        if (window.getStage() == null) {
            stage.addActor(window);
            Gdx.input.setCursorCatched(false);
            Settings.Paused = true;
        } else {
            window.remove();
            Gdx.input.setCursorCatched(true);
            Settings.Paused = false;
        }
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        window.setPosition(Core.VIRTUAL_WIDTH / 2 - window.getWidth() / 2, Core.VIRTUAL_HEIGHT / 2 - window.getHeight() / 2);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        window.setSize(width * 2, height * 2);
    }
}