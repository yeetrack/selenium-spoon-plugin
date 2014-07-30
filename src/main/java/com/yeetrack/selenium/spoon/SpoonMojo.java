package com.yeetrack.selenium.spoon;

import com.yeetrack.selenium.spoon.html.TestHtmlWrapper;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Created by xuemeng.wang on 2014/7/29.
 * @goal spoon
 */
public class SpoonMojo extends AbstractMojo {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException
    {
        TestHtmlWrapper.wrapTestHtml();
    }
}
