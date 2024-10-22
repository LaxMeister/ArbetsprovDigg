package com.lax.arbetsprov.Service;

import com.lax.arbetsprov.Beans.User;
import com.lax.arbetsprov.Dao.UserDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service indikerar att denna klass är en tjänstkomponent som hanterar affärslogik.
// Logger används för att logga information och varningar under programkörning.
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    // @Autowired injicerar UserDao så att tjänsten kan använda DAO-metoder för att interagera med databasen.
    @Autowired
    UserDao userDao;

    // Metod som hämtar och returnerar en lista med alla användare från databasen.
    public List<User> users() {
        return userDao.findAll();
    }

    // Metod för att hämta en användare baserat på deras ID.
    public User findUserById(long id) {
        return userDao.findById(id);
    }

    // Metod som tar emot en User-objekt, sparar det i databasen och loggar händelsen.
    public User newUser(User user) {
        logger.info("User saved: " + user.name);
        return userDao.save(user);
    }

    // Metod för att uppdatera en användare. Skapar ett nytt User-objekt för att säkerställa att uppdateringen är korrekt.
    public User updateUser(User user) {
        User updatedUser = new User();
        updatedUser.setId(user.getId());
        updatedUser.setName(user.getName());
        updatedUser.setAddress(user.getAddress());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setTelephone(user.getTelephone());

        userDao.save(updatedUser); // Sparar det uppdaterade användarobjektet.
        logger.info("User updated: " + updatedUser.name); // Loggar uppdateringshändelsen.
        return updatedUser;
    }

    // Metod för att ta bort en användare. Kontrollerar först om användaren finns innan den raderar den.
    public String deleteUser(User user) {
        // Hittar användaren baserat på ID.
        Optional<User> userToDelete = Optional.ofNullable(userDao.findById(user.getId()));

        // Kontrollerar om användaren finns.
        if (userToDelete.isPresent()) {
            // Om användaren hittas, raderas den.
            userDao.deleteById(user.getId());
            logger.info("User deleted: " + userToDelete.get().getName());
            return userToDelete.get().getName() + " was deleted"; // Bekräftelsemeddelande.
        } else {
            // Om användaren inte hittas, loggas en varning och ett meddelande returneras.
            logger.warn("User not found with ID: " + user.getId());
            return "User not found";
        }
    }
}
