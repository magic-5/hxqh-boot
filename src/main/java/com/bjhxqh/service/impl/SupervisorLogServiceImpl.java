package com.bjhxqh.service.impl;

import com.bjhxqh.common.base.service.ServiceSupport;
import com.bjhxqh.mapper.SysUserMapper;
import com.bjhxqh.model.po.SysUser;
import com.bjhxqh.model.po.TbSuperviseLog;
import com.bjhxqh.service.SupervisorLogService;
import com.bjhxqh.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by Administrator on 2017-07-26.
 */
@Service
public class SupervisorLogServiceImpl extends ServiceSupport<TbSuperviseLog> implements SupervisorLogService {

    @Override
    public List<TbSuperviseLog> selectByLog(TbSuperviseLog tslog, int page, int rows) {
        Example example = new Example(TbSuperviseLog.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(tslog.getProjectnum())) {
            criteria.andLike("projectnum", "%" + tslog.getProjectnum() + "%");
        }
        if (StringUtil.isNotEmpty(tslog.getProjectname())) {
            criteria.andLike("projectname", "%" + tslog.getProjectname() + "%");
        }
        if (tslog.getId() != null) {
            criteria.andEqualTo("id", tslog.getId());
        }
        //分页查询
        PageHelper.startPage(page, rows);
        return selectByExample(example);
    }
}
