package com.yeetrack.selenium.spoon.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/*******************************************************************
 * 该JavaBean可以直接在其他Java应用程序中调用，实现屏幕的"拍照"
 * This JavaBean is used to snapshot the GUI in a
 * Java application! You can embeded
 * it in to your java application source code, and us
 * it to snapshot the right GUI of the application
 * @author liluqun (liluqun@263.net)
 * @version 1.0
 *
 *****************************************************/
public class GuiCamera
{
    /**
     * filename有效性校验，插件采用~来作为系统分隔符
     * @param fileName
     * @return
     */
    private static boolean validateFileName(String fileName)
    {
        return !fileName.contains("~");
    }

    /****************************************************************
     * 对屏幕进行拍照
     * snapShot the Gui once
     ****************************************************************/
    public static void snapShot(String fileName, String imageFormat) {
        validateFileName(fileName);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            //拷贝屏幕到一个BufferedImage对象screenshot
            BufferedImage screenshot = (new Robot()).createScreenCapture(new
                    Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));
            //根据文件前缀变量和文件格式变量，自动生成文件名
            String name=fileName + "."+imageFormat;
            File f = new File(name);
            System.out.print("Save File "+name);
            //将screenshot对象写入图像文件
            ImageIO.write(screenshot, imageFormat, f);
            System.out.print("..Finished!\n");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 截图默认为png格式
     * @param fileName
     */
    public static void snapShot(String fileName)
    {
        snapShot(fileName, "png");
    }


}