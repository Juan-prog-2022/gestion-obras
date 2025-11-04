package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

}
