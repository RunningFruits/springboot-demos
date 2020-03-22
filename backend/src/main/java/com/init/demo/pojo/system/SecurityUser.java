package com.init.demo.pojo.system;

import com.init.demo.entity.system.SystemUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class SecurityUser implements UserDetails {

    private String id;
    private String username; // 对应loginName
    private String realName; // 对应userName
    private String password;



    public SecurityUser(SystemUser systemUser) {
        this.id = systemUser.getId();
        this.username = systemUser.getLoginName();
        this.realName = systemUser.getUserName();
        this.password = systemUser.getPassword();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String username = this.getUsername();
        if (username != null) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(username);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

