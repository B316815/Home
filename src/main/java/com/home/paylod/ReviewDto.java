package com.home.paylod;

import com.home.entity.AppUser;
import com.home.entity.Property;
import lombok.Data;

@Data
public class ReviewDto {

    private long Id;
    private String content;
    private int rating;

    private Property property;
    private AppUser appUser;


}
