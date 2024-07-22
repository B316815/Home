package com.home.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

@Data
@Transactional
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "guest", nullable = false)
    private Integer guest;

    @Column(name = "beds", nullable = false)
    private Integer beds;

    @Column(name = "bathrooms", nullable = false)
    private Integer bathrooms;

    @Column(name = "bedrooms", nullable = false)
    private Integer bedrooms;

    @Column(name = "property_name", nullable = false)
    private String propertyName;

    @Column(name = "nightly_price", nullable = false)
    private Double nightlyPrice;

}