package fr.sup.galilee.pharmacy.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Cart {
    @Id
    private String id;
    @OneToOne
    private User user;

}
