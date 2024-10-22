package com.lax.arbetsprov.Controller;

import com.lax.arbetsprov.Beans.User;
import com.lax.arbetsprov.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/digg")
@CrossOrigin(
        origins = {"http://127.0.0.1:80", "http://localhost"},
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
        allowCredentials = "true")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<User> users(){
        return userService.users();
    }

    @PostMapping("/newuser")
    public User newUser(@RequestBody User user){
        return userService.newUser(user);
    }

    @PutMapping("/edituser")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteuser")
    public ResponseEntity<Object> deleteUser(@RequestBody User user){
        userService.deleteUser(user);
        return ResponseEntity.noContent().build();
    }

}
