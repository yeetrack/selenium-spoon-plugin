package com.yeetrack.selenium.spoon.html;

import java.util.List;

/**
 * Created by xuemeng.wang on 2014/7/28.
 * 每个测试用例在test.html中对应的div
 */
public class TestHtmlItem
{
    private String id;
    private String className;
    private String methodName;
    private List<String> images;
    private String gifPath;

    public TestHtmlItem(String id, String className, String methodName, List<String> images, String gifPath)
    {
        this.id = id;
        this.className = className;
        this.methodName = methodName;
        this.images = images;
        this.gifPath = gifPath;
    }


    public String generateTestItemHtml()
    {
        StringBuffer pathHtmlStringBuffer = new StringBuffer();
        //生成图片列表html
        for(String pathIndex : images)
        {
            pathHtmlStringBuffer.append(
                    "<li class=\"span2\">\n" +
                            "                            <a href=\"../../../"+pathIndex+"\" class=\"thumbnail\">\n" +
                            "                                <img src=\"../../../"+pathIndex+"\" title=\""+pathIndex+"\">\n" +
                            "                            </a>\n" +
                            "                        </li>"
            );
        }


        StringBuffer stringBuffer = new StringBuffer(
                "<div class=\"row\">\n" +
                        "                <div class=\"span12\">\n" +
                        "<h2 class=\"test-result {{status}}\">\n" +className+"_"+methodName+
                        "                        \n" +
                        "                        <!-- Icons modified from Glyphish Free. http://glyphish.com -->\n" +
                        "                        <a href=\"../../../target/log/selenium-log.html\" title=\"View device log\" class=\"pull-right icon\">\n" +
                        "                            <img src=\"../static/icon-log.png\" alt=\"View device log\">\n" +
                        "                        </a>\n" +
                        "                        <a href=\"../../../"+gifPath+"\" title=\"View as animated GIF\" class=\"pull-right icon\">\n" +
                        "                            <img src=\"../static/icon-animated.png\" alt=\"View as animated GIF\">\n" +
                        "                        </a>\n" +
                        "                    </h2>\n" +
                        "                    <div class=\"alert alert-error stacktrace {{status}}\">\n" +
                        "                        <div class=\"stacktrace-body collapse\" id=\"stacktrace-{{serial}}\">\n" +
                        "                            {{#body}}\n" +
                        "                            <div class=\"stacktrace-line\">{{{toString}}}</div>\n" +
                        "                            {{/body}}\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <ul class=\"thumbnails\">\n" + pathHtmlStringBuffer.toString()+
                        "                    </ul>\n" +

                        "                </div>\n" +
                        "            </div>"
        );
        return stringBuffer.toString();
    }
}
