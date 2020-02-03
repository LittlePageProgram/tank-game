package cn.steveyu.fire;

import cn.steveyu.pojo.Bullet;
import cn.steveyu.pojo.Dir;
import cn.steveyu.pojo.Player;
import cn.steveyu.run.TankFrame;

/**
 * 上下开火
 */
public class UpDownFireStrategy implements FireStrategy {
    @Override
    public void fire(Player player) {
        TankFrame.INSTANCE.gameModel.add(new Bullet(player.getX(), player.getY(), Dir.U, player.getGroup()));
        TankFrame.INSTANCE.gameModel.add(new Bullet(player.getX(), player.getY(), Dir.D, player.getGroup()));
    }
}
