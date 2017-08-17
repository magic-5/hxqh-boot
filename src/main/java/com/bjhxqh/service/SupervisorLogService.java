package com.bjhxqh.service;

import com.bjhxqh.common.base.service.IService;
import com.bjhxqh.model.po.SysUser;
import com.bjhxqh.model.po.TbSuperviseLog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-07-26.
 */
@Service
public interface SupervisorLogService extends IService<TbSuperviseLog> {
    /**
     * 根据条件分页查询
     *
     * @param tslog
     * @param page
     * @param rows
     * @return
     */
    List<TbSuperviseLog> selectByLog(TbSuperviseLog tslog, int page, int rows);
}
