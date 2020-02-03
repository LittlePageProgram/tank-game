package cn.steveyu.collide;

import cn.steveyu.pojo.AbstractGameObject;

import java.io.Serializable;

/**
 * The collider use chain of responsibility pattern
 * that can let any AbstractGameObject collide
 */
public interface Collider extends Serializable {
    // return true chain go on, return false chain stop
    void collide(AbstractGameObject go1, AbstractGameObject go2);
}
