package travelagency.services;

import lombok.Data;

// Carries the order tracking number as a response to a purchase.
@Data
public class PurchaseResponse {

    private String orderTrackingNumber; // Unique ID track the order.

    // Initializes response with an order tracking number.

    public PurchaseResponse(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }
}
