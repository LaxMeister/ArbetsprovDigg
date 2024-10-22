package com.lax.arbetsprov.Beans;

// Importerar nödvändiga klasser för att använda Jackson för JSON och JPA för databasoperationer
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

// @Entity indikerar att detta är en JPA-entitet som representerar en databas-tabell.
// @Table specificerar namnet på tabellen som denna entitet mappas till ("Users").
@Entity
@Table(name = "Users")
public class User {

    // @Id indikerar att detta fält är primärnyckeln för entiteten.
    // @GeneratedValue används för att generera ett unikt ID automatiskt med hjälp av "GenerationType.IDENTITY", vilket låter databasen hantera detta.
    // @JsonProperty gör så att detta fält inkluderas i JSON-representationen av objektet.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private long id;

    // @JsonProperty gör så att dessa fält inkluderas i JSON-representationen.
    @JsonProperty
    public String name;

    @JsonProperty
    public String address;

    @JsonProperty
    public String email;

    @JsonProperty
    public String telephone;

    // Standardkonstruktör som krävs av JPA (ingen-argument-konstruktör).
    public User() {
    }

    // Konstruktör som tillåter att skapa ett objekt med alla fält vid instansiering.
    public User(long id, String name, String address, String email, String telephone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
    }

    // Getter- och setter-metoder för varje fält för att ge tillgång till och möjlighet att uppdatera objektets data.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String phone) {
        this.telephone = phone;
    }
}
