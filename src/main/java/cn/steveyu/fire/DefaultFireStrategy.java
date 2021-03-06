package cn.steveyu.fire;

import cn.steveyu.pojo.Bullet;
import cn.steveyu.pojo.Player;
import cn.steveyu.run.TankFrame;

/**
 * 一个方向开火
 */
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Player player) {
        TankFrame.INSTANCE.gameModel.add(new Bullet(player.getX(), player.getY(), player.getDir(), player.getGroup()));
    }
}
