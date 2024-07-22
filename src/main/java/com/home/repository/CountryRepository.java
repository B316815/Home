package com.home.repository;

import com.home.entity.Country;
import com.home.paylod.CountryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

}