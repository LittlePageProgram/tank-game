package cn.steveyu.fire;

import cn.steveyu.gameObj.Bullet;
import cn.steveyu.gameObj.Dir;
import cn.steveyu.gameObj.Player;
import cn.steveyu.run.TankFrame;

/**
 * 四个方向开火
 */
public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Player player) {
        for (Dir dir : Dir.values()) {
            TankFrame.INSTANCE.add(new Bullet(player.getX(), player.getY(), dir, player.getGroup()));
        }
    }
}
