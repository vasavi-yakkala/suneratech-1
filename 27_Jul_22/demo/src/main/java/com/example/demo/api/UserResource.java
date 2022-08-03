package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class UserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);
    @Autowired
    private UserRepo repo;

    @Value("${myprop}")
    private String myProp;

    @Operation(summary = "Get users either by a nickname or get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "users found"),
            @ApiResponse(responseCode = "404", description = "users not found")
    })
    @GetMapping(path = "/users", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity getAllUsers(@RequestParam(required = false, name = "nickname") String nickName) {
        LOGGER.info("myProp: {}", myProp);
        LOGGER.info("getAllUsers method is called with nickname: {}", nickName);
        if(StringUtils.isNotBlank(nickName)) {
            LOGGER.info("nickname: {} is passed", nickName);
            Optional<List<User>> optionalUserList = repo.findByNickNameContainingIgnoreCase(nickName);
            if(optionalUserList.isPresent() && optionalUserList.get().size() > 0) {
                LOGGER.info("found users with size: {}", optionalUserList.get().size());
                return ResponseEntity.ok(optionalUserList.get());
            }
            LOGGER.error("users not found for nickname: {}", nickName);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("nickname: " + nickName + " not found");
        }
        return ResponseEntity.ok(repo.findAll());
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

    @PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
