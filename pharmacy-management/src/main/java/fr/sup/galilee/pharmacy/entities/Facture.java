package fr.sup.galilee.pharmacy.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class Facture {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private float value;
    @Column(nullable = false)
    private Instant date;
    @ManyToOne
    @Column(nullable = false)
    private User user;

}
