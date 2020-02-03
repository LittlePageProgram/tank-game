package cn.steveyu.collide;

import cn.steveyu.gameObj.*;

/**
 * 我放子弹敌方坦克碰撞器
 *
 * 我方子弹碰到敌方坦克，子弹和坦克消失
 */
public class BulletTankCollider implements Collider {
    @Override
    public void collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Tank && go2 instanceof Bullet) {
            collide(go2, go1);
            return;
        }
        if (go1 instanceof Bullet && go2 instanceof Tank) {
            Bullet b = (Bullet) go1;
            Tank t = (Tank) go2;
            if(b.isLive() && t.isLive() && b.getGroup() == Group.GOOD) {
                if(b.getRect().intersects(t.getRect())){
                    b.die();
                    t.die();
                }
            }
        }
    }
}
