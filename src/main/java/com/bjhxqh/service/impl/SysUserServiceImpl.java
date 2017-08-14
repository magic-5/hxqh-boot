package com.bjhxqh.service.impl;

import com.bjhxqh.common.base.service.ServiceSupport;
import com.bjhxqh.mapper.SysUserMapper;
import com.bjhxqh.model.po.SysUser;
import com.bjhxqh.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-07-26.
 */
@Service
public class SysUserServiceImpl extends ServiceSupport<SysUser> implements SysUserService {

    public SysUser getUserByAccount(String account){
        SysUserMapper sumap = (SysUserMapper)getMapper();
        return sumap.findUserbyAccount(account);
    }
}
