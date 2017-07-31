package com.bjhxqh.service.impl;

import com.bjhxqh.common.base.service.ServiceSupport;
import com.bjhxqh.model.po.UserInfo;
import com.bjhxqh.service.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-07-17.
 */
@Service("UserInfoService")
public class UserInfoServiceImpl extends ServiceSupport<UserInfo> implements UserInfoService {

    @Override
    public int testInterFace() {
        return 0;
    }

    @Override
    public UserInfo testUser() {
        return new UserInfo();
    }

    @Override
    public int insertUser(UserInfo userinfo) {
        return save(userinfo);
    }

    public UserInfo getUser(String name){
        UserInfo user = new UserInfo();
        user.setUsername(name);
        return selectByEntity(user);
    }
    //新增加的实现
    @Override
    public List<UserInfo> selectALL(){
        return selectAll();
    }
}
