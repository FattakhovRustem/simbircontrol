package com.simbirsoft.simbircontrol.security;

import com.simbirsoft.simbircontrol.entity.Usr;
import com.simbirsoft.simbircontrol.enums.Role;
import com.simbirsoft.simbircontrol.exception.NoEntityException;
import com.simbirsoft.simbircontrol.repository.UsrRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsrRepository usrRepository;

    public UserDetailsServiceImpl(UsrRepository usrRepository) {
        this.usrRepository = usrRepository;
    }

    /**
     * find user with login
     * @param s login user
     * @return UserDetails for work with spring security
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usr usr = usrRepository.findByLogin(s).orElseThrow(() -> new NoEntityException(String.format(ResourceBundle.getBundle("resource").getString("userLoginNotFound"), s)));
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(usr.getRole().name()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                usr.getLogin(), usr.getPassword(), roles
        );
        return userDetails;
    }
}
