package com.zadaca.events.services;

import com.zadaca.events.domains.*;
import com.zadaca.events.enums.ERole;
import com.zadaca.events.payload.response.MessageResponse;
import com.zadaca.events.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;
    @Autowired
    PasswordEncoder encoder;

    public AdminServiceImpl(UserRepository userRepository,
                            RoleRepository roleRepository, RoleRepository roleRepository1, LocationRepository locationRepository, CategoryRepository categoryRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository1;
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public ResponseEntity<?> banUser(Long id){
        if(!this.userRepository.existsById(id)){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: User with entered ID does not exist!"));
        }
        User existingUser = this.userRepository.findById(id).orElseThrow();
        if(existingUser.getBanned()){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: User is already banned!"));
        }
        existingUser.setBanned(true);
        this.userRepository.save(existingUser);
        return ResponseEntity.ok()
                .body(new MessageResponse("User with id: " + id + " successfully banned!"));
    }
    @Override
    public ResponseEntity<?> changePassword(Long id, String newPassword){

        User user = this.userRepository.findById(id).orElseThrow();
        user.setPassword(encoder.encode(newPassword));
        this.userRepository.save(user);
        return ResponseEntity.ok()
                .body(new MessageResponse("Password changes successfully!"));
    }

    @Override
    public void populateDB() {
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName(ERole.ROLE_ADMIN);
        this.roleRepository.save(role);
        roles.add(role);
        User user = new User("admin", "admin", "admin", "admin@gmail.com", encoder.encode("admin1234"));
        user.setRoles(roles);
        this.userRepository.save(user);

        role = new Role();
        role.setName(ERole.ROLE_USER);
        roles = new HashSet<>();
        roles.add(role);
        this.roleRepository.save(role);
        user = new User("pasha", "amer", "pasanbegovic", "amer@gmail.com", encoder.encode("test1234"));
        user.setRoles(roles);
        this.userRepository.save(user);
        user = new User("mujo", "mujo", "mujic", "mujo@gmail.com", encoder.encode("test1234"));
        user.setRoles(roles);
        this.userRepository.save(user);

        Location location = new Location("Zenica", "Random opis", "zamislitCemoDaJeOvoImageUrl");
        locationRepository.save(location);
        Category category = new Category("Termin", "zamislitCemoDaJeOvoIconUrl");
        categoryRepository.save(category);
        Event event = new Event("Termin fudbala", LocalDateTime.of(2023, 1, 20, 20, 0, 0), "Neki opis", "zamislitCemoDaJeOvoImageUrl", location, category);
        eventRepository.save(event);

        location = new Location("Sarajevo", "Random opis", "zamislitCemoDaJeOvoImageUrl");
        locationRepository.save(location);
        category = new Category("Koncert", "zamislitCemoDaJeOvoIconUrl");
        categoryRepository.save(category);
        event = new Event("Dino Merlin", LocalDateTime.of(2023, 12, 20, 20, 0, 0), "Neki opis", "zamislitCemoDaJeOvoImageUrl", location, category);
        eventRepository.save(event);

        location = new Location("Mostar", "Random opis", "zamislitCemoDaJeOvoImageUrl");
        locationRepository.save(location);
        category = new Category("Predavanje", "zamislitCemoDaJeOvoIconUrl");
        categoryRepository.save(category);
        event = new Event("IDK", LocalDateTime.of(2023, 6, 20, 20, 0, 0), "Neki opis", "zamislitCemoDaJeOvoImageUrl", location, category);
        eventRepository.save(event);
    }
}
