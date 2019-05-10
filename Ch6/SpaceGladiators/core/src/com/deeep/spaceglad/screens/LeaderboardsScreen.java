package com.deeep.spaceglad.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.deeep.spaceglad.Assets;
import com.deeep.spaceglad.Core;
import com.deeep.spaceglad.Settings;

/**
 * Created by scanevaro on 06/08/2015.
 */
public class LeaderboardsScreen implements Screen {
    Core game;
    Stage stage;
    Image backgroundImage;
    TextButton backButton;
    Label label[];
    boolean loaded;

    public LeaderboardsScreen(Core game) {
        this.game = game;
        stage = new Stage(new FitViewport(Core.VIRTUAL_WIDTH, Core.VIRTUAL_HEIGHT));
        setWidgets();
        configureWidgers();
        setListeners();
        Gdx.input.setInputProcessor(stage);
    }

    private void setWidgets() {
        backgroundImage = new Image(new Texture(Gdx.files.internal("data/backgroundMN.png")));
        backButton = new TextButton("Back", Assets.skin);
        label = new Label[5];
        label[0] = new Label("Loading scores from online leaderborads...", Assets.skin);
        Settings.load(label);
//        for (int i = 0; i < label.length; i++) label[i] = new Label(i + 1 + ") " + Settings.highscores[i], Assets.skin);
    }

    private void configureWidgers() {
        backgroundImage.setSize(Core.VIRTUAL_WIDTH, Core.VIRTUAL_HEIGHT);
        backButton.setSize(128, 64);
        backButton.setPosition(Core.VIRTUAL_WIDTH - backButton.getWidth() - 5, 5);

        stage.addActor(backgroundImage);
        stage.addActor(backButton);

        label[0].setFontScale(3);
        label[0].setPosition(15, Core.VIRTUAL_HEIGHT - label[0].getHeight() - 25);
        stage.addActor(label[0]);
    }

    private void setListeners() {
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        /** Updates */
        stage.act(delta);
        updateLeaderboard();
        /** Draw */
        stage.draw();
    }

    public void updateLeaderboard() {
        if (label[1] != null && loaded == false) {
            loaded = true;
            int y = 0;
            for (int i = 0; i < label.length; i++) {
                label[i].setFontScale(3);
                label[i].setPosition(15, Core.VIRTUAL_HEIGHT - label[i].getHeight() - 25 - y);
                y += 96;
                stage.addActor(label[i]);
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void show() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }
}