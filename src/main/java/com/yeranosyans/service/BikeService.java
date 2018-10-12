package com.yeranosyans.service;

import com.yeranosyans.model.Bike;
import com.yeranosyans.model.dto.BikeDto;

import java.util.List;

public interface BikeService {

    /**
     * Created bike for provided dto
     * @see Bike
     * @see BikeDto
     *
     * @param  bikeDto
     * @return bike
     */
    Bike create(final BikeDto bikeDto);

    /**
     * Retrieves all available bikes
     *
     * @return list of bikes
     */
    List<Bike> getAll();

    /**
     * Retrieves single bike for id
     *
     * @param  id
     * @return bike
     */
    Bike getById(final Long id);

    /**
     * Updates bike for provided dto
     *
     * @param id
     * @param bikeDto
     * @return
     */
    Bike update(final Long id,final BikeDto bikeDto);


    /**
     * Removes bike with provided id
     *
     * @param id
     */
    void remove(final Long id);


}
