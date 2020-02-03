package cn.steveyu.gameObj;

import java.awt.*;

/**
 * 抽象游戏对象
 */
public abstract class AbstractGameObject {
    public abstract void paint(Graphics g);

    public abstract boolean isLive();

}
