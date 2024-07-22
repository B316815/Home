package com.home.repository;

import com.home.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

   public List<Image> findByPropertyId(Long PropertyId);
}