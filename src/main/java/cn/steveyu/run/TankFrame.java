package cn.steveyu.run;

import cn.steveyu.collide.ColliderChain;
import cn.steveyu.pojo.*;
import cn.steveyu.manager.PropertyMgr;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 坦克窗体
 */
public class TankFrame extends Frame {
    public static TankFrame INSTANCE = null;

    Image offScreenImage = null;

    public static final int GAME_WIDTH = Integer.parseInt(PropertyMgr.get("gameWidth"));
    public static final int GAME_HEIGHT = Integer.parseInt(PropertyMgr.get("gameHeight"));

    public GameModel gameModel = new GameModel();

    private TankFrame() {
        this.setLocation(400, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setTitle("tank war");
        this.addWindowListener(new WindowCloseListener());
        this.addKeyListener(new TankKeyListener());
        this.setResizable(false);
        this.setVisible(true);
        new Thread(new RepaintRunnable()).start();
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

    /**
     * 窗体绘制
     *
     * @param g 窗体画笔
     */
    @Override
    public void paint(Graphics g) {
        gameModel.paint(g);
    }

    /**
     * 按下按键事件
     */
    private class TankKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_S) {
                save();
            }
            if(e.getKeyCode() == KeyEvent.VK_L) {
                load();
            }
            gameModel.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            gameModel.keyReleased(e);
        }
    }

    /**
     * 载入
     */
    private void load() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            File f = new File("src/main/resources/data.dat");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            this.gameModel = (GameModel) (ois.readObject());
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(ois != null) {
                    ois.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存
     */
    private void save() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            File f = new File("src/main/resources/data.dat");
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(gameModel);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
