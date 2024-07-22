package com.home.Controller;

import com.home.Service.LocationService;
import com.home.entity.Country;
import com.home.entity.Location;
import com.home.paylod.CountryDto;
import com.home.paylod.LocationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/d1/location")

public class LocationController {
    private LocationService locationService;
    public LocationController(LocationService locationService) {
        this.locationService = locationService;

    }
    @PostMapping("/{addLocation}")
    public ResponseEntity<LocationDto>addLocation(@RequestBody LocationDto locationDto){
        System.out.println(locationDto.getLocationName());
        LocationDto dto = locationService.addLocation(locationDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PutMapping("/{updateLocation}")
    public ResponseEntity<LocationDto>upateLocation(@RequestBody LocationDto locationDto){
        LocationDto dto = locationService.updateLocation(locationDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Location>>getAllLocation(){
        List<Location> location = locationService.findAll();
        return new ResponseEntity<>(location,HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<String>deleteLocation(@RequestParam("id") long id){
          locationService.deleteLocation(id);
        return ResponseEntity.ok("Location is Deleted");
    }


    @GetMapping("/{all}")
    public ResponseEntity<List<Location>> getAll(){
    List<Location> locations = locationService.findAll();
    return new ResponseEntity<>(locations,HttpStatus.OK);
    }
//    @GetMapping
//    public ResponseEntity <Location> findById(@PathVariable Long id ) {
//        Location byId = locationService.findById(id);
//        return new ResponseEntity<>(byId, HttpStatus.OK);
//    }

    
    
}




