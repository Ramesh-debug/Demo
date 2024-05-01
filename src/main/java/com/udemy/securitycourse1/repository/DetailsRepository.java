package com.udemy.securitycourse1.repository;

import com.udemy.securitycourse1.entity.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailsRepository extends JpaRepository<Details,Integer> {
    public Optional<Details> findByUserName(String userName);
}
