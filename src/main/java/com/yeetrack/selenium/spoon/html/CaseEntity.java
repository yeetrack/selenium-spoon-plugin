package com.yeetrack.selenium.spoon.html;

import java.util.List;

/**
 * Created by xuemeng.wang on 2014/7/28.\
 * 每个case执行情况
 */
public class CaseEntity
{
    private String caseName;
    private boolean isSuccess;
    List<String> screenShotsFileList;

    public List<String> getScreenShotsFileList() {
        return screenShotsFileList;
    }

    public void setScreenShotsFileList(List<String> screenShotsFileList) {
        this.screenShotsFileList = screenShotsFileList;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
