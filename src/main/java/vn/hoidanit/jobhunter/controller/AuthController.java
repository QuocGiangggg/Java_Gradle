package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.hoidanit.jobhunter.domain.dto.LoginDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import jakarta.validation.Valid;


@RestController
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }        

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@Valid @RequestBody LoginDTO loginDTO) {

        UsernamePasswordAuthenticationToken authenticationToken = 
        new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), 
                loginDTO.getPassword()
            );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return ResponseEntity.ok().body(loginDTO);
    }
}
