package cn.steveyu.collide;

import cn.steveyu.pojo.AbstractGameObject;
import cn.steveyu.manager.PropertyMgr;

import java.util.ArrayList;
import java.util.List;

public class ColliderChain implements Collider{
    private List<Collider> colliders;

    public ColliderChain() {
        initColliders();
    }

    private void initColliders() {
        try {
            colliders = new ArrayList<>();
            String[] collidersNames = PropertyMgr.get("colliders").split(",");
            for (String collidersName : collidersNames) {
                Class<?> collideClazz = Class.forName("cn.steveyu.collide." + collidersName.trim());
                Collider collider = (Collider) (collideClazz.getConstructor().newInstance());
                colliders.add(collider);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collide(AbstractGameObject go1, AbstractGameObject go2) {
        for (Collider collider : colliders) {
            collider.collide(go1, go2);
        }
    }
}
