package com.shaunmccready.orderupapi.controller;

import com.shaunmccready.orderupapi.domain.Users;
import com.shaunmccready.orderupapi.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * Retrieve a single User from the Database
     *
     * @param userId Id of the user
     * @return User Object
     */
    @GetMapping("/{id}")
    public Users getUser(@Valid @PathVariable("id") String userId) {
        return usersService.getUser(userId);
    }


    /**
     * Create a new User
     *
     * @param user
     * @return
     */
    @PostMapping
    public ResponseEntity<Users> createUser(@Valid @RequestBody Users user) {
        Users newUser = usersService.createUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(newUser);
    }

    /**
     * Modify an existing User
     *
     * @param userId
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    public Users updateUser(@PathVariable("id") String userId,
                            @RequestBody Users user) {
        return usersService.updateUser(userId, user);
    }

}
