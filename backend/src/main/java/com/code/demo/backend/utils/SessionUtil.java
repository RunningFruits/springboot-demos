package com.code.demo.backend.utils;

import com.code.demo.backend.pojo.system.SecurityUser;
import com.code.demo.backend.entity.system.SystemUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SessionUtil {

    public SystemUser getCurrentUser() { //为了session从获取用户信息,可以配置如下
        SecurityUser user;
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        if (auth != null) {
            if (auth.getPrincipal() instanceof UserDetails) {
                user = (SecurityUser) auth.getPrincipal();
                SystemUser systemUser = new SystemUser();
                systemUser.setId(user.getId());
                systemUser.setLoginName(user.getUsername());
                systemUser.setUserName(user.getRealName());
                systemUser.setPassword(user.getPassword());
                return systemUser;
            }
        }
        return null;
    }
}
