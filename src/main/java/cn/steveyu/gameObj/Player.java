package cn.steveyu.gameObj;

import cn.steveyu.fire.FireStrategy;
import cn.steveyu.fire.FourDirFireStrategy;
import cn.steveyu.mgr.ResourceMgr;
import cn.steveyu.run.TankFrame;
import lombok.Data;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 玩家实体
 */
@Data
public class Player extends AbstractGameObject {
    public static final int PLAYER_WIDTH = 50, PLAYER_HEIGHT = 50;
    private int x, y, oldX, oldY;
    private Dir dir;
    private boolean moving;
    private boolean bL, bU, bR, bD;
    private Rectangle rect;
    private boolean live = true;
    private Group group = Group.GOOD;
    private FireStrategy strategy = null;

    private static final int SPEED = 5;

    public Player(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
        this.dir = dir;
        rect = new Rectangle(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        initFireStrategy();
    }

    /**
     * 初始化开火策略
     */
    private void initFireStrategy() {
        strategy = new FourDirFireStrategy();
    }

    /**
     * 绘画事件
     *
     * @param g
     */
    public void paint(Graphics g) {
        if (!isLive()) return;
        move();
        switch (dir) {
            case U:
                g.drawImage(ResourceMgr.goodTankU, x, y, PLAYER_WIDTH, PLAYER_HEIGHT, null);
                break;
            case L:
                g.drawImage(ResourceMgr.goodTankL, x, y, PLAYER_WIDTH, PLAYER_HEIGHT, null);
                break;
            case R:
                g.drawImage(ResourceMgr.goodTankR, x, y, PLAYER_WIDTH, PLAYER_HEIGHT, null);
                break;
            case D:
                g.drawImage(ResourceMgr.goodTankD, x, y, PLAYER_WIDTH, PLAYER_HEIGHT, null);
                break;
        }
        rect.setRect(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    /**
     * 按下键盘事件
     *
     * @param e 键盘事件
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
        }
        setMainDir();
    }

    /**
     * 当对应MainDir为true时，会一直移动
     */
    private void setMainDir() {
        if (!bL && !bU && !bR && !bD) {
            moving = false;
        } else {
            moving = true;
        }
        if (!bL && bU && !bR && !bD) {
            dir = Dir.U;
        }
        if (bL && !bU && !bR && !bD) {
            dir = Dir.L;
        }
        if (!bL && !bU && bR && !bD) {
            dir = Dir.R;
        }
        if (!bL && !bU && !bR && bD) {
            dir = Dir.D;
        }
    }

    /**
     * 移动函数
     */
    private void move() {
        if (!moving) return;
        oldX = x;
        oldY = y;
        switch (dir) {
            case U:
                y -= SPEED;
                break;
            case D:
                y += SPEED;
                break;
            case L:
                x -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
        }
        boundsCheck();
    }

    private void boundsCheck() {
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.y < 30) {
            this.y = 30;
        }
        if (this.x > TankFrame.GAME_WIDTH - PLAYER_WIDTH) {
            this.x = TankFrame.GAME_WIDTH - PLAYER_WIDTH;
        }
        if (this.y > TankFrame.GAME_HEIGHT - PLAYER_HEIGHT) {
            this.y = TankFrame.GAME_HEIGHT - PLAYER_HEIGHT;
        }
    }

    /**
     * 释放监听事件
     *
     * @param e 键盘监听事件
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            case KeyEvent.VK_Z:
                fire();
                break;
        }
        setMainDir();
    }

    private void fire() {
        strategy.fire(this);
    }

    public void die() {
        this.setLive(false);
        TankFrame.INSTANCE.add(new Explode(x, y));
    }

    public void back() {
        this.x = this.oldX;
        this.y = this.oldY;
    }
}
