package com.titans.travelbooking.repository;

import com.titans.travelbooking.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);

    @Query("select  u from Users  u where lower(u.name) like lower(concat('%',:word , '%'))")
    List<Users> searchByName(@Param("word") String word);
}
