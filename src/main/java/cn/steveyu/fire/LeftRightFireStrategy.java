package cn.steveyu.fire;

import cn.steveyu.pojo.Bullet;
import cn.steveyu.pojo.Dir;
import cn.steveyu.pojo.Player;
import cn.steveyu.run.TankFrame;

/**
 * 左右开火
 */
public class LeftRightFireStrategy implements FireStrategy {
    @Override
    public void fire(Player player) {
        TankFrame.INSTANCE.gameModel.add(new Bullet(player.getX(), player.getY(), Dir.L, player.getGroup()));
        TankFrame.INSTANCE.gameModel.add(new Bullet(player.getX(), player.getY(), Dir.R, player.getGroup()));
    }
}
