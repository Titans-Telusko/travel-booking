package com.titans.travelbooking.repository;

import com.titans.travelbooking.entity.Lodging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LodgingRepo extends JpaRepository<Lodging, Integer> {
}
