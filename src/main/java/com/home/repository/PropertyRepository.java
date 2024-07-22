package com.home.repository;

import com.home.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    //It Join Location And Country Both
  @Query("select p from Property p join Location l on p.location = l.id  join Country c on p.country = c.id where l.locationName=:locationName or c.countryName=:locationName")
    List<Property>listPropertyByLocationOrCountryName(@Param("locationName") String locationName);

    //    it joins only Location
//    @Query("select p from Property p join Location l on p.location = l.id where l.locationName=:locationName")
//    List<Property>listPropertyByLocationOrCountryName(@Param("locationName") String locationName);

}