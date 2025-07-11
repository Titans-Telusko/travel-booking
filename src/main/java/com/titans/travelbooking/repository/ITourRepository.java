package com.titans.travelbooking.repository;

import com.titans.travelbooking.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ITourRepository extends JpaRepository<Tour,Integer> {

    Optional<Tour> findByVacationNameIgnoreCase(String vacationName);
}
