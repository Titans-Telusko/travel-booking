package com.titans.travelbooking.repository;

import com.titans.travelbooking.entity.Tour;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITourRepository extends JpaRepository<Tour,Integer> {

    /**
     * Retrieves a tour by its vacation name (case-insensitive).
     *
     * @param vacationName the vacation name to search for
     * @return an optional tour, or an empty optional if not found
     */
    Optional<Tour> findByVacationNameIgnoreCase(String vacationName);

    /**
     * Finds all tours with a number of tickets available greater than the specified value.
     *
     * @param greaterThanZero the minimum number of tickets that should be available
     * @param pageable pagination information
     * @return a list of tours with tickets available greater than the specified value
     */
    List<Tour> findByTicketsAvailableGreaterThan(Integer greaterThanZero, Pageable pageable);
}

