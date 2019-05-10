package com.deeep.spaceglad.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.badlogic.gdx.physics.bullet.dynamics.btConstraintSolver;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver;
import com.deeep.spaceglad.components.*;

/**
 * Created by scanevaro on 22/09/2015.
 */
public class BulletSystem extends EntitySystem implements EntityListener {
    public final btCollisionConfiguration collisionConfiguration;
    public final btCollisionDispatcher dispatcher;
    public final btBroadphaseInterface broadphase;
    public final btConstraintSolver solver;
    public final btDiscreteDynamicsWorld collisionWorld;
    private btGhostPairCallback ghostPairCallback;
    public int maxSubSteps = 5;
    public float fixedTimeStep = 1f / 60f;

    public class MyContactListener extends ContactListener {
        @Override
        public void onContactStarted(btCollisionObject colObj0, btCollisionObject colObj1) {
            if (colObj0.userData instanceof Entity && colObj0.userData instanceof Entity) {
                Entity entity0 = (Entity) colObj0.userData;
                Entity entity1 = (Entity) colObj1.userData;
                if (entity0.getComponent(CharacterComponent.class) != null && entity1.getComponent(CharacterComponent.class) != null) {
                    if (entity0.getComponent(EnemyComponent.class) != null) {
                        if (entity0.getComponent(StatusComponent.class).alive)
                            entity1.getComponent(PlayerComponent.class).health -= 10;
                        entity0.getComponent(StatusComponent.class).alive = false;
                    } else {
                        if (entity1.getComponent(StatusComponent.class).alive)
                            entity0.getComponent(PlayerComponent.class).health -= 10;
                        entity1.getComponent(StatusComponent.class).alive = false;
                    }
                }
            }
        }
    }

    @Override
    public void addedToEngine(Engine engine) {
        engine.addEntityListener(Family.all(BulletComponent.class).get(), this);
    }

    public BulletSystem() {
        MyContactListener myContactListener = new MyContactListener();
        myContactListener.enable();
        collisionConfiguration = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfiguration);
        broadphase = new btAxisSweep3(new Vector3(-1000, -1000, -1000), new Vector3(1000, 1000, 1000));
        solver = new btSequentialImpulseConstraintSolver();
        collisionWorld = new btDiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfiguration);
        ghostPairCallback = new btGhostPairCallback();
        broadphase.getOverlappingPairCache().setInternalGhostPairCallback(ghostPairCallback);
        this.collisionWorld.setGravity(new Vector3(0, -0.5f, 0));
    }

    @Override
    public void update(float deltaTime) {
        collisionWorld.stepSimulation(deltaTime);
    }

    public void dispose() {
        collisionWorld.dispose();
        if (solver != null) solver.dispose();
        if (broadphase != null) broadphase.dispose();
        if (dispatcher != null) dispatcher.dispose();
        if (collisionConfiguration != null) collisionConfiguration.dispose();
        ghostPairCallback.dispose();
    }

    @Override
    public void entityAdded(Entity entity) {
        BulletComponent bulletComponent = entity.getComponent(BulletComponent.class);
        if (bulletComponent.body != null) {
            collisionWorld.addRigidBody((btRigidBody) bulletComponent.body);
        }
    }

    public void removeBody(Entity entity) {
        BulletComponent comp = entity.getComponent(BulletComponent.class);
        if (comp != null)
            collisionWorld.removeCollisionObject(comp.body);
        CharacterComponent character = entity.getComponent(CharacterComponent.class);
        if (character != null) {
            collisionWorld.removeAction(character.characterController);
            collisionWorld.removeCollisionObject(character.ghostObject);
        }
    }

    @Override
    public void entityRemoved(Entity entity) {
    }
}