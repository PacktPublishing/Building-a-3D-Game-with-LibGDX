package com.deeep.spaceglad.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.deeep.spaceglad.Logger;
import com.deeep.spaceglad.Settings;

/**
 * Created by Andreas on 10/21/2015.
 */

public class CrosshairWidget extends Actor {

    private Image crosshairDot, /*crosshairOuterRing,*/ crosshairInnerRing;
    //private float outerRotationSpeed, innerRotationSpeed;

    public CrosshairWidget() {
        crosshairDot = new Image(new Texture(Gdx.files.internal("crosshair/crossHairPoint.png")));
        crosshairInnerRing = new Image(new Texture(Gdx.files.internal("crosshair/crossHairInnerRing.png")));
        //crosshairOuterRing = new Image(new Texture(Gdx.files.internal("crosshair/crossHairOuterRing.png")));
        //outerRotationSpeed = 1F;
        //innerRotationSpeed = -1F;
    }

    @Override
    public void act(float delta) {
        if (Settings.Paused) return;
        //TODO: Add this in a later chapter
        //crosshairInnerRing.rotateBy(innerRotationSpeed);
        //crosshairOuterRing.rotateBy(outerRotationSpeed);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (Settings.Paused) return;
        crosshairDot.draw(batch, parentAlpha);
        crosshairInnerRing.draw(batch, parentAlpha);
        //TODO: Make this smaller
        //crosshairOuterRing.draw(batch, parentAlpha);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        crosshairDot.setPosition(x - 16, y - 16);
        crosshairInnerRing.setPosition(x - 16, y - 16);
        //crosshairOuterRing.setPosition(x - 16, y - 16);
        crosshairInnerRing.setOrigin(crosshairInnerRing.getWidth() / 2, crosshairInnerRing.getHeight() / 2);
        //crosshairOuterRing.setOrigin(crosshairOuterRing.getWidth() / 4, crosshairOuterRing.getHeight() / 4);
        Logger.log(Logger.ANDREAS, Logger.INFO, "Setting origin to " + x + ", " + y);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        crosshairDot.setSize(width * 2, height * 2);
        crosshairInnerRing.setSize(width * 2, height * 2);
        //crosshairOuterRing.setSize(width * 2, height * 2);
    }

}
