package travelagency.services;

import travelagency.entities.Cart;
import travelagency.entities.CartItem;
import travelagency.dao.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

import static travelagency.entities.StatusType.ordered;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private CartRepository cartRepository;

    public CheckoutServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Cart cart = purchase.getCart();
        cart.setStatus(ordered);
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);
        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> {
            cart.add(item);
            item.setCart(cart);
            item.getExcursions().forEach(excursion -> {
                excursion.setVacation(item.getVacation());
                excursion.getCartitems().add(item);
            });
        });
        cartRepository.save(cart);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
