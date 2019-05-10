package com.deeep.spaceglad.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Elmar on 8-8-2015.
 */
public class PlayerComponent extends Component {

    //    public float energy;
//    public float oxygen;
    public float health;
    public static int score;

    public PlayerComponent() {
//        energy = 100;
//        oxygen = 100;
        health = 100;
        score = 0;
    }
}