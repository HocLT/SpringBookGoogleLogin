/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.googlelogin.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author quang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserRole")
public class UserRole implements Serializable{
    @Id
    @Column(name = "Id")
    private int id;
    
    // mô tả tham chiếu về AppUser => quan hệ 1
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    private AppUser user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RoleId", referencedColumnName = "Id")
    private AppRole role;
}