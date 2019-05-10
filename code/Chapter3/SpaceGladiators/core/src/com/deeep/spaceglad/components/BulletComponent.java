package com.deeep.spaceglad.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.badlogic.gdx.physics.bullet.linearmath.btMotionState;
import com.deeep.spaceglad.bullet.MotionState;

/**
 * Created by Andreas on 8/12/2015.
 */
public class BulletComponent extends Component{
    public btMotionState motionState;
    public btCollisionObject body;
    public btRigidBody.btRigidBodyConstructionInfo bodyInfo;
}
