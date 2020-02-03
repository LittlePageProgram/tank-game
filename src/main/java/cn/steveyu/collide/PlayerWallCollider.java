package cn.steveyu.collide;

import cn.steveyu.gameObj.AbstractGameObject;
import cn.steveyu.gameObj.Player;
import cn.steveyu.gameObj.Wall;

/**
 * 玩家和墙接触，玩家退后一步
 */
public class PlayerWallCollider implements Collider{
    @Override
    public void collide(AbstractGameObject go1, AbstractGameObject go2) {
        if(go1 instanceof Wall && go2 instanceof Player) {
            collide(go2, go1);
            return;
        }
        if(go1 instanceof Player && go2 instanceof Wall) {
            Player p = (Player) go1;
            Wall w = (Wall) go2;
            if(p.isLive() && p.getRect().intersects(w.getRect())) {
                p.back();
            }
        }
    }
}
