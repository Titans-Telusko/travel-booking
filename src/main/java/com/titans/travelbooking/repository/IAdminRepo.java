package com.titans.travelbooking.repository;

import com.titans.travelbooking.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAdminRepo extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String username);

    @Query(value = "SELECT * from Admin  where  lower( full_name) like  lower(concat('%' , :name ,'%'))" , nativeQuery = true)
    List<Admin> fetchByName(@Param("name") String name);
}
