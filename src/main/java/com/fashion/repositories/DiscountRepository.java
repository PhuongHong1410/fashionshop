package com.fashion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fashion.models.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer>{

}
