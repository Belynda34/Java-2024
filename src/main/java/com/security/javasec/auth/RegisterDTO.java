package com.security.javasec.auth;


import com.security.javasec.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String username;
    private String email;
    private String password;
    private Role role;
}
