package travelagency.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * This Vacation entity encapsulates all the information about travel packages offered.
 * It serves as a central point for exploring and linking to various excursions.
 */

@Entity
@Table(name = "vacations")
@Getter
@Setter

public class Vacation {

    // The primary identifier for a Vacation

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_id")
    private Long id;

    // The title that captures the essence of the vacation

    @Column(name = "vacation_title")
    private String vacation_title;

    // A sneak-peek into what the vacation may be about

    @Column(name = "description")
    private String description;

    // The price that's advertised for going on this vacation

    @Column(name = "travel_fare_price")
    @JsonProperty("travel_price")
    private BigDecimal travel_price;

    // A URL pointing to an image of the destination

    @Column(name = "image_url")
    @JsonProperty("image_URL")
    private String image_URL;

    // Automatically sets the date/time of creation

    @CreationTimestamp
    @Column(name = "create_date")
    private Date createDate;

    // Updated when modifications occur

    @UpdateTimestamp
    @Column(name = "last_update")
    private Date lastUpdate;

    // A collection of excursions available with this vacation

    @OneToMany(mappedBy = "vacation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Excursion> excursions;
}
