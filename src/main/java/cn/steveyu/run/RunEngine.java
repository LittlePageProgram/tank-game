package cn.steveyu.run;

import cn.steveyu.utils.AudioUtils;

public class RunEngine {
    public static void main(String[] args) {
        new RunEngine().start();
    }

    /**
     * The entrance of the Game
     */
    public void start() {
        TankFrame.getInstance();
        new Thread(()->new AudioUtils("audio/hundouluobgm.wav").loop()).start();
    }
}
