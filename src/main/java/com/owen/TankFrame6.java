package com.owen;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 根据面向对象的抽象特性抽象出坦克类并且定义坦克的特性
 *
 * @author OwenHuang
 * @since 2022/11/3 22:25
 */
public class TankFrame6 extends Frame {
    private Tank tank1 = new Tank(200, 200, Direction.DOWN);

    public TankFrame6() {
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
        tank1.paint(graphics);
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
                tank1.setDirection(Direction.LEFT);
            }
            if (isRightPressed) {
                tank1.setDirection(Direction.RIGHT);
            }
            if (isUpPressed) {
                tank1.setDirection(Direction.UP);
            }
            if (isDownPressed) {
                tank1.setDirection(Direction.DOWN);
            }
        }

    }


    public static void main(String[] args) throws InterruptedException {
        Frame frame = new TankFrame6();
        while (true) {
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
