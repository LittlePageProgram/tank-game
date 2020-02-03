package cn.steveyu.pojo;

import java.awt.*;
import java.io.Serializable;

/**
 * 抽象游戏对象
 */
public abstract class AbstractGameObject implements Serializable {
    public abstract void paint(Graphics g);

    public abstract boolean isLive();

}
