package com.home.repository;

import com.home.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByEmail(String email);


    @Modifying
    @Transactional
    @Query("update AppUser u set u.password = ?2 where u.email = ?1")
    void updatePassword(String email, String password);
}
