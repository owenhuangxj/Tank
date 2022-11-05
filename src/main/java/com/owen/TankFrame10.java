package com.owen;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 发射子弹(只能发射一颗子弹)
 *
 * @author OwenHuang
 * @since 2022/11/4 5:25
 */
public class TankFrame10 extends Frame {
    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 800;

    private Image screenImage;
    private TankWithOneBullet tank = new TankWithOneBullet(200, 200, Direction.DOWN, this);
    Bullet bullet = new Bullet(200, 200, Direction.DOWN);

    public TankFrame10() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
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
        tank.paint(graphics);
        bullet.paint(graphics);
    }

    @Override
    public void update(Graphics graphics) {
        if (screenImage == null) {
            screenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics screenImageGraphics = screenImage.getGraphics();
        Color color = screenImageGraphics.getColor();
        screenImageGraphics.setColor(Color.BLACK);
        screenImageGraphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        screenImageGraphics.setColor(color);
        paint(screenImageGraphics);
        graphics.drawImage(screenImage, 0, 0, null);
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
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                    break;
                default:
                    break;
            }
            setTankDirection();
        }

        private void setTankDirection() {
            tank.setMoving(true);
            if (isLeftPressed) {
                tank.setDirection(Direction.LEFT);
            }
            if (isRightPressed) {
                tank.setDirection(Direction.RIGHT);
            }
            if (isUpPressed) {
                tank.setDirection(Direction.UP);
            }
            if (isDownPressed) {
                tank.setDirection(Direction.DOWN);
            }
            if (!isDownPressed && !isUpPressed && !isLeftPressed && !isRightPressed) {
                tank.setMoving(false);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Frame frame = new TankFrame10();
        while (true) {
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
