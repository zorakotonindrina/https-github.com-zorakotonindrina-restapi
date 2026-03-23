package mg.votretp.restapi.config;

import mg.votretp.restapi.model.*;
import mg.votretp.restapi.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(RoleRepository roleRepository,
                               UserRepository userRepository,
                               RestaurantRepository restaurantRepository,
                               CategoryRepository categoryRepository,
                               PlatRepository platRepository,
                               PrixPlatRepository prixPlatRepository,
                               PasswordEncoder passwordEncoder) {
        return args -> {

            // =========================
            // ROLES
            // =========================
            Role adminRole = roleRepository.findByName("ADMIN").orElseGet(() -> {
                Role role = new Role();
                role.setName("ADMIN");
                return roleRepository.save(role);
            });

            Role customerRole = roleRepository.findByName("CUSTOMER").orElseGet(() -> {
                Role role = new Role();
                role.setName("CUSTOMER");
                return roleRepository.save(role);
            });

            Role ownerRole = roleRepository.findByName("RESTAURANT_OWNER").orElseGet(() -> {
                Role role = new Role();
                role.setName("RESTAURANT_OWNER");
                return roleRepository.save(role);
            });

            // =========================
            // ADMIN USER
            // =========================
            User admin = userRepository.findByNumero("0320000000").orElseGet(() -> {
                User user = new User();
                user.setNom("Admin");
                user.setPrenom("Root");
                user.setNumero("0320000000");
                user.setMdp(passwordEncoder.encode("123456"));
                user.setRole(adminRole);
                return userRepository.save(user);
            });

            // =========================
            // RESTAURANT UNIQUE
            // =========================
            Restaurant restaurant = restaurantRepository.findAll().stream().findFirst().orElseGet(() -> {
                Restaurant r = new Restaurant();
                r.setNom("Mon Restaurant");
                r.setLogo("logo.png");
                return restaurantRepository.save(r);
            });

            // =========================
            // CATEGORIES
            // =========================
            Category pizza = categoryRepository.findByNom("Pizza").orElseGet(() -> {
                Category c = new Category();
                c.setNom("Pizza");
                return categoryRepository.save(c);
            });

            Category burger = categoryRepository.findByNom("Burger").orElseGet(() -> {
                Category c = new Category();
                c.setNom("Burger");
                return categoryRepository.save(c);
            });

            Category boisson = categoryRepository.findByNom("Boisson").orElseGet(() -> {
                Category c = new Category();
                c.setNom("Boisson");
                return categoryRepository.save(c);
            });

            // =========================
            // PLATS
            // =========================
            Plat pizzaRoyale = platRepository.findByNom("Pizza Royale").orElseGet(() -> {
                Plat p = new Plat();
                p.setNom("Pizza Royale");
                p.setImage("b08c8689-2103-4f37-897d-64160220dacd.jpg");
                p.setDetail("Pizza avec jambon et fromage");
                p.setDispo(true);
                p.setCategory(pizza);
                p.setRestaurant(restaurant);
                return platRepository.save(p);
            });

            Plat burgerMaison = platRepository.findByNom("Burger Maison").orElseGet(() -> {
                Plat p = new Plat();
                p.setNom("Burger Maison");
                p.setImage("burger_maison.jpg");
                p.setDetail("Burger maison avec steak, fromage et frites");
                p.setDispo(true);
                p.setCategory(burger);
                p.setRestaurant(restaurant);
                return platRepository.save(p);
            });

            Plat coca = platRepository.findByNom("Coca-Cola").orElseGet(() -> {
                Plat p = new Plat();
                p.setNom("Coca-Cola");
                p.setImage("coca.jpg");
                p.setDetail("Boisson fraîche 50cl");
                p.setDispo(true);
                p.setCategory(boisson);
                p.setRestaurant(restaurant);
                return platRepository.save(p);
            });

            // =========================
            // PRIX DES PLATS
            // =========================
            if (prixPlatRepository.findTopByPlat_IdPlatOrderByDatePrixDesc(pizzaRoyale.getIdPlat()).isEmpty()) {
                PrixPlat prix = new PrixPlat();
                prix.setPlat(pizzaRoyale);
                prix.setPrix(new BigDecimal("18000"));
                prixPlatRepository.save(prix);
            }

            if (prixPlatRepository.findTopByPlat_IdPlatOrderByDatePrixDesc(burgerMaison.getIdPlat()).isEmpty()) {
                PrixPlat prix = new PrixPlat();
                prix.setPlat(burgerMaison);
                prix.setPrix(new BigDecimal("15000"));
                prixPlatRepository.save(prix);
            }

            if (prixPlatRepository.findTopByPlat_IdPlatOrderByDatePrixDesc(coca.getIdPlat()).isEmpty()) {
                PrixPlat prix = new PrixPlat();
                prix.setPlat(coca);
                prix.setPrix(new BigDecimal("5000"));
                prixPlatRepository.save(prix);
            }
        };
    }
}