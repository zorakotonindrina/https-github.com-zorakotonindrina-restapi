package mg.votretp.restapi.controller;

import mg.votretp.restapi.dto.AuthResponseDTO;
import mg.votretp.restapi.dto.LoginDTO;
import mg.votretp.restapi.dto.RefreshTokenRequestDTO;
import mg.votretp.restapi.dto.RegisterDTO;
import mg.votretp.restapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Authentification", description = "Endpoints liés à l'authentification")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Connexion utilisateur")
    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginDTO dto) {
        return authService.login(dto.getNumero(), dto.getPassword());
    }
    @Operation(summary = "Inscription utilisateur")
    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO dto) {
        return authService.register(dto);
    }

    @Operation(summary = "Rafraichir le token")
    @PostMapping("/refresh")
    public AuthResponseDTO refresh(@RequestBody RefreshTokenRequestDTO dto) {
        return authService.refreshToken(dto);
    }
}