//package com.home.Service;
//
//import com.home.entity.AppUser;
//import com.home.entity.Property;
//import com.home.entity.Review;
//import com.home.paylod.ReviewDto;
//import com.home.repository.AppUserRepository;
//import com.home.repository.PropertyRepository;
//import com.home.repository.ReviewRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class ReviewServiceImpl implements ReviewService {
//    private ReviewRepository reviewRepository;
//    private PropertyRepository propertyRepository;
//    private AppUserRepository appUserRepository;
//
//
//    public ReviewServiceImpl(ReviewRepository reviewRepository, PropertyRepository propertyRepository, AppUserRepository appUserRepository) {
//        this.reviewRepository = reviewRepository;
//        this.propertyRepository = propertyRepository;
//        this.appUserRepository = appUserRepository;
//    }
//
//    @Override
//    public ReviewDto addReview(ReviewDto reviewDto) {
//        Review review = new Review();
//        review.setId(reviewDto.getId());
//        review.setContent(reviewDto.getContent());
//        review.setRating(reviewDto.getRating());
//
////        Optional<Property> byId = propertyRepository.findById(Id);
////        Property property = byId.get();
//        review.setAppUser(reviewDto.getAppUser());
//        review.setProperty(reviewDto.getProperty());
//
////        Optional<Property> byId = propertyRepository.findById(propertyId());
////        Property property = byId.get();
////        Review review = new Review();
////        review.setAppUser(User);
////        review.setProperty(property);
//
//        Review saved = reviewRepository.save(review);
//        ReviewDto dto = new ReviewDto();
//        dto.setId(saved.getId());
//        dto.setContent(saved.getContent());
//        dto.setRating(saved.getRating());
////        dto.setAppUser(saved.getAppUser());
////        dto.setProperty(saved.getProperty());
//        return  reviewDto;
//
//    }
//
//}
