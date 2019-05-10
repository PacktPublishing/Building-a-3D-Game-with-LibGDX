package com.deeep.spaceglad.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.utils.Align;
import com.deeep.spaceglad.Assets;
import com.deeep.spaceglad.Settings;

/**
 * Created by scanevaro on 04/08/2015.
 */
public class
        OxygenWidget extends Actor {
    private ProgressBar oxigenBar;
    private ProgressBar.ProgressBarStyle progressBarStyle;
    private Label label;
    private float addedValue;

    public OxygenWidget() {
        progressBarStyle = new ProgressBar.ProgressBarStyle(
                Assets.skin.newDrawable("white", Color.BLUE),
                Assets.skin.newDrawable("white", Color.CYAN));
        progressBarStyle.knobBefore = progressBarStyle.knob;
        oxigenBar = new ProgressBar(0, 100, 1, false, progressBarStyle);
        oxigenBar.setValue(100);
        label = new Label("Oxygen", Assets.skin);
        label.setAlignment(Align.center);
    }

    @Override
    public void act(float delta) {
        if (Settings.Paused) return;
        oxigenBar.act(delta);
        label.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        oxigenBar.draw(batch, parentAlpha);
        label.draw(batch, parentAlpha);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        oxigenBar.setPosition(x, y);
        label.setPosition(x, y);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        oxigenBar.setSize(width, height);
        progressBarStyle.background.setMinWidth(width);
        progressBarStyle.background.setMinHeight(height);
        progressBarStyle.knob.setMinWidth(oxigenBar.getValue());
        progressBarStyle.knob.setMinHeight(height);
        label.setSize(width, height);
    }

    public void setValue(float value) {
        addedValue += value;
        if (addedValue > 1) {
            oxigenBar.setValue(value);
            addedValue = 0;
        }
    }
}