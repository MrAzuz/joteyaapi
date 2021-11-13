package com.joteya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joteya.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
