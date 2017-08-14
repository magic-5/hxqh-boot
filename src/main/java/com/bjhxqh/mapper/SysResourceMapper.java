package com.bjhxqh.mapper;

import com.bjhxqh.common.base.mapper.MyMapper;
import com.bjhxqh.model.po.SysResource;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface SysResourceMapper extends MyMapper<SysResource> {
    ArrayList<SysResource> findResourceByUserId(String account);
    ArrayList<SysResource> findParentResource(String account);
    ArrayList<SysResource> findSecondaryMenuByUserAndParentId(@Param("account")String account, @Param("parent")int parent);
    ArrayList<SysResource> findResourceByUserAndParentId(@Param("account")String account, @Param("parent")int parent);
}