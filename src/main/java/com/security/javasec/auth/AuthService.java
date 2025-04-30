package com.security.javasec.auth;


import com.security.javasec.auth.exceptions.InvalidCredentialsException;
import com.security.javasec.auth.exceptions.EmailTakenException;
import com.security.javasec.enums.Role;
import com.security.javasec.models.User;
import com.security.javasec.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.security.javasec.config.JWTService;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthenticationResponse register(RegisterDTO dto){
        boolean emailTaken = userRepository.existsByEmail(dto.getEmail());
        if(emailTaken)
            throw new EmailTakenException("Email Taken");
        try{
            var user = new User();
            user.setEmail(dto.getEmail());
            user.setUsername(dto.getUsername());
            user.setRole(Role.USER);
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            userRepository.save(user);
            System.out.println(user.toString());

            var token = jwtService.generateToken(user);
            return new AuthenticationResponse(user.getUsername(),user.getEmail(),token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public AuthenticationResponse login(LoginDTO dto){
        System.out.println("Step 1");
        var user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow();
        var passwordsMatch = passwordEncoder.matches(dto.getPassword(), user.getPassword());
        if(!passwordsMatch)
            throw new InvalidCredentialsException("Invalid Credentials");
        String token = jwtService.generateToken(user);
        return  new AuthenticationResponse(user.getUsername(),user.getEmail(),token);
    }

}
