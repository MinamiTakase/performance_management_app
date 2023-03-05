package com.app.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.domain.MST_User;

public class LoginUserDetails implements UserDetails {
	
//	以下３行加筆？
//    private String username;
//    private String password;
//    private Collection<GrantedAuthority> grantedAuthorities;
    
    private final MST_User loginUserForm;
    private final Collection<? extends GrantedAuthority> authorities;

    public LoginUserDetails(MST_User loginUserForm) {
        this.loginUserForm = loginUserForm; //username&passwordの入っている箱。個別に設定しなくてよい？
        this.authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return loginUserForm.getPassword();
    }

    @Override
    public String getUsername() {
        return loginUserForm.getUser_address(); //書き換え
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
