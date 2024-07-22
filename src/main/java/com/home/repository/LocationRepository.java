package com.home.repository;

import com.home.entity.Location;
import com.home.paylod.CountryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
}