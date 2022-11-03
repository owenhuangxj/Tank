package com.owen;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 增加键盘处理，根据上、下、左、右按键判断移动方向
 *
 * @author OwenHuang
 * @since 2022/11/3 21:50
 */
public class TankFrame5 extends Frame {
    private static final int SPEED = 10;
    private int x = 0;
    private int y = 0;

    private Direction direction = Direction.DOWN;

    public TankFrame5() {
        this.setSize(800, 600);
        this.setResizable(false);
        this.setTitle("Tank War");
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new TankKeyListener());
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.fillRect(x, y, 50, 50);
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

    class TankKeyListener extends KeyAdapter {
        private boolean isLeftPressed;
        private boolean isRightPressed;
        private boolean isUpPressed;
        private boolean isDownPressed;
        @Override
        public void keyPressed(KeyEvent event) {
            int keyCode = event.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    isLeftPressed = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    isRightPressed = true;
                    break;
                case KeyEvent.VK_UP:
                    isUpPressed = true;
                    break;
                case KeyEvent.VK_DOWN:
                    isDownPressed = true;
                    break;
                default:
                    break;
            }
            setTankDirection();
        }

        @Override
        public void keyReleased(KeyEvent event) {
            int keyCode = event.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    isLeftPressed = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    isRightPressed = false;
                    break;
                case KeyEvent.VK_UP:
                    isUpPressed = false;
                    break;
                case KeyEvent.VK_DOWN:
                    isDownPressed = false;
                    break;
                default:
                    break;
            }
            setTankDirection();
        }
        private void setTankDirection() {
            if (isLeftPressed) {
                direction = Direction.LEFT;
            }
            if (isRightPressed) {
                direction = Direction.RIGHT;
            }
            if (isUpPressed) {
                direction = Direction.UP;
            }
            if (isDownPressed) {
                direction = Direction.DOWN;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Frame frame = new TankFrame5();
        while (true) {
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
