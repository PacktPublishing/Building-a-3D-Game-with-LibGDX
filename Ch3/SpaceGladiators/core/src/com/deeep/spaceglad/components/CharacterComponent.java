package com.deeep.spaceglad.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btConvexShape;
import com.badlogic.gdx.physics.bullet.collision.btPairCachingGhostObject;
import com.badlogic.gdx.physics.bullet.dynamics.btKinematicCharacterController;

/**
 * Created by Elamre on 9/26/2015.
 */
public class CharacterComponent extends Component {
    public btPairCachingGhostObject ghostObject;
    public btConvexShape ghostShape;
    public btKinematicCharacterController characterController;

    public Vector3 characterDirection = new Vector3();
    public Vector3 walkDirection = new Vector3();
}
