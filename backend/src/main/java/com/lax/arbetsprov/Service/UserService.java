package com.lax.arbetsprov.Service;

import com.lax.arbetsprov.Beans.User;
import com.lax.arbetsprov.Dao.UserDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserDao userDao;

    public List<User> users() {
        return userDao.findAll();
    }

    public User findUserById(long id) {
        return userDao.findById(id);
    }

    public User newUser(User user) {
        logger.info("User saved: " + user.name);
        return userDao.save(user);
    }

    public User updateUser(User user) {
        User updatedUser = new User();
        updatedUser.setId(user.getId());
        updatedUser.setName(user.getName());
        updatedUser.setAddress(user.getAddress());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setTelephone(user.getTelephone());

        userDao.save(updatedUser);
        logger.info("User updated: " + updatedUser.name);
        return updatedUser;
    }

    public String deleteUser(User user) {
        // Find the user by ID first
        Optional<User> userToDelete = Optional.ofNullable(userDao.findById(user.getId()));

        // Check if the user exists
        if (userToDelete.isPresent()) {
            // If found, delete the user
            userDao.deleteById(user.getId());
            logger.info("User deleted: " + userToDelete.get().getName());
            return userToDelete.get().getName() + " was deleted";
        } else {
            // If not found, return an error message or throw an exception
            logger.warn("User not found with ID: " + user.getId());
            return "User not found";
        }
    }

}
