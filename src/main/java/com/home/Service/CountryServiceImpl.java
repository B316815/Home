package com.home.Service;

import com.home.entity.Country;
import com.home.paylod.CountryDto;
import com.home.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryDto addCountry(CountryDto countryDto) {
        Country country = mapToEntity(countryDto);
        Country con = countryRepository.save(country);
        CountryDto dto = mapToDto(con);
        return dto;
    }

    @Override
    public CountryDto updateCountry(CountryDto countryDto) {
        Country country = mapToEntity(countryDto);
        Country con = countryRepository.save(country);
        CountryDto dto = mapToDto(con);
        return dto;
    }
    @Override
    public CountryDto deleteCountry(long id) {
        countryRepository.deleteById(id);
        return null;
    }

    @Override
    public List<Country> findAll() {
        List<Country> countries = countryRepository.findAll();
        return countries;
    }

    @Override
    public Country getById(Long id) {
        Optional<Country> byId = countryRepository.findById(id);
        Country country = byId.get();
        return country;
    }

    CountryDto mapToDto(Country country){
       CountryDto dto=new CountryDto();
       dto.setId(country.getId());
       dto.setCountryName(country.getCountryName());

       return dto;
   }
   Country mapToEntity(CountryDto countryDto){
       Country country= new Country();
       country.setId(countryDto.getId());
       country.setCountryName(countryDto.getCountryName());
       return country;
   }
}
