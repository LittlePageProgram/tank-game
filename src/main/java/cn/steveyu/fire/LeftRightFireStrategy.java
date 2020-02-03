package cn.steveyu.fire;

import cn.steveyu.gameObj.Bullet;
import cn.steveyu.gameObj.Dir;
import cn.steveyu.gameObj.Player;
import cn.steveyu.run.TankFrame;

/**
 * 左右开火
 */
public class LeftRightFireStrategy implements FireStrategy{
    @Override
    public void fire(Player player) {
        TankFrame.INSTANCE.add(new Bullet(player.getX(), player.getY(), Dir.L, player.getGroup()));
        TankFrame.INSTANCE.add(new Bullet(player.getX(), player.getY(), Dir.R, player.getGroup()));
    }
}
