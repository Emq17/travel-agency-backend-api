package travelagency.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Division entity captures subdivisions witihn a country, such as states, and links
 * to the customers who reside within.
 */

@Entity
@Table(name="divisions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Division {

    // The divisions unique identifier

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "division_id")
    private Long id;

    // The common name of the division

    @Column(name = "division")
    private String division_name;

    // The date the division record was created

    @CreationTimestamp
    @Column(name = "create_date")
    private Date create_date;

    // The date the division record was last updated

    @UpdateTimestamp
    @Column(name = "last_update")
    private Date last_update;

    // The country to which the division belongs to

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false, insertable = false, updatable = false)
    private Country country;

    // The customers living in this division

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "division")
    private Set<Customer> customers = new HashSet<>();

    // The ID linking the division to its country

    @Column(name = "country_id")
    private long country_id;

    /** Associates the division with a country and updates teh country_id accordingly.
     * @param country is the country that encompasses this division.
     */
    public void setCountry(Country country) {
        setCountry_id (country.getId());
        this.country = country;
    }
}


