/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.googlelogin.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.googlelogin.entity.AppRole;
import vn.aptech.googlelogin.repository.RoleRepository;
import vn.aptech.googlelogin.service.RoleService;

/**
 *
 * @author quang
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository repo;
    
    @Override
    public List<AppRole> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<AppRole> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public AppRole save(AppRole user) {
        return repo.save(user);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
