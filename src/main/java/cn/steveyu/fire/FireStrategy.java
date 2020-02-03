package cn.steveyu.fire;

import cn.steveyu.pojo.Player;

import java.io.Serializable;

/**
 * The fire strategy use the strategy pattern
 * so we can make many kinds of fires
 */
public interface FireStrategy extends Serializable {
    void fire(Player player);
}
