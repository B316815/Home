package com.home.Service;

import com.home.entity.Country;
import com.home.paylod.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    public CountryDto addCountry(CountryDto countryDto);
    public CountryDto updateCountry(CountryDto countryDto);
    public CountryDto deleteCountry(long id);

   public List<Country> findAll();

   public Country getById(Long id);

}
