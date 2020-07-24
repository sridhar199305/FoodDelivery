
package com.example.restdemo.Dao;

import com.example.restdemo.Entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRespository  extends JpaRepository<MenuItem, Long> {

    Optional<MenuItem> findById(Long id);
    List<MenuItem>  findByMenus(String menu);
 }

