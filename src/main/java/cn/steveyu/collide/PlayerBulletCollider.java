package cn.steveyu.collide;

import cn.steveyu.pojo.*;

public class PlayerBulletCollider implements Collider {
    @Override
    public void collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Bullet && go2 instanceof Player) {
            collide(go2, go1);
            return;
        }
        if (go1 instanceof Player && go2 instanceof Bullet) {
            Player p = (Player) go1;
            Bullet b = (Bullet) go2;
            if (p.isLive() && p.getRect().intersects(b.getRect()) && b.getGroup() == Group.BAD) {
                b.die();
                p.die();
            }
        }
    }
}
