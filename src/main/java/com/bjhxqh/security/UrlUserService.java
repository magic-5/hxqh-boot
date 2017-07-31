package com.bjhxqh.security;

import com.bjhxqh.mapper.SysAccountMapper;
import com.bjhxqh.mapper.SysResourceMapper;
import com.bjhxqh.model.po.SysAccount;
import com.bjhxqh.model.po.SysResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuyg on 2017-07-21.
 */
@Service
public class UrlUserService implements UserDetailsService { //自定义UserDetailsService 接口

    @Autowired
    SysAccountMapper accountMapper;
    @Autowired
    SysResourceMapper sysResourceMapper;

    public UserDetails loadUserByUsername(String userName) {
        SysAccount account = new SysAccount();
        account.setAccount(userName);
        account = accountMapper.selectOne(account);
        if (account != null) {
            List<SysResource> resources = sysResourceMapper.findResourceByUserId(account.getAccount());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (SysResource resource : resources) {
                if (resource != null && resource.getName() != null) {
                    GrantedAuthority grantedAuthority = new UrlGrantedAuthority(resource.getUrl(), resource.getMethod());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(account.getAccount(), account.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + userName + " do not exist!");
        }
    }
}
