package com.home.Service;

import com.home.entity.Country;
import com.home.entity.Location;
import com.home.paylod.CountryDto;
import com.home.paylod.LocationDto;

import java.util.List;

public interface LocationService {

    public LocationDto addLocation(LocationDto locationDto);
    public LocationDto updateLocation(LocationDto locationDto);
    public LocationDto deleteLocation(long id);
    public List<Location> findAll();
    public Location findById(Long id);
}

