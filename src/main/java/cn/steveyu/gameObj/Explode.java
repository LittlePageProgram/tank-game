package cn.steveyu.gameObj;

import cn.steveyu.mgr.ResourceMgr;
import lombok.Data;

import java.awt.*;

/**
 * 爆炸对象
 */
@Data
public class Explode extends AbstractGameObject {
    private int x, y;
    private int width, height;
    private int step = 0;
    private boolean live = true;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = ResourceMgr.explodes[0].getWidth();
        this.height = ResourceMgr.explodes[0].getHeight();
    }

    public void paint(Graphics g) {
        if (!live) {
            return;
        }
        g.drawImage(ResourceMgr.explodes[step], x, y, null);
        step++;
        if (step >= ResourceMgr.explodes.length) {
            live = false;
            step = 0;
        }
    }
}
