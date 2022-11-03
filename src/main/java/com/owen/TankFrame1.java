package com.owen;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 窗口切换Frame都会往右下角移动10个像素
 *
 * @author OwenHuang
 * @since 2022/11/2 21:20
 */
public class TankFrame1 extends Frame {

    private int x = 0;
    private int y = 0;

    public TankFrame1() {
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
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.fillRect(x, y, 50, 50);
        x += 10;
        y += 10;
    }

    public static void main(String[] args) {
        new TankFrame1();
    }

}
