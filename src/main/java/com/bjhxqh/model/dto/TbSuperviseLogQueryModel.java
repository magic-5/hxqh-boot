
package com.bjhxqh.model.dto;

import com.bjhxqh.common.base.model.QueryModel;
import com.bjhxqh.model.po.TbSuperviseLog;

public class TbSuperviseLogQueryModel extends QueryModel {

    private TbSuperviseLog tslog;

    public TbSuperviseLogQueryModel() {
        this(new TbSuperviseLog());
    }

    public TbSuperviseLogQueryModel(TbSuperviseLog tslog) {
        this.tslog = tslog;
    }

    public Integer getId() {
        return tslog.getId();
    }

    public void setId(Integer id) {
        tslog.setId(id);
    }

    public String getProjectnum() {
        return tslog.getProjectnum();
    }

    public void setProjectnum(String projectnum) {
        tslog.setProjectnum(projectnum);
    }

    public String getProjectname() {
        return tslog.getProjectname();
    }

    public void setProjectname(String projectname) {
        tslog.setProjectname(projectname);
    }
}