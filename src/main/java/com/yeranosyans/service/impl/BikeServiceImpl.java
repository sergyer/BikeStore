package com.yeranosyans.service.impl;

import com.yeranosyans.model.Bike;
import com.yeranosyans.model.dto.BikeDto;
import com.yeranosyans.persistence.BikeRepository;
import com.yeranosyans.service.BikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

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
    public Bike create(final BikeDto bikeDto) {
        Assert.notNull(bikeDto, "Argument bikeDto should not be null");
        LOGGER.debug("Creating bike for provided dto '{}'", bikeDto);
        //Create
        Bike bike = new Bike();
        bikeDto.updateDomainEntityProperties(bike);
        bike.setCreatedOn(LocalDateTime.now());
        bike.setUpdatedOn(bike.getCreatedOn());
        //Persist
        bike = bikeRepository.save(bike);
        LOGGER.debug("Successfully created bike '{}' for dto '{}'", bike, bikeDto);
        return bike;
    }

    @Override
    public Bike getById(final Long id) {
        Assert.notNull(id, "Argument id should not be null");
        LOGGER.debug("Retrieving bike for id '{}'", id);
        final Bike bike = bikeRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Could not find bike with id %d", id)));
        LOGGER.debug("Successfully retrieved bike '{}' for id '{}'", bike, id);
        return bike;
    }

    @Override
    public List<Bike> getAll() {
        LOGGER.debug("Retrieving all bikes");
        List<Bike> result = bikeRepository.findAll();
        LOGGER.debug("Successfully retrieved bikes '{}'", result);
        return result;
    }

    @Transactional
    @Override
    public Bike update(final Long id, final BikeDto bikeDto) {
        Assert.notNull(id, "Argument id should not be null");
        Assert.notNull(bikeDto, "Argument bikeDto should not be null");
        LOGGER.debug("Updating bike with id '{}' for provided dto '{}'", id, bikeDto);
        //Retrieve
        Bike bike = getById(id);
        //Update
        bikeDto.updateDomainEntityProperties(bike);
        bike.setUpdatedOn(LocalDateTime.now());
        //Persist
        bike = bikeRepository.save(bike);
        LOGGER.debug("Successfully updated bike '{}' for provided dto '{}'", bike, bikeDto);
        return bike;
    }

    @Transactional
    @Override
    public void remove(final Long id) {
        Assert.notNull(id, "Argument id should not be null");
        final Bike bike = getById(id);
        bikeRepository.delete(bike);
        LOGGER.debug("Successfully deleted bike '{}'", bike);
    }
    //endregion

}
