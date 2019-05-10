package com.deeep.spaceglad.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Andreas on 8/5/2015.
 */
public class EnemyComponent extends Component {

    public enum STATE {
        IDLE,
        FLEEING,
        HUNTING
    }

    public STATE state = STATE.IDLE;

    public EnemyComponent(STATE state){
        this.state = state;
    }
}
