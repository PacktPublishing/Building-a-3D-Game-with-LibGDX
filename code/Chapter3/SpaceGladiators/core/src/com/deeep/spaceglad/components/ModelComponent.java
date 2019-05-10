package com.deeep.spaceglad.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;

/**
 * Created by Andreas on 8/4/2015.
 */
public class ModelComponent extends Component {
    public Model model;
    public ModelInstance instance;

    public ModelComponent(Model model, float x, float y, float z) {
        this.model = model;
        this.instance = new ModelInstance(model, new Matrix4().setToTranslation(x, y, z));
    }
}