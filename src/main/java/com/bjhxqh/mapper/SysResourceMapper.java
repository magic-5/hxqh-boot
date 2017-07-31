package com.bjhxqh.mapper;

import com.bjhxqh.common.base.mapper.MyMapper;
import com.bjhxqh.model.po.SysResource;

import java.util.ArrayList;
import java.util.List;

public interface SysResourceMapper extends MyMapper<SysResource> {
    ArrayList<SysResource> findResourceByUserId(String account);
    ArrayList<SysResource> findParentMenu();
}