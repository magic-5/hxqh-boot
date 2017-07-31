package com.bjhxqh.service;

import com.bjhxqh.common.base.service.IService;
import com.bjhxqh.model.po.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-07-17.
 */
@Service
public interface UserInfoService extends IService<UserInfo> {

    public int testInterFace();

    public UserInfo testUser();

    public int insertUser(UserInfo userinfo);

    //新增加的方法
    List<UserInfo> selectALL();
}
