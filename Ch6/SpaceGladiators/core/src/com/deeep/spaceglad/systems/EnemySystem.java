package com.deeep.spaceglad.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.emitters.RegularEmitter;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.deeep.spaceglad.GameWorld;
import com.deeep.spaceglad.components.*;
import com.deeep.spaceglad.managers.EntityFactory;

import java.util.Random;

/**
 * Created by Andreas on 8/5/2015.
 */

public class EnemySystem extends EntitySystem implements EntityListener {
    private ImmutableArray<Entity> entities;
    private Entity player;
    private Quaternion quat = new Quaternion();
    private Engine engine;
    private GameWorld gameWorld;
    private Vector3 playerPosition = new Vector3();
    private Vector3 enemyPosition = new Vector3();
    private Matrix4 ghost = new Matrix4();
    private Vector3 translation = new Vector3();
    private Random random = new Random();

    private float[] xSpawns = {12, -12, 112, -112};
    private float[] zSpawns = {-112, 112, -12, 12};

    ComponentMapper<CharacterComponent> cm = ComponentMapper.getFor(CharacterComponent.class);
    ComponentMapper<StatusComponent> sm = ComponentMapper.getFor(StatusComponent.class);

    public EnemySystem(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void addedToEngine(Engine e) {
        entities = e.getEntitiesFor(Family.all(EnemyComponent.class, CharacterComponent.class, StatusComponent.class).get());
        e.addEntityListener(Family.one(PlayerComponent.class).get(), this);
        this.engine = e;
    }

    public void update(float delta) {
        if (entities.size() < 1) spawnEnemy(getRandomSpawnIndex());

        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            ModelComponent mod = e.getComponent(ModelComponent.class);
            ModelComponent playerModel = player.getComponent(ModelComponent.class);

            if (!e.getComponent(StatusComponent.class).alive)
                mod.update(delta);

            if (!e.getComponent(StatusComponent.class).alive &&
                    !e.getComponent(DieParticleComponent.class).used) {
                e.getComponent(DieParticleComponent.class).used = true;
                ParticleEffect effect = e.getComponent(DieParticleComponent.class).originalEffect.copy();
                ((RegularEmitter) effect.getControllers().first().emitter).setEmissionMode(RegularEmitter.EmissionMode.EnabledUntilCycleEnd);
                effect.setTransform(e.getComponent(ModelComponent.class).instance.transform);
                effect.scale(3.25f, 1, 1.5f);
                effect.init();
                effect.start();
                RenderSystem.particleSystem.add(effect);
            }

            if (!sm.get(e).alive) return;
            playerModel.instance.transform.getTranslation(playerPosition);
            mod.instance.transform.getTranslation(enemyPosition);

            float dX = playerPosition.x - enemyPosition.x;
            float dZ = playerPosition.z - enemyPosition.z;

            float theta = (float) (Math.atan2(dX, dZ));

            //Calculate the transforms
            Quaternion rot = quat.setFromAxis(0, 1, 0, (float) Math.toDegrees(theta) + 90);

            cm.get(e).characterDirection.set(-1, 0, 0).rot(mod.instance.transform);
            cm.get(e).walkDirection.set(0, 0, 0);
            cm.get(e).walkDirection.add(cm.get(e).characterDirection);
            cm.get(e).walkDirection.scl(10f * delta);   //TODO make this change on difficulty
            cm.get(e).characterController.setWalkDirection(cm.get(e).walkDirection);

            ghost.set(0, 0, 0, 0);
            translation.set(0, 0, 0);
            cm.get(e).ghostObject.getWorldTransform(ghost);
            ghost.getTranslation(translation);

            mod.instance.transform.set(translation.x, translation.y, translation.z, rot.x, rot.y, rot.z, rot.w);
        }
    }

    private void spawnEnemy(int randomSpawnIndex) {
        engine.addEntity(EntityFactory.createEnemy(gameWorld.bulletSystem, xSpawns[randomSpawnIndex], 33, zSpawns[randomSpawnIndex]));
    }

    @Override
    public void entityAdded(Entity entity) {
        player = entity;
    }

    @Override
    public void entityRemoved(Entity entity) {
    }

    public int getRandomSpawnIndex() {
        return random.nextInt(xSpawns.length);
    }
}
