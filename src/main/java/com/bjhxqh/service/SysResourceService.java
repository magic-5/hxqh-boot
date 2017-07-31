package com.bjhxqh.service;

import com.bjhxqh.common.base.service.IService;
import com.bjhxqh.model.dto.MenuDto;
import com.bjhxqh.model.po.SysResource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-07-26.
 */
@Service
public interface SysResourceService extends IService<SysResource> {
    public List<MenuDto> getMenuByAccount(String account);
}
