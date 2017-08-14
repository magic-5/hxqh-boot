package com.bjhxqh.service.impl;

import com.bjhxqh.common.base.service.ServiceSupport;
import com.bjhxqh.mapper.SysResourceMapper;
import com.bjhxqh.model.dto.MenuDto;
import com.bjhxqh.model.po.SysResource;
import com.bjhxqh.service.SysResourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-07-26.
 */
@Service
public class SysResourceServiceImpl extends ServiceSupport<SysResource> implements SysResourceService {

    public List<SysResource> getParentResources(String account){
        SysResourceMapper srmap = (SysResourceMapper)getMapper();
        return srmap.findParentResource(account);
    }

    public List<MenuDto> getMenuByAccountAndParentId(String account,int parent,String parentName){
        ArrayList<MenuDto> arr = new ArrayList<MenuDto>();
        SysResourceMapper srmap = (SysResourceMapper)getMapper();
        ArrayList<SysResource> al = srmap.findSecondaryMenuByUserAndParentId(account,parent);
        ArrayList<SysResource> resourceList = srmap.findResourceByUserAndParentId(account,parent);
        for(int i=0;i<al.size();i++){
            int parentId = al.get(i).getId();
            String menuName = al.get(i).getName();
            MenuDto menudto = new MenuDto();
            menudto.setName(menuName);
            //menudto.setParentName(parentName);
            for(int j=0;j<resourceList.size();j++){
                SysResource sysResource = resourceList.get(j);
                int parentValue = sysResource.getParent();
                if(parentValue!=0&&parentValue==parentId){
                    menudto.addMenu(sysResource);
                }
            }
            if(menudto.getMenuSize()>0){
                arr.add(menudto);
            }
        }
        return  arr;
    }
}
