package com.yeranosyans.bike.persistence;

import com.yeranosyans.bike.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike,Long> {
}
