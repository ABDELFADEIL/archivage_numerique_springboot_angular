package com.archive.dto.in;


import com.archive.entity.RoleEntity;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;




@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class UserDtoIn implements Serializable {

    private String lastName;
    private String firstName;
    private String UID;
    private String password;
    private String email;
    private String rolename;


}
