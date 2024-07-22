package com.home.Service;

import com.home.entity.Property;
import com.home.paylod.PropertyDto;
import com.home.repository.LocationRepository;
import com.home.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    private PropertyRepository propertyRepository;
    private final LocationRepository locationRepository;


    public PropertyServiceImpl(PropertyRepository propertyRepository,
                               LocationRepository locationRepository) {
        this.propertyRepository = propertyRepository;

        this.locationRepository = locationRepository;
    }

    @Override
    public PropertyDto addProperty(PropertyDto propertyDto) {
       Property property = mapToEntity(propertyDto);
        Property savep = propertyRepository.save(property);
        PropertyDto dto = mapToDto(savep);
        return dto;
    }

    @Override
    public PropertyDto updateProperty(PropertyDto propertyDto) {
        Property property = mapToEntity(propertyDto);
        Property savep = propertyRepository.save(property);
        PropertyDto dto = mapToDto(savep);
        return dto;
    }

    @Override
    public PropertyDto deleteProperty(long id) {
        locationRepository.deleteById(id);
        return null;
    }

    @Override
    public List<Property> findAll() {
        List<Property> properties = propertyRepository.findAll();
        return properties;
    }

    @Override
    public Property FindById(Long id) {
        Optional<Property> byId = propertyRepository.findById(id);
        Property property= byId.get();
        return property;
    }

    @Override
    public List<Property> listPropertyByLocationOrCountryName(String locationName) {
        List<Property> properties = propertyRepository.listPropertyByLocationOrCountryName(locationName);
        return properties;
    }

    PropertyDto mapToDto(Property p1){
        PropertyDto dto = new PropertyDto();
        dto.setId(p1.getId());
        dto.setGuest(p1.getGuest());
        dto.setBeds(p1.getBeds());
        dto.setBedrooms(p1.getBedrooms());
        dto.setBathrooms(p1.getBathrooms());
        dto.setPropertyName(p1.getPropertyName());
        dto.setNightlyPrice(p1.getNightlyPrice());
        dto.setCountry(p1.getCountry());
        dto.setLocation(p1.getLocation());
        return dto;
    }
    Property mapToEntity(PropertyDto propertyDto){
        Property property =new Property();
        property.setId(propertyDto.getId());
        property.setGuest(propertyDto.getGuest());
        property.setBeds(propertyDto.getBeds());
        property.setBedrooms(propertyDto.getBedrooms());
        property.setBathrooms(propertyDto.getBathrooms());
        property.setPropertyName(propertyDto.getPropertyName());
        property.setNightlyPrice(propertyDto.getNightlyPrice());
        property.setCountry(propertyDto.getCountry());
        property.setLocation(propertyDto.getLocation());

        return property;
    }
}

