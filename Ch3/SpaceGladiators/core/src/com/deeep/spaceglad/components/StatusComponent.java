package com.deeep.spaceglad.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Andreas on 8/10/2015.
 */
public class StatusComponent extends Component{
    public boolean alive;
    public StatusComponent(){
        alive = true;
    }
}
