package com.yeranosyans.bike.service;

import com.yeranosyans.bike.model.Bike;
import com.yeranosyans.bike.model.dto.BikeDto;

public interface BikeService {


    Bike create(final BikeDto bikeDto);

    Bike getById(final Long id);

    Bike update(final Long id,final BikeDto bikeDto);

    void remove(final Long id);


}
