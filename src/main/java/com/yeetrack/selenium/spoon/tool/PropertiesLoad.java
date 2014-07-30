package com.yeetrack.selenium.spoon.tool;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by victor on 14-3-19.
 */
public class PropertiesLoad
{
    private static Logger logger = LoggerFactory.getLogger(PropertiesLoad.class);
    private static PropertiesLoad propertiesLoad;
    private static Properties properties = new Properties();

    static {
        File  file = new File("src/main/resources/application.properties");
        if (!file.exists())
            file = new File("src/test/resources/application.properties");
        if(!file.exists())
        {
            logger.error("application.properties not Found!!!");
            System.exit(1);
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertiesLoad getInstance()
    {
        if(propertiesLoad == null)
        {
            propertiesLoad = new PropertiesLoad();
        }
        return propertiesLoad;
    }

    /**
     * 获取浏览器宽度
     */
    public String getBrowserWidth()
    {
        return properties.getProperty("browser.width");
    }

    /**
     * 获取浏览器高度
     */
    public String getBrowserHeight()
    {
        return properties.getProperty("browser.height");
    }

    /**
     * 获取chrome webdriver路径
     */
    public String getChromeDriverPath()
    {
        return properties.getProperty("webdriver.chrome.driver");
    }
    /**
     * 获取firefox路径
     */
    public String getFirefoxPath()
    {
        return properties.getProperty("webdriver.firefox.bin");
    }

    /**
     * 获取切割成小块的宽度
     */
    public String getImageSliceWidth()
    {
        return properties.getProperty("image.slice.width");
    }

    /**
     * 获取切割成小块的高度
     */
    public String getImageSliceHeight()
    {
        return properties.getProperty("image.slice.height");
    }

}
