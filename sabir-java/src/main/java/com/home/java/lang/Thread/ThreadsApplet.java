/*
 * Thread.java
 *
 * Created on November 26, 2008, 1:48 PM
 *
 */

package com.home.java.lang.Thread;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Date;
/**
 *
 * @author siddisab
 */
public class ThreadsApplet extends Applet implements Runnable {
    
    Thread runner;
    public void init() {
    }
    public void start() {
        if (runner==null) {            
            runner=new Thread(this);
            runner.start();
        }
    }
    public void stop() {
        if (runner!=null) {
            runner=null;
        }
    }
    public void run() {
        Thread thisthread=Thread.currentThread();
        while (runner==thisthread) {
            repaint();
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) { }
        }
    }
    public void paint(Graphics screen) {
        Date hello=new Date();
        screen.setFont(new Font("Sherif", Font.BOLD+Font.ITALIC, 20));
        screen.drawString(hello.toString(),50,50);
    }
}
