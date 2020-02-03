package cn.steveyu.collide;

import cn.steveyu.pojo.AbstractGameObject;
import cn.steveyu.pojo.Tank;
import cn.steveyu.pojo.Wall;

/**
 * 坦克和墙对撞，坦克退后一步
 */
public class TankWallCollider implements Collider {
    @Override
    public void collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Wall && go2 instanceof Tank) {
            collide(go2, go1);
            return;
        }
        if (go1 instanceof Tank && go2 instanceof Wall) {
            Tank t = (Tank) go1;
            Wall w = (Wall) go2;
            if (t.isLive() && t.getRect().intersects(w.getRect())) {
                t.back();
            }
        }
    }
}
