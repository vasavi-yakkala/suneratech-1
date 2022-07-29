package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class UserResource {

    @Autowired
    private UserRepo repo;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getSingleUser(@PathVariable Integer id) {
        Optional<User> userFound = repo.findById(id);
        if(userFound.isPresent()) {
            return ResponseEntity.ok(userFound.get());
        }

//        return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User: " + id + " not found");
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody User user) throws URISyntaxException {
        if (Objects.isNull(user) || Objects.isNull(user.getName()) || Objects.isNull(user.getNickName())) {
            return ResponseEntity.badRequest().build();
        }
        user.setId(null);
        User newUser = repo.save(user);

        return ResponseEntity.created(new URI(newUser.getId().toString())).body(newUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        repo.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
