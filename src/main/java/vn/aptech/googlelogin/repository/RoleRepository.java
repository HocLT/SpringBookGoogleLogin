/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.googlelogin.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.googlelogin.entity.AppRole;

/**
 *
 * @author quang
 */
public interface RoleRepository extends JpaRepository<AppRole, Integer>{
    @Query("SELECT o.role FROM UserRole o WHERE o.user.username=:username")
    List<AppRole> findRolesByUsername(@Param("username") String username);

}
