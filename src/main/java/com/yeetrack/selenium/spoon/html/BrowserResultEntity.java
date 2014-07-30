package com.yeetrack.selenium.spoon.html;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuemeng.wang on 2014/7/28.
 */
public class BrowserResultEntity
{
    private String browserName;
    private int caseCount;
    private int successCount;
    private int failCount;
    private List<ClassEntity> classList;

    public BrowserResultEntity() { classList = new ArrayList<ClassEntity>(); }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public int getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(int caseCount) {
        this.caseCount = caseCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public List<ClassEntity> getClassList() {
        return classList;
    }

    public void setClassList(List<ClassEntity> classList) {
        this.classList = classList;
    }
}
