package travelagency.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

/** This is a representation of the customer profile holding personal details and their
 * associated bookings. Captures essential details of a customer.
 */
@Getter
@Setter
@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    // Acts as the primary key in the database

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    // This is the customer's given first name

    @NotBlank(message = "A first name must be provided")
    @Column(name = "customer_first_name", nullable = false)
    private String firstName;

    // This here is the customer's last name

    @NotBlank(message = "A last name must be provided")
    @Column(name = "customer_last_name", nullable = false)
    private String lastName;

    // Mailing address for contact purposes

    @NotBlank(message = "An address must be provided")
    @Column(name = "address", nullable = false)
    private String address;

    // Code for mail sorting and delivery

    @NotBlank(message = "You must enter a postal code")
    @Size(min = 5, max = 10, message = "Postal code should have between 5 - 10 characters")
    @Column(name = "postal_code", nullable = false)
    private String postal_code;

    // Primary telephone number from the customer

    @NotBlank(message = "A phone number has to be provided")
    @Column(name = "phone", nullable = false)
    private String phone;

    // The date/time when the customer record was first created

    @CreationTimestamp
    @Column(name = "create_date")
    private Date create_date;

    // This reflects the most recent update to the customer's record

    @UpdateTimestamp
    @Column(name = "last_update")
    private Date last_update;

    // The division (state or province) to which the customer belongs to.

    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;

    // All carts associated with the customer

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cart> carts;
}