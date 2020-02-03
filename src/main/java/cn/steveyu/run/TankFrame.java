package cn.steveyu.run;

import cn.steveyu.collide.Collider;
import cn.steveyu.gameObj.*;
import cn.steveyu.mgr.PropertyMgr;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 坦克窗体
 */
public class TankFrame extends Frame {
    public static TankFrame INSTANCE = null;

    private Player myTank = new Player(100, 100, Dir.R);

    private List<AbstractGameObject> objects = null;

    private List<Collider> colliders = null;

    private Random random = new Random();

    Image offScreenImage = null;

    public static final int GAME_WIDTH = Integer.parseInt(PropertyMgr.get("gameWidth"));
    public static final int GAME_HEIGHT = Integer.parseInt(PropertyMgr.get("gameHeight"));

    private TankFrame() {
        this.setLocation(400, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setTitle("tank war");
        this.addWindowListener(new WindowCloseListener());
        this.addKeyListener(new TankKeyListener());
        initColliders();
        initGameObjects();
        this.setVisible(true);
        new Thread(new RepaintRunnable()).start();
    }

    private void initColliders() {
        try {
            colliders = new ArrayList<>();
            String[] collidersNames = PropertyMgr.get("colliders").split(",");
            for (String collidersName : collidersNames) {
                Class<?> collideClazz = Class.forName("cn.steveyu.collide." + collidersName.trim());
                Collider collider = (Collider) (collideClazz.getConstructor().newInstance());
                colliders.add(collider);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initGameObjects() {
        objects = new ArrayList<>();
        objects.add(myTank);
        int tankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));
        this.addEnemyTank(tankCount);
        this.add(new Wall(300, 200, 400, 50));
    }

    private void addEnemyTank(int tankNumber) {
        for (int i = 0; i < tankNumber; i++) {
            objects.add(new Tank(random.nextInt(GAME_WIDTH), random.nextInt(GAME_HEIGHT)));
        }
    }

    public void add(AbstractGameObject abstractGameObject) {
        objects.add(abstractGameObject);
    }

    public static TankFrame getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TankFrame();
        }
        return INSTANCE;
    }

    /**
     * 双缓冲
     *
     * @param g
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }


    /**
     * repaint线程
     */
    private class RepaintRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("游戏组件个数：" + objects.size(), 10, 50);
        g.setColor(c);

        for (int i = 0; i < objects.size(); i++) {
            AbstractGameObject go = objects.get(i);
            // 碰撞检测
            for (int j = i + 1; j < objects.size(); j++) {
                for (Collider collider : colliders) {
                    collider.collide(go, objects.get(j));
                }
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

    /**
     * 按下按键事件
     */
    private class TankKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }

    /**
     * 关闭窗口
     */
    private class WindowCloseListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
