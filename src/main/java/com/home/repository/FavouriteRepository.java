package com.home.repository;

import com.home.entity.AppUser;
import com.home.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
  @Query("select f from Favourite f where f.appUser=:User")
  public List<Favourite> getFavourite(@Param("User") AppUser User);
}