package com.home.repository;

import com.home.entity.AppUser;
import com.home.entity.Property;
import com.home.entity.Review;
import com.home.paylod.ReviewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
   @Query("select r from Review r where r.property=:property and r.appUser=:User")
    Review fetchUserReview(@Param("property") Property property, @Param("User") AppUser User );
}