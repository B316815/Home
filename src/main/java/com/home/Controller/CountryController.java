package com.home.Controller;

import com.home.Service.CountryService;
import com.home.entity.Country;
import com.home.paylod.CountryDto;
import com.home.repository.CountryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/c1/countries")

public class CountryController {
    private CountryService countryService;
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @PostMapping("/{addCountry}")
    public ResponseEntity<CountryDto>addCountry(@RequestBody CountryDto countryDto){
        System.out.println(countryDto.getCountryName());
        CountryDto dto = countryService.addCountry(countryDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PutMapping("/{updateCountry}")
    public ResponseEntity<CountryDto>updateCountry(@RequestBody CountryDto countryDto){
        CountryDto dto = countryService.updateCountry(countryDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
//    @DeleteMapping
//    public ResponseEntity<CountryDto>deleteCountry(@RequestParam ("id") long id){
//        CountryDto dto = countryService.deleteCountry(id);
//        return new ResponseEntity<>(dto, HttpStatus.CREATED);
//    }

    @DeleteMapping
    public ResponseEntity<String>deleteCountry(@RequestParam ("id") long id){
         countryService.deleteCountry(id);
        return ResponseEntity.ok("Country is Deleted");
    }

    @GetMapping("/{all}")
   public ResponseEntity <List<Country>> getAll() {
        List<Country> countries = countryService.findAll();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity <Country> getById(@PathVariable Long id ) {
        Country byId = countryService.getById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }


}
