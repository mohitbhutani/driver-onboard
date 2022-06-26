package com.intuit.driveronboard.service;

import java.util.ArrayList;
import java.util.List;

import com.intuit.driveronboard.dao.DriverRepository;
import com.intuit.driveronboard.model.CurrentUser;
import com.intuit.driveronboard.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private DriverRepository driverRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Driver driver = driverRepository.findByEmail(username);

        if (driver != null && driver.getEmail() != null) {
            return buildUserForAuthentication(driver, new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private User buildUserForAuthentication(Driver user,
                                            List<GrantedAuthority> authorities) {
        String username = user.getEmail();
        String password = user.getPassword();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        CurrentUser currentUser =  new CurrentUser(username, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setId(user.getId());
        return currentUser;
    }
}
