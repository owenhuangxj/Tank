package com.owen;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 发射多颗子弹(解决内存泄露问题)
 * 但是因为ArrayList的删除元素的坑会报错ConcurrentModificationException
 *
 * @author OwenHuang
 * @since 2022/11/5 19:00
 */
public class TankFrame12 extends Frame {
    static final int GAME_WIDTH = 800;
    static final int GAME_HEIGHT = 800;

    private Image screenImage;
    private TankWithMultiBullets2 tank = new TankWithMultiBullets2(200, 200, Direction.DOWN, this);

    List<BulletWithLifeStatus> bullets = new ArrayList<>();

    public TankFrame12() {
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
        Color color = graphics.getColor();
        graphics.setColor(Color.WHITE);
        Font font = graphics.getFont();
        Font title = new Font("title", Font.BOLD, 25);
        graphics.setFont(title);
        graphics.drawString("子弹的数量:" + bullets.size(), 10, 50);
        tank.paint(graphics);
        for (BulletWithLifeStatus bullet : bullets) {
            bullet.paint(graphics);
        }
        graphics.setFont(font);
        graphics.setColor(color);
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
        Frame frame = new TankFrame12();
        while (true) {
            Thread.sleep(50);
            frame.repaint();
        }
    }
}