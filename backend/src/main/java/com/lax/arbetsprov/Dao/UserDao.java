package com.lax.arbetsprov.Dao;

import com.lax.arbetsprov.Beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long >{

    User findById(long id);
    User deleteById(long id);
    User findByName(String name);

}
