package JWT.JWT.service;

import JWT.JWT.domain.Role;
import JWT.JWT.domain.User;
import JWT.JWT.repository.RoleRepository;
import JWT.JWT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> saveUser(User user){
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        log.info("Got Here Load User By username");
        if(user == null){
            log.error("User Not Found!!");
            throw new UsernameNotFoundException("User not found!!!!!");
        }
        else{
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                System.out.println(role.getName());
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }

    }
}
