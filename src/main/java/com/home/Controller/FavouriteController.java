package com.home.Controller;

import com.home.entity.AppUser;
import com.home.entity.Favourite;
import com.home.entity.Property;
import com.home.repository.FavouriteRepository;
import com.home.repository.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/favourite")
public class FavouriteController {
    private FavouriteRepository fr;
    private PropertyRepository propertyRepository;

    public FavouriteController(FavouriteRepository fr, PropertyRepository propertyRepository) {
        this.fr = fr;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/addFav")
    public ResponseEntity<Favourite>addFavourite(
            @AuthenticationPrincipal AppUser User,
            @RequestParam long propertyId

            ){
        Optional<Property> byId = propertyRepository.findById(propertyId);
        Property property = byId.get();
        Favourite favourite = new Favourite();
        favourite.setAppUser(User);
        favourite.setProperty(property);
        favourite.setIsFavourite(true);
        Favourite savedfavourite = fr.save(favourite);
        return new ResponseEntity<>(savedfavourite, HttpStatus.CREATED);
    }
    @GetMapping("/userFavouriteList")
    public ResponseEntity<List<Favourite>>getAllFavouritesOfUser(
        @AuthenticationPrincipal AppUser  User
    ){
        List<Favourite> favourites = fr.getFavourite(User);
            return new ResponseEntity<>(favourites, HttpStatus.OK);
    }
}
