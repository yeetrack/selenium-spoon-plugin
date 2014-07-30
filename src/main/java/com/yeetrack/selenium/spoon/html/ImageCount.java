package com.yeetrack.selenium.spoon.html;

import com.yeetrack.selenium.spoon.Image.CompratorByLastModified;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xuemeng.wang on 2014/7/28.
 * 统计target/screenshot文件夹中的图片，按照不同浏览器统计
 */
public class ImageCount
{
    private static String IMAGEPATH = "target/screenshots";
    private static List<BrowserResultEntity> browserResultEntityList;



    private static List<BrowserResultEntity> imageCountByBrowserAndCase()
    {
        List<BrowserResultEntity> browserResultList = new ArrayList<BrowserResultEntity>();

        File file = new File(IMAGEPATH);
        File[] browserDirs = file.listFiles();

        for(File indexBroswerFile : browserDirs)
        {
            String browserName = indexBroswerFile.getName();
            BrowserResultEntity browserResultEntity = new BrowserResultEntity();
            browserResultEntity.setBrowserName(browserName);
            //遍历类名文件夹
            File[] classDirs = indexBroswerFile.listFiles();

            for(File indexClassFile : classDirs)
            {
                String className = indexClassFile.getName();
                ClassEntity classEntity = new ClassEntity();
                classEntity.setClassName(className);

                File[] cases = indexClassFile.listFiles();
                //遍历类名文件夹下的case
                for(File indexCaseFile : cases)
                {
                    CaseEntity caseEntity = new CaseEntity();
                    caseEntity.setCaseName(indexCaseFile.getName());
                    //处理截图
                    File[] shots = indexCaseFile.listFiles();
                    //按时间正序排列
                    Arrays.sort(shots, new CompratorByLastModified());
                    List<String> shotFileList = new ArrayList<String>();
                    for (File shotFile : shots)
                        shotFileList.add(shotFile.getAbsolutePath());

                    caseEntity.setScreenShotsFileList(shotFileList);
                    classEntity.getCaseList().add(caseEntity);
                }
                browserResultEntity.getClassList().add(classEntity);
            }
            browserResultList.add(browserResultEntity);

        }
        return browserResultList;
    }

    public static List<BrowserResultEntity> getBrowserResultEntityList() {
        if(browserResultEntityList == null)
            browserResultEntityList = imageCountByBrowserAndCase();
        return browserResultEntityList;
    }

    public static void setBrowserResultEntityList(List<BrowserResultEntity> browserResultEntityList) {
        ImageCount.browserResultEntityList = browserResultEntityList;
    }

    public static void main(String[] args) {
        List<BrowserResultEntity> aa = imageCountByBrowserAndCase();
        System.out.println(aa.size());
    }
}
