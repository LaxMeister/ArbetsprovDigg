package com.lax.arbetsprov.Dao;

import com.lax.arbetsprov.Beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository indikerar att detta gränssnitt är en komponent som hanterar databasoperationer.
// UserDao är ett gränssnitt som utökar JpaRepository, vilket ger grundläggande CRUD-operationer för User-entiteten.
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    // Metod för att hämta en användare baserat på dess ID. JpaRepository tillhandahåller också en findById()-metod, så denna kan vara överflödig.
    User findById(long id);

    // Metod för att ta bort en användare baserat på dess ID. Jag vet att JpaRepository redan tillhandahåller en deleteById()-metod,
    // så denna kan också anses vara överflödig men jag ville vara extra tydlig. Det returnerade värdet här kan vara otydligt eftersom deleteById() vanligtvis inte returnerar ett objekt.
    User deleteById(long id);

}
