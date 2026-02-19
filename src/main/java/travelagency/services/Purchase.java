package travelagency.services;

import travelagency.entities.Cart;
import travelagency.entities.CartItem;
import travelagency.entities.Customer;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

/** Represents the details of a customer's purchase; This class acts as a data transfer
* object to carry data between processes.
 */
@Getter
@Setter
public class Purchase {
    private Customer customer; // The customer making the purchase
    private Cart cart; // The cart containing the items being purchased
    private Set<CartItem> cartItems; // The items within the cart
}
