package com.home.Service;

import com.home.entity.Country;
import com.home.entity.Property;
import com.home.paylod.CountryDto;
import com.home.paylod.PropertyDto;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
    public PropertyDto addProperty(PropertyDto propertyDto);
    public PropertyDto updateProperty(PropertyDto propertyDto);
    public PropertyDto deleteProperty(long id);
    public List<Property> findAll();
    public Property FindById(Long id);


    List<Property> listPropertyByLocationOrCountryName(String locationName);

}
