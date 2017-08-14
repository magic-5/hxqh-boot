package com.bjhxqh.mapper;

import com.bjhxqh.common.base.mapper.MyMapper;
import com.bjhxqh.model.po.SysUser;

import java.util.ArrayList;
import java.util.List;

public interface SysUserMapper extends MyMapper<SysUser> {
    SysUser findUserbyAccount(String account);
}