package cn.steveyu.collide;

import cn.steveyu.gameObj.AbstractGameObject;
import cn.steveyu.gameObj.Bullet;
import cn.steveyu.gameObj.Wall;

/**
 * 我方子弹碰到墙，子弹消失
 */
public class BulletWallCollider implements Collider {

    @Override
    public void collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go2 instanceof Bullet && go1 instanceof Wall) {
            collide(go2, go1);
            return;
        }
        if (go1 instanceof Bullet && go2 instanceof Wall) {
            Bullet b = (Bullet) go1;
            Wall w = (Wall) go2;
            if (b.isLive()) {
                if (b.getRect().intersects(w.getRect())) {
                    b.die();
                }
            }
        }
    }
}
