package cn.steveyu.fire;

import cn.steveyu.pojo.Bullet;
import cn.steveyu.pojo.Dir;
import cn.steveyu.pojo.Player;
import cn.steveyu.run.TankFrame;

/**
 * 四个方向开火
 */
public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Player player) {
        for (Dir dir : Dir.values()) {
            TankFrame.INSTANCE.gameModel.add(new Bullet(player.getX(), player.getY(), dir, player.getGroup()));
        }
    }
}
