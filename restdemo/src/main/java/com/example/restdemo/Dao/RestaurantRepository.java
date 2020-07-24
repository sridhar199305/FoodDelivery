package com.example.restdemo.Dao;

import com.example.restdemo.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
   List<Restaurant> findByLocation(@Param("location") String location);
   Restaurant findByName(String name);
}
