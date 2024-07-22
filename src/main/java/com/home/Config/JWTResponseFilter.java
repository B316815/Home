package com.home.Config;

import com.home.Service.JWTService;
import com.home.entity.AppUser;
import com.home.repository.AppUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTResponseFilter extends OncePerRequestFilter {
    private JWTService jwtService;
    private AppUserRepository appUserRepository;

    public JWTResponseFilter(JWTService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
//        System.out.println(tokenHeader);
        if(tokenHeader!=null && tokenHeader.startsWith("Bearer ")){
            String token = tokenHeader.substring(8 ,tokenHeader.length()-1);
            var username = jwtService.getUserName(token);
            System.out.println(username);
            Optional<AppUser> opUser = appUserRepository.findByUsername(username);
            if(opUser.isPresent()){
                AppUser appUser=opUser.get();
//            To track current user logged in
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(appUser,null, Collections.singleton(new SimpleGrantedAuthority(appUser.getUserRole())));
//                adani set new world record
                authentication.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        filterChain.doFilter(request,response);

    }
}












//import com.home.Service.JWTService;
//import com.home.entity.AppUser;
//import com.home.repository.AppUserRepository;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Optional;
//@Component
//public class JWTResponseFilter extends OncePerRequestFilter {
//
//    private JWTService jwtService;
//    private AppUserRepository appUserRepository;
//    public JWTResponseFilter(JWTService jwtService, AppUserRepository appUserRepository) {
//        this.jwtService = jwtService;
//        this.appUserRepository = appUserRepository;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String tokenHeader = request.getHeader("Authorization");
////        System.out.println(tokenHeader);
//        if(tokenHeader!=null && tokenHeader.startsWith("Bearer ")){
//            String token = tokenHeader.substring(8 ,tokenHeader.length()-1);
//            String name = jwtService.getUserName(token);
//            System.out.println(name);
//            Optional<AppUser> opUser = appUserRepository.findByUsername(name);
//            if(opUser.isPresent()){
//                AppUser appUser=opUser.get();
////            To track current user logged in
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(appUser,null, Collections.singleton(new SimpleGrantedAuthority(appUser.getUserRole())));
////                adani set new world record
//                authentication.setDetails(new WebAuthenticationDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//
//        }
//        filterChain.doFilter(request,response);
//
//    }
//}


//package com.home.Config;
//
//import com.home.Service.JWTService;
//import com.home.entity.AppUser;
//import com.home.repository.AppUserRepository;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Optional;
//
//@Component
//public class JWTResponseFilter extends OncePerRequestFilter {
//
//    private final JWTService jwtService;
//    private final AppUserRepository appUserRepository;
//
//    public JWTResponseFilter(JWTService jwtService, AppUserRepository appUserRepository) {
//        this.jwtService = jwtService;
//        this.appUserRepository = appUserRepository;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String tokenHeader = request.getHeader("Authorization");
//
//        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
//            String token = tokenHeader.substring(7); // Extracting token without "Bearer "
//
//            try {
//                String username = jwtService.getUserName(token);
//                System.out.println(username);
//                Optional<AppUser> user = appUserRepository.findByUsername(username);
//
//                if (user.isPresent()) {
//                    AppUser appUser = user.get();
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                            appUser, null, Collections.singleton(new SimpleGrantedAuthority(appUser.getUserRole()))
//                    );
//                    authentication.setDetails(new WebAuthenticationDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            } catch (Exception e) {
//                // Log the exception if needed and handle the invalid token scenario
//                logger.error("Invalid JWT token", e);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
