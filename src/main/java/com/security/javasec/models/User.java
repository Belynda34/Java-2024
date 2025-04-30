package com.security.javasec.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.security.javasec.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name="username",length = 10)
    private String username;
    @Email(message = "Invalid email")
    @Column(nullable = false,unique=true)
    private String email;
    @JsonIgnore
    private String password ;


    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
}




//@Pattern(regexp = "^[A-Z][a-z]*$", message = "Name must start with a capital letter and contain only letters")
//-- For MySQL
//        ALTER TABLE users DROP INDEX UKr43af9ap4edm43mmtq01oddj6; for being unique
