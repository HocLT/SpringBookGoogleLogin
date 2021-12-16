/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.googlelogin.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.aptech.googlelogin.entity.AppRole;
import vn.aptech.googlelogin.entity.AppUser;
import vn.aptech.googlelogin.entity.UserRole;
import vn.aptech.googlelogin.repository.RoleRepository;

/**
 *
 * @author quang
 */
public class MyUserDetails implements UserDetails {

    @Autowired
    private RoleRepository repoRole; 
    
    private AppUser user;

    public MyUserDetails(AppUser user) {
        this.user = user;
    }
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<AppRole> roles = repoRole.findRolesByUsername(user.getUsername());
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roles != null && roles.size() > 0) {
            // trường hợp user có role
            for(AppRole r: roles) {
                // tạo đối tượng GrantedAuthority theo role
                GrantedAuthority authority = new SimpleGrantedAuthority(r.getName());
                grantList.add(authority);
            }
        }
        return grantList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
