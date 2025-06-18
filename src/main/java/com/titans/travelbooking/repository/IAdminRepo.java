package com.titans.travelbooking.repository;

import com.titans.travelbooking.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepo extends JpaRepository<Admin,Integer> {
}
