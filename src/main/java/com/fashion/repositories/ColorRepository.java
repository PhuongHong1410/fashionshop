package com.fashion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fashion.models.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer>{

}
