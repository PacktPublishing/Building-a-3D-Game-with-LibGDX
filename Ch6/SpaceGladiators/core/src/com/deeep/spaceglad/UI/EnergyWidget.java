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
public class EnergyWidget extends Actor {
    private ProgressBar energyBar;
    private ProgressBar.ProgressBarStyle progressBarStyle;
    private Label label;

    public EnergyWidget() {
        progressBarStyle = new ProgressBar.ProgressBarStyle(
                Assets.skin.newDrawable("white", Color.OLIVE),
                Assets.skin.newDrawable("white", Color.ORANGE));
        progressBarStyle.knobBefore = progressBarStyle.knob;
        energyBar = new ProgressBar(0, 100, 1, false, progressBarStyle);
        label = new Label("Energy", Assets.skin);
        label.setAlignment(Align.center);
    }

    @Override
    public void act(float delta) {
        if (Settings.Paused) return;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        energyBar.draw(batch, parentAlpha);
        label.draw(batch, parentAlpha);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        energyBar.setPosition(x, y);
        label.setPosition(x, y);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        energyBar.setSize(width, height);
        progressBarStyle.background.setMinWidth(width);
        progressBarStyle.background.setMinHeight(height);
        progressBarStyle.knob.setMinWidth(energyBar.getValue());
        progressBarStyle.knob.setMinHeight(height);
        label.setSize(width, height);
    }

    public void setValue(float value) {
        energyBar.setValue(value);
    }
}