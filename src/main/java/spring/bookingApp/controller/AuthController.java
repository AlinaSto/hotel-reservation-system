package spring.bookingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.bookingApp.dto.AuthDTO;
import spring.bookingApp.dto.RegisterDTO;
import spring.bookingApp.model.User;
import spring.bookingApp.service.JwtTokenService;
import spring.bookingApp.service.UserDetailsServiceImpl;
import spring.bookingApp.service.UserService;

@RestController
public class AuthController {


    private JwtTokenService jwtTokenService;
    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsService;
    private UserService userService;

    @Autowired
    public AuthController(JwtTokenService jwtTokenService, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, UserService userService) {
        this.jwtTokenService = jwtTokenService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthDTO user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        return jwtTokenService.generateToken(userDetails);
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterDTO newUser) {
        return userService.register(newUser);
    }
}