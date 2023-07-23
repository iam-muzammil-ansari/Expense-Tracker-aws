package com.geekster.expenseTrackerAPI.controller;

import com.geekster.expenseTrackerAPI.model.DataToObject.SignInInput;
import com.geekster.expenseTrackerAPI.model.DataToObject.SignUpOutput;
import com.geekster.expenseTrackerAPI.model.User;
import com.geekster.expenseTrackerAPI.service.AuthenticationService;
import com.geekster.expenseTrackerAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public SignUpOutput registerUser(@Valid @RequestBody User user) {
        return userService.SignUpUser(user);
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody @Valid SignInInput signInInput) {
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("/signout")
    public String signOutUser(@RequestParam String email, @RequestParam String token) {
        if (authenticationService.authenticate(email, token)) {
            return userService.signOutUser(email);
        } else {
            return "Sign out not allowed for non-authenticated user.";
        }
    }

}
