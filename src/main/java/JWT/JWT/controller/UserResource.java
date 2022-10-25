package JWT.JWT.controller;

import JWT.JWT.domain.Role;
import JWT.JWT.domain.User;
import JWT.JWT.service.RoleService;
import JWT.JWT.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/get-user")
    public ResponseEntity<?> getUsers(){
        return userService.getUser();
    }
    @PostMapping("/add-user")
    public ResponseEntity<?> addUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    @GetMapping("/get-role")
    public ResponseEntity<?> getRole(){
        return roleService.getRole();
    }
    @PostMapping("/add-role")
    public ResponseEntity<?> addRole(@RequestBody Role role){
        return roleService.saveRole(role);
    }

    @PostMapping("/add-role-to-user")
    public ResponseEntity<?> addRoleToUser(@RequestBody String username, String name){
        return userService.addRoleToUser(username, name);
    }
}
