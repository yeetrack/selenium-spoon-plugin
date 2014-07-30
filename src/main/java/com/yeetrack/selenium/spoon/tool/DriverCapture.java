package com.yeetrack.selenium.spoon.tool;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by xuemeng.wang on 2014/7/25.
 */
public class DriverCapture
{
    public static String SYSTEMTAG = "~";

    /**
     * 获取case的Class Name和Method Name
     * @return
     */
    public static String[] getCallerInfo()
    {
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        return new String[] { stack[3].getClassName(), stack[3].getMethodName()};
    }

    /**
     * WebDriver截图，默认png格式
     * @param fileUserName 用户传过来的截图名称
     * @param driver
     */
    public static void snapShot(String fileUserName, WebDriver driver)
    {
        String filePath = "target/screenshots";
        String[] callerInfo = getCallerInfo();


        /**
         * 先检测以浏览器名字命令的文件夹是否存在，如果不存在就创建
         */
        String driverType = getWebDriverType(driver);
        File browserDir = new File(filePath+"/"+driverType);
        if(!browserDir.exists())
            browserDir.mkdirs();
        if(browserDir.isFile())
        {
            browserDir.delete();
            browserDir.mkdirs();
        }

        //检测类名的文件夹是否存在，如果不存在就新建
        File classNameDir = new File(filePath+"/"+driverType+"/"+callerInfo[0]);
        if(!classNameDir.exists())
            classNameDir.mkdirs();
        if(classNameDir.isFile())
        {
            classNameDir.delete();
            classNameDir.mkdirs();
        }

        /**
         * 再检测方法名的文件夹是否存在，如果不存在就创建
         */
        File methodNameDir = new File(filePath+"/"+driverType+"/"+callerInfo[0]+"/"+callerInfo[1]);
        if(!methodNameDir.exists())
            methodNameDir.mkdirs();
        if(methodNameDir.isFile())
        {
            methodNameDir.delete();
            methodNameDir.mkdirs();
        }



        File file =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(file, new File(filePath+"/"+driverType+"/"+callerInfo[0]+"/"+callerInfo[1]+"/"+fileUserName + System.currentTimeMillis()+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取WebDriver类型
     */
    public static String getWebDriverType(WebDriver driver)
    {
        String driverString = driver.toString();
        if(driverString.contains("chrome"))
            return "chrome";
        else if(driverString.contains("firefox"))
            return "firefox";
        else if(driverString.contains("ie"))
            return "ie";
        else
            return "";
    }
}
