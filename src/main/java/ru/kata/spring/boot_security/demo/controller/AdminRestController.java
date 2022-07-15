package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/admin")
    public ResponseEntity<List<User>> showAdminPage() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody User user, @PathVariable long id) {
        userService.getUserById(id);
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }


    @GetMapping("/header")
    public ResponseEntity<User> getAuthentication(Principal principal) {
        User user = userService.getUserByLogin(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
