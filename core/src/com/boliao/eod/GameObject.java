package com.boliao.eod;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.boliao.eod.components.Component;
import com.boliao.eod.components.render.Renderable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrboliao on 17/1/17.
 */

public class GameObject {
    protected String name;
    protected boolean isDestroyed = false;
    protected List<Component> components = new ArrayList<Component>();

    public GameObject (String name) {
        this.name = name;
    }

    /**
     * I know there are very little components in an object, so no need for hash table.
     * @param name  name of component
     * @return the component object, or null if does not exist
     */
    public Component getComponent(String name) {
        for (Component c: components) {
            if (c.getName() == name) {
                return c;
            }
        }
        return null;
    }

    public Renderable getRenderable() {
        for (Component c: components) {
            if (c instanceof Renderable) {
                return (Renderable)c;
            }
        }
        return null;
    }

    /**
     * This can only be called after added all components
     */
    public void init() {
        //todo: throw exception if no components
//        if (components.isEmpty()) {
//            throw new Exception("Components need to be added before calling GameObject::init().");
//        }

        for (Component c: components) {
            c.init(this);
        }
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void update(float dt) {
        for (Component c: components) {
            c.update(dt);
        }
    }

    public String getName() {
        return name;
    }

    public void setDestroyed() {
        isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void finalize() {
        for (Component c: components) {
            c.finalize();
        }
    }
}
