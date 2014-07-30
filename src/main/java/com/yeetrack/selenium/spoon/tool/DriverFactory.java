package com.yeetrack.selenium.spoon.tool;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by victor on 14-3-19.
 * 获取不同浏览器的driver
 */
public class DriverFactory
{
    private static Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    //Chrome 截图大小
    private static int chromeWidth = -1;
    private static int chromeHeight = -1;
    //Firefox 截图大小
    private static int firefoxWidth = -1;
    private static int firefoxHeight = -1;
    //Internet截图大小
    private static int internetWidth = -1;
    private static int internetHeight = -1;
    private static float width;
    private static float height;
    static {
        width = Float.parseFloat(PropertiesLoad.getInstance().getBrowserWidth());
        height = Float.parseFloat(PropertiesLoad.getInstance().getBrowserHeight());
    }

    /**
     * 获取Driver,driver的大小以chrome为标准
     */
    public static WebDriver getBrowserDriver(String browserType)
    {
        WebDriver driver = null;
        if(browserType == null || "".equals(browserType))
            System.exit(2);
        String type = browserType.toLowerCase();

//        File sizeFile = new File("size.txt");
//        if(!sizeFile.exists())
//            runBrowserCase();
//        FileReader reader = null;
//        try {
//            reader = new FileReader(sizeFile);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        BufferedReader bufferedReader = new BufferedReader(reader);
//        String line;
//        try {
//            while ((line=bufferedReader.readLine())!=null)
//            {
//                String[] size = line.split("\\*");
//                if("firefox".equals(size[0]))
//                {
//                    firefoxWidth = Integer.parseInt(size[1]);
//                    firefoxHeight = Integer.parseInt(size[2]);
//                }
//                else if("chrome".equals(size[0]))
//                {
//                    chromeWidth = Integer.parseInt(size[1]);
//                    chromeHeight = Integer.parseInt(size[2]);
//                }
//                else if("internet".equals(size[0]))
//                {
//                    internetWidth = Integer.parseInt(size[1]);
//                    internetHeight = Integer.parseInt(size[2]);
//                }
//                else //其他
//                {
//
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                bufferedReader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        if("firefox".equals(type))
        {
            driver = getFirefoxDriver();

        }
        else if("chrome".equals(type))
        {
            driver = getChromeDriver();
        }
        else if("internet".equals(type))
            driver = getInternetDriver();
        else
            logger.error("未知的浏览器类型---"+type);




        return driver;
    }

    /**
     * 获取firefox的driver
     */
    public static WebDriver getFirefoxDriver()
    {
        System.setProperty("webdriver.firefox.bin", PropertiesLoad.getInstance().getFirefoxPath());
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1024, 800));
        //FirefoxUtils.hideStatusBar();
        return driver;
    }

    /**
     * 获取chrome Driver
     */
    public static WebDriver getChromeDriver()
    {
        System.setProperty("webdriver.chrome.driver", PropertiesLoad.getInstance().getChromeDriverPath());
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1032, (int)height));
        return driver;
    }
    /**
     * 获取internet driver
     */
    public static WebDriver getInternetDriver()
    {
        WebDriver driver = new InternetExplorerDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension((int)width, (int)height));
        return driver;
    }

    public static int getChromeWidth() {
        return chromeWidth;
    }

    public static void setChromeWidth(int chromeWidth) {
        DriverFactory.chromeWidth = chromeWidth;
    }

    public static int getChromeHeight() {
        return chromeHeight;
    }

    public static void setChromeHeight(int chromeHeight) {
        DriverFactory.chromeHeight = chromeHeight;
    }

    public static int getFirefoxWidth() {
        return firefoxWidth;
    }

    public static void setFirefoxWidth(int firefoxWidth) {
        DriverFactory.firefoxWidth = firefoxWidth;
    }

    public static int getFirefoxHeight() {
        return firefoxHeight;
    }

    public static void setFirefoxHeight(int firefoxHeight) {
        DriverFactory.firefoxHeight = firefoxHeight;
    }

    public static int getInternetWidth() {
        return internetWidth;
    }

    public static void setInternetWidth(int internetWidth) {
        DriverFactory.internetWidth = internetWidth;
    }

    public static int getInternetHeight() {
        return internetHeight;
    }

    public static void setInternetHeight(int internetHeight) {
        DriverFactory.internetHeight = internetHeight;
    }

}
