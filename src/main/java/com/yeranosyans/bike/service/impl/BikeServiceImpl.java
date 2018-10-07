package com.yeranosyans.bike.service.impl;

import com.yeranosyans.bike.model.Bike;
import com.yeranosyans.bike.model.dto.BikeDto;
import com.yeranosyans.bike.persistence.BikeRepository;
import com.yeranosyans.bike.service.BikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class BikeServiceImpl implements BikeService {

    //region Logger instance
    private static final Logger LOGGER = LoggerFactory.getLogger(BikeServiceImpl.class);
    //endregion

    //region Dependencies
    @Autowired
    private BikeRepository bikeRepository;
    //endregion

    //region Service methods
    @Transactional
    @Override
    public Bike create(BikeDto bikeDto) {
        Assert.notNull(bikeDto, "Argument bikeDto should not be null");
        LOGGER.debug("Creating bike for provided dto '{}'", bikeDto);
        //Create
        Bike bike = new Bike();
        bikeDto.updateDomainEntityProperties(bike);
        //Persist
        bike = bikeRepository.save(bike);
        LOGGER.debug("Successfully created bike '{}' for dto '{}'", bike, bikeDto);
        return bike;
    }

    @Override
    public Bike getById(Long id) {
        Assert.notNull(id, "Argument id should not be null");
        LOGGER.debug("Retrieving bike for id '{}'", id);
        final Bike bike = bikeRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Could not find bike with id %d", id)));
        LOGGER.debug("Successfully retrieved bike '{}' for id '{}'", bike, id);
        return bike;
    }

    @Override
    public Bike update(Long id, BikeDto bikeDto) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
    //endregion

}
