package travelagency.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

/**
 * Defines a line item in the booking cart. It links a vacation package
 * to additional optional activities (excursions) chosen by the customer.
 */

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

     // Unique identifier for the CartItem entity.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    /**
     * The vacation associated with this cart item. Represents a many-to-one relationship
     * between CartItem and Vacation.
     */

    @ManyToOne
    @JoinColumn(name = "vacation_id")
    private Vacation vacation;

    /**
     * The set of extra excursions selected for this travel package. Reflects the
     * possibility of multiple excursions being associated with a single cart item.
     */

    @ManyToMany
    @JoinTable(
            name = "excursion_cartitem",
            joinColumns = @JoinColumn(name = "cart_item_id"),
            inverseJoinColumns = @JoinColumn(name = "excursion_id")
    )

    Set<Excursion> excursions;

    /**
     * Reference back to the cart this item is part of, forming a part of
     * the broader booking transaction.
     */

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    // The timestamp when this cart item was created, automatically set at creation time.

    @CreationTimestamp
    @Column(name = "create_date")
    private Date create_date;

    /**
     * The timestamp of the last update to this cart item, which is also automatically set
     * at update time.
     */

    @UpdateTimestamp
    @Column(name = "last_update")
    private Date last_update;

}
