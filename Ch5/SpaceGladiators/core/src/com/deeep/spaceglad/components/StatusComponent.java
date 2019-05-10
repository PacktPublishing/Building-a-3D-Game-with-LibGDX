package com.deeep.spaceglad.components;

import com.badlogic.ashley.core.Component;
import com.deeep.spaceglad.managers.EnemyAnimations;

/**
 * Created by Andreas on 8/10/2015.
 */
public class StatusComponent extends Component {
    private AnimationComponent animationComponent;
    public boolean alive, running, attacking;
    public float aliveStateTime;

    public StatusComponent(AnimationComponent animationComponent) {
        this.animationComponent = animationComponent;
        alive = true;
        running = true;
    }

    public void update(float delta) {
        if (!alive) aliveStateTime += delta;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
        playDeathAnim2();
    }

    private void playDeathAnim2() {
        animationComponent.animate(EnemyAnimations.id, EnemyAnimations.offsetDeath2, EnemyAnimations.durationDeath2, 1, 3);
    }
}