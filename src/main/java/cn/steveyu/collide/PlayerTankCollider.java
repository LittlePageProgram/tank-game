package cn.steveyu.collide;

import cn.steveyu.pojo.AbstractGameObject;
import cn.steveyu.pojo.Player;
import cn.steveyu.pojo.Tank;

/**
 * 玩家碰到敌军坦克，玩家死亡
 */
public class PlayerTankCollider implements Collider {
    @Override
    public void collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Tank && go2 instanceof Player) {
            collide(go2, go1);
            return;
        }
        if (go1 instanceof Player && go2 instanceof Tank) {
            Player p = (Player) go1;
            Tank t = (Tank) go2;
            if (p.isLive() && p.getRect().intersects(t.getRect())) {
                p.die();
            }
        }
    }
}
