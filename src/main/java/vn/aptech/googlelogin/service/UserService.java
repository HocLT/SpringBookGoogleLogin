/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.googlelogin.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.googlelogin.entity.AppUser;

/**
 *
 * @author quang
 */
public interface UserService {
    
    List<AppUser> findAll();
    
    Optional<AppUser> findById(int id);
    
    AppUser save(AppUser user);
    
    void deleteById(int id);
}
