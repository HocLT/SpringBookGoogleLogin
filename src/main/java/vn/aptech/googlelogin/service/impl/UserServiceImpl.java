/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.googlelogin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.aptech.googlelogin.entity.AppRole;
import vn.aptech.googlelogin.entity.AppUser;
import vn.aptech.googlelogin.repository.RoleRepository;
import vn.aptech.googlelogin.repository.UserRepository;
import vn.aptech.googlelogin.service.UserService;

/**
 *
 * @author quang
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService{
    @Autowired
    private UserRepository repo;
    
    @Autowired
    private RoleRepository repoRole;
    
    
    @Override
    public List<AppUser> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<AppUser> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public AppUser save(AppUser user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setProvider("LOCAL");
        return repo.save(user);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // tìm user theo username
        AppUser user = repo.findByUsername(username);
        
        // nếu không tìm thấy, trả về User not found
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        
        // chạy đến đoạn này => tìm thấy
        // get role by username
        List<AppRole> roles = repoRole.findRolesByUsername(username);
        // spring quản lý các roles bằng GrantedAuthority
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roles != null && roles.size() > 0) {
            // trường hợp user có role
            for(AppRole r: roles) {
                // tạo đối tượng GrantedAuthority theo role
                GrantedAuthority authority = new SimpleGrantedAuthority(r.getName());
                grantList.add(authority);
            }
        } else {
            // cấp cho user này role USER
            grantList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        
        boolean enabled = user.isEnabled();
        boolean accountNonExpired = true;
        boolean credentialNonExpried = true;
        boolean accountNonLocked = true;
        
        return new User(user.getUsername(), user.getPassword(), enabled,
                accountNonExpired, credentialNonExpried, 
                accountNonLocked, grantList);
    }
    
    // sau khi login bằng google account, 
    // chúng ta sẽ tạo 1 tài khoản local tương ứng để lưu thông tin
    public void processOAuthPostLogin(String username) {
        AppUser user = repo.findByUsername(username);
        // trường hợp chưa có tài khoản => tạo mới
        if (user == null) {
            AppUser u = new AppUser();
            // thiếu phần setId();
            u.setUsername(username);
            u.setProvider("GOOGLE");
            u.setEnabled(true);
            
            repo.save(u);
        }
    }
}
