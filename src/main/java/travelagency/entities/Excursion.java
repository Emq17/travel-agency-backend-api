package travelagency.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class embodies an excursion, which is basically a special adventure customers can enjoy
 * as part of their travels.
 */

@Entity
@Table(name = "excursions")
@Getter
@Setter

public class Excursion {

    // Identifies the excursion uniquely in the database

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "excursion_id")
    private Long id;

    // The name of the excursion itself

    @Column(name = "excursion_title")
    private String excursion_title;

    // The financial cost of the excursion

    @Column(name = "excursion_price")
    private BigDecimal excursion_price;

    // URL pointing to an image representing the excursion

    @Column(name = "image_url")
    private String image_URL;

    // The date/time this record was created

    @CreationTimestamp
    @Column(name = "create_date")
    private Date create_date;

    // The date/time this record was last modified or updated

    @UpdateTimestamp
    @Column(name = "last_update")
    private Date last_update;

    // This is the associated vacation package

    @ManyToOne
    @JoinColumn(name = "vacation_id", nullable = false)
    private Vacation vacation;

    // This is the cart items that include this excursion

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "excursions")
    Set<CartItem> cartitems=new HashSet<>();

    public Excursion() {

    }

}
