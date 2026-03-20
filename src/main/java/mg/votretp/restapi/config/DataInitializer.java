package mg.votretp.restapi.config;


import mg.votretp.restapi.model.Role;
import mg.votretp.restapi.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {

            if (roleRepository.findByName("ADMIN").isEmpty()) {
                Role admin = new Role();
                admin.setName("ADMIN");
                roleRepository.save(admin);
            }

            if (roleRepository.findByName("CUSTOMER").isEmpty()) {
                Role customer = new Role();
                customer.setName("CUSTOMER");
                roleRepository.save(customer);
            }

            if (roleRepository.findByName("RESTAURANT_OWNER").isEmpty()) {
                Role owner = new Role();
                owner.setName("RESTAURANT_OWNER");
                roleRepository.save(owner);
            }
        };
    }
}