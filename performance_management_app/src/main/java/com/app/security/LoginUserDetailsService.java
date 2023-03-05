package com.app.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.domain.MST_User;
import com.app.mapper.LoginMapper;

@Service
public class LoginUserDetailsService implements UserDetailsService {

    private final LoginMapper loginMapper;
   
    //コンストラクタ
    public LoginUserDetailsService(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    @Override
    //usernameに引数として渡されている値は？
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //書き換え
    	
        Optional<MST_User> loginUserOptional = loginMapper.findByAccount(username); //書き換え
        return loginUserOptional.map(loginUserForm -> new LoginUserDetails(loginUserForm))
                .orElseThrow(() -> new UsernameNotFoundException("not found"));
    }
}
