package com.project.Covoiturage.repository;

import com.project.Covoiturage.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository <Driver, Long> {

}
