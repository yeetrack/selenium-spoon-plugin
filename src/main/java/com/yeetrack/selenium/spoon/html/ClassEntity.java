package com.yeetrack.selenium.spoon.html;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuemeng.wang on 2014/7/28.
 * 在test包中，以类为维度进行统计
 */
public class ClassEntity
{
    private String className;
    List<CaseEntity> caseList;

    public ClassEntity() { caseList = new ArrayList<CaseEntity>(); }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    public List<CaseEntity> getCaseList() {
        return caseList;
    }

    public void setCaseList(List<CaseEntity> caseNameList) {
        this.caseList = caseNameList;
    }
}
