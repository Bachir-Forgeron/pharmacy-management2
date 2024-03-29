package fr.sup.galilee.pharmacy.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartProduct {
    @Id
    @GeneratedValue
    private long id;
    private Integer quantity;
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Product product;

}
