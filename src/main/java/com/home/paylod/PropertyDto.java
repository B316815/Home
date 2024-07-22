package com.home.paylod;

import com.home.entity.Country;
import com.home.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDto {
    private long id;
    private int guest;
    private int beds;
    private int bathrooms;
    private int bedrooms;
    private double nightlyPrice;
    private String propertyName;
    private Location location;
    private Country country;

}
