package com.owen;

import java.awt.*;

/**
 * 可以发射多颗子弹的坦克
 *
 * @author OwenHuang
 * @since 2022/11/5 17:28
 */
public class TankWithMultiBullets {
    private static final int SPEED = 5;
    private int x = 0;
    private int y = 0;
    private Direction direction;

    private TankFrame11 tankFrame;

    private boolean moving = false;

    public TankWithMultiBullets(int x, int y, Direction direction, TankFrame11 tankFrame) {
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
        /**
         * 此处只是向容器中添加子弹，最终会造成内存泄露
         * 子弹超过游戏桌面后就应该被销毁，但是任然存在于bullets中，这样最终会造成内存泄露
         */
        this.tankFrame.bullets.add(new Bullet(this.x, this.y, this.direction));
    }
}