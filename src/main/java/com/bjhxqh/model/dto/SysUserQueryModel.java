
package com.bjhxqh.model.dto;


import com.bjhxqh.common.base.model.QueryModel;
import com.bjhxqh.model.po.SysUser;

public class SysUserQueryModel extends QueryModel {

    private SysUser user;

    public SysUserQueryModel() {
        this(new SysUser());
    }

    public SysUserQueryModel(SysUser user) {
        this.user = user;
    }

    public Integer getId() {
        return user.getId();
    }

    public void setId(Integer id) {
        user.setId(id);
    }

    public String getNmae() {
        return user.getName();
    }

    public void setNmae(String name) {
        user.setName(name);
    }

    public String getAddress() {
        return user.getAddress();
    }

    public void setAddress(String address) {
        user.setAddress(address);
    }
}