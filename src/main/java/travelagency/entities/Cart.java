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

/** This entity basically stores details about the travel package selected by the user,
 * including thngs like price, party size, and associated items.
 */

@Entity
@Table(name = "carts")
@Getter
@Setter

public class Cart {

    //Unique identifier for the Cart entity.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    // This is the tracking # for the order which will be generated somewhere else.

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    // This is simply the price of the travel package that is selected.

    @Column(name = "package_price")
    private BigDecimal package_price;

    // This is the size of the party that will be booked in the cart.

    @Column(name = "party_size")
    private int party_size;

    // Below is bascially the current status of the cart (ex. pending, completed, cancelled)

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status;

    // This is the time when the cart was created which is automatically set at creation time.

    @CreationTimestamp
    @Column(name = "create_date")
    private Date create_date;

    // The timestamp of the last modification made to the cart.

    @UpdateTimestamp
    @Column(name = "last_update")
    private Date last_update;

    // This creates the many-to-one relationship between Cart and Customer entities.

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /** This is a set of Cartitem entities associated with this cart. It creates a one-to-many
     * relationship between the Cart and Cartitem entities.
     */
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CartItem> cartItem = new HashSet<>();

    public void add(CartItem cartItem) { this.cartItem.add(cartItem);
    }
}
