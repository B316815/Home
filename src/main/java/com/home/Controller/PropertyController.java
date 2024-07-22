package com.home.Controller;

import com.home.Service.CountryService;
import com.home.Service.LocationService;
import com.home.Service.PropertyService;
import com.home.entity.Country;
import com.home.entity.Property;
import com.home.paylod.CountryDto;
import com.home.paylod.PropertyDto;
import com.home.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/e1/property")
public class PropertyController {

   private PropertyService propertyService;
   private PropertyRepository propertyRepository;
    public PropertyController(PropertyService propertyService, PropertyRepository propertyRepository) {
        this.propertyService = propertyService;
        this.propertyRepository = propertyRepository;
    }
    @PostMapping("/{addProperty}")
    public ResponseEntity<PropertyDto> addProperty(@RequestBody PropertyDto propertyDto){
        System.out.println(propertyDto.getBeds());
        PropertyDto dto = propertyService.addProperty(propertyDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
//    public ResponseEntity<Property> addProperty(@RequestBody Property property){
//        System.out.println(property.getBeds());
//        Property save = propertyRepository.save(property);
//        return new ResponseEntity<>(save, HttpStatus.CREATED);
//    }

    @PutMapping("/{updateProperty}")
    public ResponseEntity<PropertyDto>updateProperty(@RequestBody PropertyDto propertyDto){
        PropertyDto dto = propertyService.updateProperty(propertyDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

//    @DeleteMapping
//    public ResponseEntity<String>deleteProperty(@RequestParam("id")long id){
//        propertyService.deleteProperty(id);
//        return ResponseEntity.ok("Property is Deleted");
//    }
    @DeleteMapping
    public ResponseEntity<PropertyDto>deleteProperty(@RequestParam ("id") long id){
        PropertyDto dto = propertyService.deleteProperty(id);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @GetMapping("/{all}")
    public  ResponseEntity<List<Property>>findAll(){
        List<Property> properties = propertyService.findAll();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
//    @GetMapping
//    public ResponseEntity<Property>findById(@PathVariable Long id){
//        Property byId = propertyService.FindById(id);
//        return new ResponseEntity<>(byId,HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<Property>>getPropertyListing(@RequestParam String locationName){
        List<Property> properties = propertyService.listPropertyByLocationOrCountryName(locationName);
        return new ResponseEntity<>(properties,HttpStatus.OK);
    }

}
