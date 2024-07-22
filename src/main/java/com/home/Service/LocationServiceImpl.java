package com.home.Service;

import com.home.entity.Country;
import com.home.entity.Location;
import com.home.paylod.CountryDto;
import com.home.paylod.LocationDto;
import com.home.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService{

    private LocationRepository locationRepository;
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    @Override
    public LocationDto addLocation(LocationDto locationDto) {
        Location location = mapToEntity(locationDto);
        Location savel = locationRepository.save(location);
        LocationDto dto = mapToDto(savel);
        return dto;
    }
    @Override
    public LocationDto updateLocation(LocationDto locationDto) {
        Location location = mapToEntity(locationDto);
        Location save = locationRepository.save(location);
        LocationDto dto = mapToDto(save);
        return dto;
    }
    @Override
    public LocationDto deleteLocation(long id) {
        locationRepository.deleteById(id);
        return null;
    }

    @Override
    public List <Location> findAll() {
        List<Location> locations = locationRepository.findAll();
        return locations;
    }

    @Override
    public Location findById(Long id) {
        Optional<Location> byId = locationRepository.findById(id);
        Location location=byId.get();
        return location;
    }





    LocationDto mapToDto(Location location){
        LocationDto dto = new LocationDto();
        dto.setId(location.getId());
        dto.setLocationName(location.getLocationName());
        return dto;
    }

   Location mapToEntity (LocationDto locationDto){
       Location location = new Location();
       location.setId(locationDto.getId());
       location.setLocationName(locationDto.getLocationName());
       return location;
   }
}
