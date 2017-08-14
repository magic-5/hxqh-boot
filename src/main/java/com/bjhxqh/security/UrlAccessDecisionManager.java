package com.bjhxqh.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by fuyg on 2017-07-21.
 */
@Service
public class UrlAccessDecisionManager implements AccessDecisionManager {

    //decide 方法是判定是否拥有权限的决策方法
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String url, method;
        if ("anonymousUser".equals(authentication.getPrincipal())
                || matchers("/static/imgs/**", request)
                || matchers("/static/js/**", request)
                || matchers("/static/css/**", request)
                || matchers("/", request)
                || matchers("/index", request)
                || matchers("/index/**", request)
                || matchers("/login", request)
                || matchers("/logout", request)
                || matchers("/druid/**", request)
                || matchers("/service/**", request)
                || matchers("/activiti/**", request)
                || matchers("/static/activiti/**", request)
                || matchers("/favicon.ico", request)) {
            return;
        } else {
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (ga instanceof UrlGrantedAuthority) {
                    UrlGrantedAuthority urlGrantedAuthority = (UrlGrantedAuthority) ga;
                    url = urlGrantedAuthority.getPermissionUrl();
                    method = urlGrantedAuthority.getMethod();
                    if (matchers(url, request)) {
                        if (method.equals(request.getMethod()) || "ALL".equals(method)) {
                            return;
                        }
                    }
                }
            }
        }
        throw new AccessDeniedException("no right");
    }



    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    private boolean matchers(String url, HttpServletRequest request) {
        AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
        if (matcher.matches(request)) {
            return true;
        }
        return false;
    }
}
