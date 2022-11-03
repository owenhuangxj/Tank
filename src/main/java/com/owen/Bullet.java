package com.owen;

import java.awt.*;

/**
 * @author OwenHuang
 * @since 2022/11/3 23:13
 */
public class Bullet {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int SPEED = 10;
    private int x, y;
    private Direction direction;

    public Bullet(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    private void move() {
        System.out.println("Tank direction:" + direction + "x:" + x + ",y:" + y);
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

    public void paint(Graphics graphics) {
        Color color = graphics.getColor();
        graphics.setColor(Color.RED);
        graphics.fillOval(x, y, WIDTH, HEIGHT);
        graphics.setColor(color);
        move();
    }
}
