package cn.steveyu.collide;

import cn.steveyu.gameObj.AbstractGameObject;

/**
 * The collider use chain of responsibility pattern
 * that can let any AbstractGameObject collide
 */
public interface Collider {
    void collide(AbstractGameObject go1, AbstractGameObject go2);
}
