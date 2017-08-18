package com.bjhxqh.service.impl;

import com.bjhxqh.common.base.service.ServiceSupport;
import com.bjhxqh.mapper.SysUserMapper;
import com.bjhxqh.model.po.SysUser;
import com.bjhxqh.service.SysUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-07-26.
 */
@Service
public class SysUserServiceImpl extends ServiceSupport<SysUser> implements SysUserService {

    @Override
    public SysUser getUserByAccount(String account){
        SysUserMapper sumap = (SysUserMapper)getMapper();
        return sumap.findUserbyAccount(account);
    }

    @Override
    public List<SysUser> selectByUser(SysUser user, int page, int rows) {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(user.getNumber())) {
            criteria.andLike("number", "%" + user.getNumber() + "%");
        }
        if (StringUtil.isNotEmpty(user.getName())) {
            criteria.andLike("name", "%" + user.getName() + "%");
        }
        if (user.getId() != null) {
            criteria.andEqualTo("id", user.getId());
        }
        //分页查询
        PageHelper.startPage(page, rows);
        return selectByExample(example);
    }
}
