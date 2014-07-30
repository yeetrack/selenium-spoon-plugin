package com.yeetrack.selenium.spoon.html;


import com.yeetrack.selenium.spoon.Image.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 * Created by xuemeng.wang on 2014/7/28.
 * 生成test.html页面
 */
public class TestHtmlWrapper
{
    private static Logger logger = LoggerFactory.getLogger(TestHtmlWrapper.class);

    private static List<BrowserResultEntity> browserResultEntityList;

    public static void wrapTestHtml()
    {
        browserResultEntityList = ImageCount.getBrowserResultEntityList();

        //按照浏览器的不同写入不同的文件
        for(BrowserResultEntity browserResultEntity : browserResultEntityList)
        {
            StringBuffer sourceStringBuffer = readTestHtmlTemplate();
            logger.info("读取test html模板完毕");
            String title = browserResultEntity.getBrowserName();
            logger.info("正在生成"+title+"浏览器报表");
            StringBuffer stringBuffer = wrapTitleInfo(sourceStringBuffer, title, title);

            StringBuffer htmlItemStringBuffer = new StringBuffer();

            List<ClassEntity> classList = browserResultEntity.getClassList();
            for(ClassEntity classEntityIndex : classList)
            {
                List<CaseEntity> caseEntityList = classEntityIndex.getCaseList();
                for(CaseEntity caseEntityIndex : caseEntityList)
                {
                    //生成gif图片
                    String gifPath = ImageUtils.generateGif(caseEntityIndex.getScreenShotsFileList());
                    logger.info("生成gif图片完毕");
                    String id = classEntityIndex.getClassName()+"_"+caseEntityIndex.getCaseName();
                    TestHtmlItem htmlItem = new TestHtmlItem(id, classEntityIndex.getClassName(), caseEntityIndex.getCaseName(), caseEntityIndex.getScreenShotsFileList(), gifPath);
                    htmlItemStringBuffer.append(htmlItem.generateTestItemHtml());


                }
            }
            int replaceStart = stringBuffer.indexOf("<replace>");
            int replaceEnd = stringBuffer.indexOf("</replace>");
            stringBuffer = stringBuffer.replace(replaceStart+9, replaceEnd, htmlItemStringBuffer.toString());

            String reportPath = "target/screenshotResult/report";
            File file = new File(reportPath);
            if(!file.exists())
                file.mkdirs();

            File reportFile = new File(reportPath+"/"+browserResultEntity.getBrowserName()+".html");
            try {
//                FileWriter fileWriter = new FileWriter(reportFile);
//                fileWriter.write(stringBuffer.toString());
//                fileWriter.close();
                FileOutputStream fileOutputStream = new FileOutputStream(reportFile);
                fileOutputStream.write(stringBuffer.toString().getBytes("utf-8"));
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeOtherFile2Target();
        logger.info("报表生成完毕");

//        //将css、js等文件copy过去
//        try {
//            FileUtils.copyDirectory(new File("src/test/resources/page"), new File("target/screenshotResult/page"));
//            FileUtils.copyDirectory(new File("src/test/resources/static"), new File(staticDir + ""));
//            FileUtils.copyFile(new File("src/test/resources/spoon.less"), new File("target/screenshotResult/spoon.less"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    /**
     * 读取html模板文件
     */
    private static StringBuffer readTestHtmlTemplate()
    {
        StringBuffer stringBuffer = new StringBuffer();
        //当实际运行时，要从jar包里读取模板文件
        InputStream is=TestHtmlWrapper.class.getClassLoader().getResourceAsStream("page/test.html");
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));

//        File file=new File(testHtmlPath);
//        if(file.isFile() && file.exists()) { //判断文件是否存在
//            InputStreamReader read = null;//考虑到编码格式
//            try {
//                read = new InputStreamReader(
//                        new FileInputStream(file), "utf-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            try {
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    stringBuffer.append(lineTxt);
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//        } else { logger.error("模板文件不存在-->"+System.getProperty("user.dir"));}
        return stringBuffer;
    }

    /**
     * 处理title、subtitle等信息
     */
    private static StringBuffer wrapTitleInfo(StringBuffer stringBuffer, String title, String subtitle)
    {
        String string = stringBuffer.toString();
        String afterTitle = string.replace("{{title}}", title);
        String afterSubTitle = afterTitle.replace("{{subtitle}}", "测试用例执行情况");


        return new StringBuffer(afterSubTitle);
    }

    /**
     * 将报表需要的图片，css，js等文件写到target文件夹中
     */
    private static void writeOtherFile2Target()
    {
        String staticDir = "target/screenshotResult/static";
        File file = new File(staticDir);
        if(!file.exists()) file.mkdirs();

        com.yeetrack.selenium.spoon.tool.FileUtils.copyTextFile(TestHtmlWrapper.class.getClassLoader().getResourceAsStream("spoon.less"), new File("target/screenshotResult/spoon.less"));
        com.yeetrack.selenium.spoon.tool.FileUtils.copyBinaryFile(TestHtmlWrapper.class.getClassLoader().getResourceAsStream("static/arrows.png"), new File(staticDir + "/arrows.png"));
        com.yeetrack.selenium.spoon.tool.FileUtils.copyTextFile(TestHtmlWrapper.class.getClassLoader().getResourceAsStream("static/bootstrap.min.css"), new File(staticDir + "/bootstrap.min.css"));
        com.yeetrack.selenium.spoon.tool.FileUtils.copyTextFile(TestHtmlWrapper.class.getClassLoader().getResourceAsStream("static/bootstrap.min.js"), new File(staticDir + "/bootstrap.min.js"));
        com.yeetrack.selenium.spoon.tool.FileUtils.copyTextFile(TestHtmlWrapper.class.getClassLoader().getResourceAsStream("static/bootstrap-responsive.min.css"), new File(staticDir + "/bootstrap-responsive.min.css"));
        com.yeetrack.selenium.spoon.tool.FileUtils.copyBinaryFile(TestHtmlWrapper.class.getClassLoader().getResourceAsStream("static/bullets.png"), new File(staticDir + "/bullets.png"));
        com.yeetrack.selenium.spoon.tool.FileUtils.copyBinaryFile(TestHtmlWrapper.class.getClassLoader().getResourceAsStream("static/icon-animated.png"), new File(staticDir + "/icon-animated.png"));
        com.yeetrack.selenium.spoon.tool.FileUtils.copyBinaryFile(TestHtmlWrapper.class.getClassLoader().getResourceAsStream("static/icon-devices.png"), new File(staticDir + "/icon-devices.png"));
        com.yeetrack.selenium.spoon.tool.FileUtils.copyBinaryFile(TestHtmlWrapper.class.getClassLoader().getResourceAsStream("static/icon-log.png"), new File(staticDir + "/icon-log.png"));
        com.yeetrack.selenium.spoon.tool.FileUtils.copyTextFile(TestHtmlWrapper.class.getClassLoader().getResourceAsStream("static/jquery.min.js"), new File(staticDir + "/jquery.min.js"));
        com.yeetrack.selenium.spoon.tool.FileUtils.copyTextFile(TestHtmlWrapper.class.getClassLoader().getResourceAsStream("static/jquery.nivo.slider.pack.js"), new File(staticDir + "/jquery.nivo.slider.pack.js"));
        com.yeetrack.selenium.spoon.tool.FileUtils.copyBinaryFile(TestHtmlWrapper.class.getClassLoader().getResourceAsStream("static/loading.gif"), new File(staticDir + "/loading.gif"));
        com.yeetrack.selenium.spoon.tool.FileUtils.copyTextFile(TestHtmlWrapper.class.getClassLoader().getResourceAsStream("static/nivo-slider.css"), new File(staticDir + "/nivo-slider.css"));
    }


    public static void main(String[] args) {
        wrapTestHtml();
    }
}
