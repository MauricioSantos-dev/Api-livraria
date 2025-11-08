package com.uninassau.livraria_api.controllers;


import com.uninassau.livraria_api.dto.AuthenticationDTO;
import com.uninassau.livraria_api.dto.LoginResponseDTO;
import com.uninassau.livraria_api.dto.RegisterDTO;
import com.uninassau.livraria_api.entities.User;
import com.uninassau.livraria_api.infra.security.TokenService;
import com.uninassau.livraria_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {
        var passwordUser = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var   auth = authenticationManager.authenticate(passwordUser);

        var token= tokenService.generateToken((User)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data) {
        if(userRepository.findByEmail(data.email()) != null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(),data.email(), encryptedPassword, data.role() );
        userRepository.save(newUser);
        return ResponseEntity.ok().build();

    }


}
