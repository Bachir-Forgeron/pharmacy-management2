package fr.sup.galilee.pharmacy.entities;

import fr.sup.galilee.pharmacy.entities.enums.UserProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    private String id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private boolean active;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserProfile profile;
}
