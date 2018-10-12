package com.yeranosyans.persistence;

import com.yeranosyans.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike,Long> {
}
