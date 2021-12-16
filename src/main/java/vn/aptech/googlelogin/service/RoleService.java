/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.googlelogin.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.googlelogin.entity.AppRole;

/**
 *
 * @author quang
 */
public interface RoleService {

    List<AppRole> findAll();
    
    Optional<AppRole> findById(int id);
    
    AppRole save(AppRole user);
    
    void deleteById(int id);
}
