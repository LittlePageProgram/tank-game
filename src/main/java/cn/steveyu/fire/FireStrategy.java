package cn.steveyu.fire;

import cn.steveyu.gameObj.Player;

/**
 * The fire strategy use the strategy pattern
 * so we can make many kinds of fires
 */
public interface FireStrategy {
    void fire(Player player);
}
