package com.owen;

import java.awt.*;

/**
 * @author OwenHuang
 * @since 2022/11/3 22:22
 */
public class TankBeforeFire {
    private static final int SPEED = 5;
    private int x = 0;
    private int y = 0;
    private Direction direction;

    private boolean moving = false;

    public TankBeforeFire(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isMoving() {
        return moving;
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
}