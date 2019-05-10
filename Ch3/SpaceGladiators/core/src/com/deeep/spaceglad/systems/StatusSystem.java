package com.deeep.spaceglad.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.deeep.spaceglad.GameWorld;
import com.deeep.spaceglad.components.StatusComponent;

import java.util.Iterator;

/**
 * Created by Elamre on 10/13/2015.
 */
public class StatusSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private GameWorld gameWorld;

    public StatusSystem(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(StatusComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        Iterator iterator = entities.iterator();
        while(iterator.hasNext()){
            Entity entity = (Entity) iterator.next();
            if(!entity.getComponent(StatusComponent.class).alive){
                gameWorld.remove(entity);
            }
        }
    }
}
