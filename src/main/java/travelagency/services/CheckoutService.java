package travelagency.services;

// Handles checkout operations for purchases.

public interface CheckoutService {

    // Processes a purchase and returns an order tracking response

    PurchaseResponse placeOrder(Purchase purchase);
}
