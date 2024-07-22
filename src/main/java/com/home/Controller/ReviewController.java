package com.home.Controller;


import com.home.Service.ReviewService;
import com.home.entity.AppUser;
import com.home.entity.Property;
import com.home.entity.Review;

import com.home.paylod.ReviewDto;
import com.home.repository.PropertyRepository;
import com.home.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ReviewRepository reviewRepository;


    @PostMapping
    public ResponseEntity<String> addReview(
            @AuthenticationPrincipal AppUser User,
            @RequestParam long propertyId,
            @RequestBody Review r
            ){
        Optional<Property> byId = propertyRepository.findById(propertyId);
        Property property = byId.get();
        r.setAppUser(User);
        r.setProperty(property);
        Review fetched = reviewRepository.fetchUserReview(property,User);
        if(fetched!=null) {
            return new ResponseEntity<>("Review is already added", HttpStatus.BAD_REQUEST);
        }
        reviewRepository.save(r);
        return new ResponseEntity<>("Review added", HttpStatus.CREATED);
    }


}
