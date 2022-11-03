package com.owen;

import java.awt.*;
import java.awt.event.*;

/**
 * 每次单击键盘会向右移动100个像素
 *
 * @author OwenHuang
 * @since 2022/11/2 21:20
 */
public class TankFrame2 extends Frame {

    private int x = 0;
    private int y = 0;

    public TankFrame2() {
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
        x += 100;
    }

    class TankKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            x += 200;
        }

        @Override
        public void keyReleased(KeyEvent event) {
            super.keyReleased(event);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Frame frame = new TankFrame2();
        while (true){
            Thread.sleep(500);
            frame.repaint();
        }
    }
}
