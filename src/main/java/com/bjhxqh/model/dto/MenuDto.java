package com.bjhxqh.model.dto;

import com.bjhxqh.model.po.SysResource;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-07-26.
 */
public class MenuDto {
    private String name;
    private ArrayList<SysResource> menuList = new ArrayList<SysResource>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SysResource> getMenuList() {
        return menuList;
    }

    public void setMenuList(ArrayList<SysResource> menuList) {
        this.menuList = menuList;
    }

    public void addMenu(SysResource sysResource){
        this.menuList.add(sysResource);
    }

    public int getMenuSize(){
        return this.menuList.size();
    }
}
