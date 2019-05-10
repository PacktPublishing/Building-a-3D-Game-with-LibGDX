package com.deeep.spaceglad.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffectLoader;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSystem;
import com.badlogic.gdx.graphics.g3d.particles.emitters.RegularEmitter;
import com.deeep.spaceglad.Assets;

/**
 * Created by Sebastian on 5/6/2016.
 */
public class DieParticleComponent extends Component {
    public ParticleEffect originalEffect;
    public boolean used = false;

    public DieParticleComponent(ParticleSystem particleSystem) {
        ParticleEffectLoader.ParticleEffectLoadParameter loadParam = new ParticleEffectLoader.ParticleEffectLoadParameter(particleSystem.getBatches());
        if (!Assets.assetManager.isLoaded("data/dieparticle.pfx")) {
            Assets.assetManager.load("data/dieparticle.pfx", ParticleEffect.class, loadParam);
            Assets.assetManager.finishLoading();
        }
        originalEffect = Assets.assetManager.get("data/dieparticle.pfx");
    }
}