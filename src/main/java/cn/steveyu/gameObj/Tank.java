package cn.steveyu.gameObj;

import cn.steveyu.mgr.ResourceMgr;
import cn.steveyu.run.TankFrame;
import lombok.Data;

import java.awt.*;
import java.util.Random;

/**
 * 坦克实体
 */
@Data
public class Tank extends AbstractGameObject {
    private int x, y, oldX, oldY;
    private Dir dir;
    private boolean moving = true;
    private boolean bL, bU, bR, bD;
    private boolean live = true;
    private Group group = Group.BAD;
    public static final int TANK_WIDTH = 50, TANK_HEIGHT = 50;
    private Rectangle rect;

    private Random random = new Random();

    private static final int SPEED = 1;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        this.oldY = y;
        this.oldX = x;
        setRandomDir();
        rect = new Rectangle(x, y, TANK_WIDTH, TANK_HEIGHT);
    }

    /**
     * 绘画事件
     *
     * @param g 窗口画笔
     */
    public void paint(Graphics g) {
        if (!isLive()) return;
        move();
        switch (dir) {
            case U:
                g.drawImage(ResourceMgr.badTankU, x, y, 50, 50, null);
                break;
            case L:
                g.drawImage(ResourceMgr.badTankL, x, y, 50, 50, null);
                break;
            case R:
                g.drawImage(ResourceMgr.badTankR, x, y, 50, 50, null);
                break;
            case D:
                g.drawImage(ResourceMgr.badTankD, x, y, 50, 50, null);
                break;
        }
        rect.setLocation(x, y);
    }


    /**
     * 移动函数
     */
    private void move() {
        oldY = y;
        oldX = x;
        setRandomDir();
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
        fire();
    }

    /**
     * 边界检测
     */
    private void boundsCheck() {
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.y < 30) {
            this.y = 30;
        }
        if (this.x > TankFrame.GAME_WIDTH - TANK_WIDTH) {
            this.x = TankFrame.GAME_WIDTH - TANK_WIDTH;
        }
        if (this.y > TankFrame.GAME_HEIGHT - TANK_HEIGHT) {
            this.y = TankFrame.GAME_HEIGHT - TANK_HEIGHT;
        }
    }

    /**
     * 开火方法
     */
    private void fire() {
        if(random.nextInt(100) > 98){
            TankFrame.INSTANCE.add(new Bullet(x, y, dir, Group.BAD));
        }
    }

    /**
     * 死亡方法，调用后死亡
     */
    public void die() {
        this.setLive(false);
        TankFrame.INSTANCE.add(new Explode(x, y));
    }


    /**
     * 随机方向
     */
    private void setRandomDir() {
        if(random.nextInt(100) > 95 || this.dir == null){
            this.dir = Dir.values()[random.nextInt(Dir.values().length)];
        }
    }

    /**
     * 回退一步
     */
    public void back() {
        x = oldX;
        y = oldY;
    }
}
