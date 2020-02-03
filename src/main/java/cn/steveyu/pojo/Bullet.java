package cn.steveyu.pojo;

import cn.steveyu.manager.ResourceMgr;
import cn.steveyu.run.TankFrame;
import lombok.Data;

import java.awt.*;

/**
 * 炮弹对象
 */
@Data
public class Bullet extends AbstractGameObject{
    private int x, y;
    private Dir dir;
    private Group group;
    private static final int SPEED = 10;
    private boolean live = true;
    public static final int BULLET_WIDTH = 50, BULLET_HEIGHT = 50;
    private Rectangle rect;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rect = new Rectangle(x, y, BULLET_WIDTH, BULLET_HEIGHT);
    }

    public void paint(Graphics g) {
        move();
        switch (dir) {
            case U:
                g.drawImage(ResourceMgr.bulletU, x, y, BULLET_WIDTH, BULLET_HEIGHT, null);
                break;
            case L:
                g.drawImage(ResourceMgr.bulletL, x, y, BULLET_WIDTH, BULLET_HEIGHT, null);
                break;
            case R:
                g.drawImage(ResourceMgr.bulletR, x, y, 50, 50, null);
                break;
            case D:
                g.drawImage(ResourceMgr.bulletD, x, y, 50, 50, null);
                break;
        }
        rect.setBounds(x, y, BULLET_WIDTH, BULLET_HEIGHT);
    }

    private void move() {
        switch (this.dir) {
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
        if (x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            live = false;
        }
    }

    public void die() {
        this.setLive(false);
    }
}
