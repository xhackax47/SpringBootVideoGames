package fr.xhackax47.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.xhackax47.dao.UserRepository;
import fr.xhackax47.exception.ResourceNotFoundException;
import fr.xhackax47.model.User;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository ur;
    
    @GetMapping("/")
    public List<User> getAllUsers() {
        return ur.findAll();
    }
    
    @PostMapping("/")
    public User createUser(@Valid @RequestBody User user) {
        return ur.save(user);
    }
    
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return ur.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", userId));
    }
    
    @PutMapping("/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId,
                                            @Valid @RequestBody User user) {

    	User usr = ur.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", userId));

    	usr.setLogin(user.getLogin());
    	usr.setPassword(user.getPassword());

        User usrUp = ur.save(usr);
        return usrUp;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVideoGame(@PathVariable(value = "id") Long userId) {
        User user = ur.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", userId));

        ur.delete(user);

        return ResponseEntity.ok().build();
    }
}
