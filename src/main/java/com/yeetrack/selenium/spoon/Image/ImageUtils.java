package com.yeetrack.selenium.spoon.Image;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;

/**
 * 图片工具类，主要针对图片水印处理
 *
 * @author  025079
 * @version  [版本号, 2011-11-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ImageUtils
{

    /**
     * 从图片名字中得到真正名字，去掉拓展名和程序添加的浏览器name
     */
    public static String getRealImageNameFromFile(File file)
    {
        String fileName = file.getName().toLowerCase();
        int lastPosition = fileName.lastIndexOf(".");
        fileName = fileName.substring(0, lastPosition);

        //去掉浏览器name和--
        if(fileName.endsWith("firefox"))
        {
            return fileName.substring(0, fileName.length()-9);
        }
        else if(fileName.endsWith("chrome"))
        {
            return fileName.substring(0, fileName.length()-8);
        }
        else if(fileName.endsWith("internet"))
        {
            return fileName.substring(0, fileName.length()-10);
        }
        return null;
    }

    public static String generateGif(java.util.List<String> imagePaths)
    {
        //生成gif图片的文件名
        String gifName = imagePaths.get(0);
        int start = gifName.indexOf("screenshots");
        int end = gifName.lastIndexOf(File.separator);
        gifName = gifName.substring(start+12, end).replace(File.separator, "_");
        File file = new File("target/screenshotResult/gif");
        if(!file.exists())
            file.mkdirs();
        String fileName = "target/screenshotResult/gif/"+gifName+".gif";
        try{
            AnimatedGifEncoder e = new AnimatedGifEncoder();
            e.setRepeat(0);
            e.start(fileName);
            for(String pathIndex : imagePaths)
            {
                BufferedImage src = ImageIO.read(new File(pathIndex));
                e.setDelay(1500);
                e.addFrame(src);
            }
            e.finish();
        }catch(IOException e){
            e.printStackTrace();
        }
        return fileName;
    }
}