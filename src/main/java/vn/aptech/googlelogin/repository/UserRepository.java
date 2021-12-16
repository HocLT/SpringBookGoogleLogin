/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.googlelogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.aptech.googlelogin.entity.AppUser;

/**
 *
 * @author quang
 */
public interface UserRepository extends JpaRepository<AppUser, Integer>{
    AppUser findByUsername(String username);
}
