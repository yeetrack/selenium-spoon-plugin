package com.yeetrack.selenium.spoon.tool;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by victor on 14-3-19.
 * 处理firefox有关的事情
 */
public class FirefoxUtils
{
    /**
     * 按下CTRL + / ,隐藏firefox的状态栏
     */
    public static void hideStatusBar()
    {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.setAutoDelay(80);
        robot.mouseMove(500, 500);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_SLASH);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_SLASH);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 按下F11让浏览器全屏
     */
    public static void fullScreen()
    {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.setAutoDelay(80);
        robot.mouseMove(500, 500);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

        robot.keyPress(KeyEvent.VK_F11);
        robot.keyRelease(KeyEvent.VK_F11);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void maximise(WebDriver driver)
    {
        final JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.open('','testwindow','width=400,height=200')");

        driver.close();

        driver.switchTo().window("testwindow");

        js.executeScript("window.moveTo(0,0);");


        /*1280和1024分别为窗口的宽和高，可以用下面的代码得到

        screenDims = Toolkit.getDefaultToolkit（）。getScreenSize（）；

        width = （int） screenDims.getWidth（）；

        height = （int） screenDims.getHeight（）；   */

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        js.executeScript("window.resizeTo("+width+","+height+");");

    }
}
