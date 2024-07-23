package com.home.repository;

import com.home.entity.AppUser;
import com.home.entity.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer> {

    @Query("select fp from ForgotPassword fp where fp.otp = ?1 and fp.appUser = ?2")
    Optional<ForgotPassword> findByOtpAndAppUser(Integer otp, AppUser appUser);
}