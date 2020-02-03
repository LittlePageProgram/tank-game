package cn.steveyu.pojo;

import cn.steveyu.collide.ColliderChain;
import cn.steveyu.manager.PropertyMgr;
import cn.steveyu.run.TankFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel implements Serializable {
    private Player myTank = new Player(100, 100, Dir.R);

    private List<AbstractGameObject> objects = null;

    private ColliderChain colliderChain = new ColliderChain();

    private Random random = new Random();

    public GameModel() {
        initGameModel();
    }

    public void initGameModel() {
        objects = new ArrayList<>();
        objects.add(myTank);
        objects = new ArrayList<>();
        objects.add(myTank);
        int tankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));
        this.addEnemyTank(tankCount);
    }

    private void addEnemyTank(int tankNumber) {
        for (int i = 0; i < tankNumber; i++) {
            objects.add(new Tank(random.nextInt(TankFrame.GAME_WIDTH), random.nextInt(TankFrame.GAME_HEIGHT)));
        }
    }

    public void add(AbstractGameObject abstractGameObject) {
        objects.add(abstractGameObject);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("游戏组件个数：" + objects.size(), 10, 50);
        g.setColor(c);

        for (int i = 0; i < objects.size(); i++) {
            AbstractGameObject go = objects.get(i);
            // 碰撞检测
            for (int j = i + 1; j < objects.size(); j++) {
                colliderChain.collide(go, objects.get(j));
            }
            if (go.isLive()) {
                go.paint(g);
            }
        }
        /**
         * 移除死亡组件
         */
        for (int i = 0; i < objects.size(); i++) {
            AbstractGameObject object = objects.get(i);
            if (!object.isLive()) {
                objects.remove(object);
            }
        }
    }


    public void keyPressed(KeyEvent e) {
        myTank.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        myTank.keyReleased(e);
    }
}
