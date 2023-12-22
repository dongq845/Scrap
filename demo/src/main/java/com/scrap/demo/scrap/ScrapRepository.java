package com.scrap.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scrap.demo.entity.Scrap;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    // Custom Methods
    List<Scrap> findByUserID(Long userID); // Retrieves all scraps belonging to userID
}
