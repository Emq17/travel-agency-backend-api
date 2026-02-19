package travelagency.entities;

/**
 * This defines the possible states of an order within the system. Each status represents
 * a distinct phase in the order lifecycle.
 */

public enum StatusType {

    // The order has been created but not yet processed

    pending,

    // The order has been officially placed and is being processed

    ordered,

    // The order has been cancelled and will not be processed

    canceled
}
