package JWT.JWT.service;

import JWT.JWT.domain.Role;
import JWT.JWT.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;

    public ResponseEntity<?> saveRole(Role role){
        log.info("Saving new role {} to the database", role.getName());
        roleRepository.save(role);
        return ResponseEntity.ok("Role add successfully");
    }
    public ResponseEntity<?> getRole(){
        log.info("Fetching all role");
        return ResponseEntity.ok(roleRepository.findAll());
    }
    public ResponseEntity<?> getRoleByName(String name){
        log.info("Fetching role for {}", name);
        return ResponseEntity.ok(roleRepository.findByName(name));
    }
}
