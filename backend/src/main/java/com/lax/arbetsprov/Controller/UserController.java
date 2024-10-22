package com.lax.arbetsprov.Controller;

import com.lax.arbetsprov.Beans.User;
import com.lax.arbetsprov.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController indikerar att denna klass hanterar HTTP-förfrågningar och returnerar JSON-svar.
// @RequestMapping anger bas-URL för alla metoder i denna controller. I detta fall "/digg".
// @CrossOrigin tillåter cross-origin requests från de angivna domänerna och konfigurerar de tillåtna HTTP-metoderna och andra inställningar.
@RestController
@RequestMapping("/digg")
@CrossOrigin(
        origins = {"http://127.0.0.1:80", "http://localhost", "http://localhost:5173"}, // Tillåter endast förfrågningar från dessa specifika ursprung.
        allowedHeaders = "*",  // Tillåter alla headers i inkommande förfrågningar.
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}, // Tillåtna HTTP-metoder.
        allowCredentials = "true")  // Tillåter credentials (cookies, autorisation headers) i förfrågningarna.
public class UserController {

    // @Autowired injicerar ett UserService-objekt i denna controller, så vi kan använda dess metoder för att hantera användardata.
    @Autowired
    UserService userService;

    // @GetMapping hanterar GET-förfrågningar till "/digg/user". Den returnerar en lista över alla användare genom att anropa userService.users().
    @GetMapping("/user")
    public List<User> users(){
        return userService.users();
    }

    // @PostMapping hanterar POST-förfrågningar till "/digg/newuser". Den skapar en ny användare genom att anropa userService.newUser() med användardata från förfrågningskroppen.
    @PostMapping("/newuser")
    public User newUser(@RequestBody User user){
        return userService.newUser(user);
    }

    // @PutMapping hanterar PUT-förfrågningar till "/digg/edituser". Den uppdaterar en befintlig användare genom att skicka användardata till userService.updateUser().
    @PutMapping("/edituser")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    // @DeleteMapping hanterar DELETE-förfrågningar till "/digg/deleteuser". Den tar bort en användare genom att anropa userService.deleteUser() med användardata från förfrågningskroppen.
    // ResponseEntity.noContent() returnerar en statuskod 204 (No Content) om borttagningen lyckas.
    @DeleteMapping("/deleteuser")
    public ResponseEntity<Object> deleteUser(@RequestBody User user){
        userService.deleteUser(user);
        return ResponseEntity.noContent().build();
    }
}
