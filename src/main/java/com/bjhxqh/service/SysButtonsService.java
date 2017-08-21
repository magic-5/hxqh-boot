package com.bjhxqh.service;

import com.bjhxqh.common.base.service.IService;
import com.bjhxqh.model.po.SysButtons;
import com.bjhxqh.model.po.SysRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-07-26.
 */
@Service
public interface SysButtonsService extends IService<SysButtons> {

    /**
     * 根据条件分页查询
     *
     * @param button
     * @return
     */
    List<SysButtons> selectByButton(SysButtons button);
}
