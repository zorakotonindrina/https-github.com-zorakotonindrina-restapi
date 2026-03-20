package mg.votretp.restapi.controller;

import mg.votretp.restapi.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {
    @Autowired
    private JwtService jwtService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring Boot API";
    }

    @GetMapping("/profile")
    public String getProfile(@RequestHeader("Authorization") String authHeader){

        String token = authHeader.substring(7); // enlever "Bearer "

        String email = jwtService.extractEmail(token);

        return email;
    }
}