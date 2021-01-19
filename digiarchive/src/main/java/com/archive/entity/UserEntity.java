package com.archive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the user database table.
 */
@Entity
@Table(name = "user")
@Data
@AllArgsConstructor @NoArgsConstructor
public class UserEntity extends AbstractEntity{
    /*
     * fields
     */
    @Column(name = "lastName", length = 55, nullable = false)
    private String lastName;
    @Column(name = "firstName", length = 55, nullable = false)
    private String firstName;
    @Column(name = "uid", length = 55, nullable = false, unique = true)
    private String UID;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", length = 55, nullable = false, unique = true)
    private String email;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id") })
    private Set<RoleEntity> roles = new HashSet<>();

    // @ManyToMany(cascade = CascadeType.ALL)
    //	@JoinTable(name = "ltorder_has_quantity", joinColumns = {
    //			@JoinColumn(name = "order_id", nullable = false) }, inverseJoinColumns = {
    //					@JoinColumn(name = "quantity_id", nullable = false) })



    public UserEntity(Integer id, String lastName, String firstName, String UID, String password, String email, Set<RoleEntity> roles) {
        super(id);
        this.lastName = lastName;
        this.firstName = firstName;
        this.UID = UID;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
}
