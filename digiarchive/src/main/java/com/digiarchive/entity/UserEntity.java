package com.digiarchive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

/**
 * The persistent class for the user database table.
 */
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity extends AbstractEntity{
    /*
     * fields
     */
    @Column(name = "lastName", length = 55, nullable = false)
    private String lastName;
    @Column(name = "firstName", length = 55, nullable = false)
    private String firstName;
    @Column(name = "uid", length = 55, nullable = false)
    private String UID;
    @Column(name = "password", length = 55, nullable = false)
    private String password;
    @Column(name = "email", length = 55, nullable = false)
    private String email;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_has_role", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id") })
    private Set<RoleEntity> roles;

}
