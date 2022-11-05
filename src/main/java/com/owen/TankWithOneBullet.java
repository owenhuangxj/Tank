package com.owen;

import java.awt.*;

/**
 * 只能发射一颗子弹的版本
 *
 * @author OwenHuang
 * @since 2022/11/5 17:28
 */
public class TankWithOneBullet {
    private static final int SPEED = 5;
    private int x = 0;
    private int y = 0;
    private Direction direction;

    private TankFrame10 tankFrame;

    private boolean moving = false;

    /**
     *
     * @param x 坦克的X坐标位置
     * @param y 坦克的Y坐标位置
     * @param direction 坦克运动方向
     * @param tankFrame 游戏桌面，子弹是在坦克中new处理的，坦克发射的子弹需要在游戏桌面画出来，所以坦克必须持有游戏桌面的引用
     */
    public TankWithOneBullet(int x, int y, Direction direction, TankFrame10 tankFrame) {
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
        this.tankFrame.bullet = new Bullet(this.x, this.y, this.direction);
    }
}