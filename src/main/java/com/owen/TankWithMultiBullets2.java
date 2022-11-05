package com.owen;

import java.awt.*;

/**
 * 可以发射多颗子弹的坦克
 * 解决了内存泄露，但是因为ArrayList的删除元素的坑会报错ConcurrentModificationException
 *
 * @author OwenHuang
 * @since 2022/11/5 19:10
 */
public class TankWithMultiBullets2 {
    private static final int SPEED = 5;
    private int x = 0;
    private int y = 0;
    private Direction direction;

    private TankFrame12 tankFrame;

    private boolean moving = false;

    public TankWithMultiBullets2(int x, int y, Direction direction, TankFrame12 tankFrame) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tankFrame = tankFrame;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics graphics) {
        Color color = graphics.getColor();
        graphics.setColor(Color.yellow);
        graphics.fillRect(x, y, 50, 50);
        graphics.setColor(color);
        move();
    }

    private void move() {
        if (moving) {
            switch (direction) {
                case UP:
                    y -= SPEED;
                    break;
                case DOWN:
                    y += SPEED;
                    break;
                case LEFT:
                    x -= SPEED;
                    break;
                case RIGHT:
                    x += SPEED;
                    break;
                default:
                    break;
            }
        }
    }

    public void fire() {
        this.tankFrame.bullets.add(new BulletWithLifeStatus(this.x, this.y, this.direction, tankFrame));
    }
}