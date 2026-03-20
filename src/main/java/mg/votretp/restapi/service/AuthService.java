package mg.votretp.restapi.service;

import mg.votretp.restapi.dto.AuthResponseDTO;
import mg.votretp.restapi.dto.RefreshTokenRequestDTO;
import mg.votretp.restapi.dto.RegisterDTO;
import mg.votretp.restapi.model.Role;
import mg.votretp.restapi.model.User;
import mg.votretp.restapi.repository.RoleRepository;
import mg.votretp.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponseDTO login(String email, String password) {
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        String accessToken = jwtService.generateAccessToken(
                user.getEmail(),
                user.getRole().getName()
        );

        String refreshToken = jwtService.generateRefreshToken(
                user.getEmail(),
                user.getRole().getName()
        );

        return new AuthResponseDTO(accessToken, refreshToken, "Connexion réussie");
    }

    public String register(RegisterDTO dto) {
        if (repo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Cet email existe déjà");
        }

        Role role = roleRepository.findByName(dto.getRoleName().toUpperCase())
                .orElseThrow(() -> new RuntimeException("Rôle introuvable"));

        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(role);

        repo.save(user);

        return "Inscription réussie";
    }

    public AuthResponseDTO refreshToken(RefreshTokenRequestDTO dto) {
        String refreshToken = dto.getRefreshToken();

        if (refreshToken == null || refreshToken.isBlank()) {
            throw new RuntimeException("Refresh token manquant");
        }

        if (!jwtService.isRefreshToken(refreshToken)) {
            throw new RuntimeException("Refresh token invalide");
        }

        String email = jwtService.extractEmail(refreshToken);

        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        String newAccessToken = jwtService.generateAccessToken(
                user.getEmail(),
                user.getRole().getName()
        );

        String newRefreshToken = jwtService.generateRefreshToken(
                user.getEmail(),
                user.getRole().getName()
        );

        return new AuthResponseDTO(newAccessToken, newRefreshToken, "Token rafraîchi avec succès");
    }
}