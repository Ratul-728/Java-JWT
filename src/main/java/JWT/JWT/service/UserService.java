package JWT.JWT.service;

import JWT.JWT.domain.Role;
import JWT.JWT.domain.User;
import JWT.JWT.repository.RoleRepository;
import JWT.JWT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public ResponseEntity<?> saveUser(User user){
        log.info("Saving new user {} to the database", user.getName());
        userRepository.save(user);
        return ResponseEntity.ok("User add successfully");
    }
    public ResponseEntity<?> getUser(){
        log.info("Getting All user");
        return ResponseEntity.ok(userRepository.findAll());
    }
    public ResponseEntity<?> getUserByUsername(String username){
        log.info("Fetching user for {}", username);
        return ResponseEntity.ok(userRepository.findByUsername(username));
    }

    public ResponseEntity<?> addRoleToUser(String username, String name){
        log.info("Adding role {} to user {}", name, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(name);

        user.getRoles().add(role);

        return ResponseEntity.ok("Role assign for user Successfully");
    }
}
