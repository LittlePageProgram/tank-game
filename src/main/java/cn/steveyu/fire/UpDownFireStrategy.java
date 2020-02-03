package cn.steveyu.fire;

import cn.steveyu.gameObj.Bullet;
import cn.steveyu.gameObj.Dir;
import cn.steveyu.gameObj.Player;
import cn.steveyu.run.TankFrame;

/**
 * 上下开火
 */
public class UpDownFireStrategy implements FireStrategy {
    @Override
    public void fire(Player player) {
        TankFrame.INSTANCE.add(new Bullet(player.getX(), player.getY(), Dir.U, player.getGroup()));
        TankFrame.INSTANCE.add(new Bullet(player.getX(), player.getY(), Dir.D, player.getGroup()));
    }
}
