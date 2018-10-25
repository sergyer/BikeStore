package com.yeranosyans.api.facade.bike.impl;

import com.yeranosyans.api.facade.bike.BikeControllerFacade;
import com.yeranosyans.api.facade.bike.model.CreateBikeModel;
import com.yeranosyans.api.facade.bike.model.UpdateBikeModel;
import com.yeranosyans.api.facade.bike.model.ViewBikeModel;
import com.yeranosyans.model.Bike;
import com.yeranosyans.model.dto.BikeDto;
import com.yeranosyans.service.BikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BikeControllerFacadeImpl implements BikeControllerFacade {

    //region Logger instance
    private static final Logger LOGGER = LoggerFactory.getLogger(BikeControllerFacadeImpl.class);
    //endregion

    //region Dependencies
    @Autowired
    private BikeService bikeService;
    //endregion

    //region Service methods
    @Override
    public ViewBikeModel getBike(final Long id) {
        LOGGER.debug("Retrieving bike for provided id '{}'", id);
        final Bike bike = bikeService.getById(id);
        final ViewBikeModel result = new ViewBikeModel(bike.getId(), bike.getName(), bike.getModel(), bike.getPurchasePrice(), bike.getSerialNumber());
        LOGGER.debug("Successfully retrieved bike model '{}' for id '{}'", result, id);
        return result;
    }

    @Override
    public ViewBikeModel createBike(final CreateBikeModel bike) {
        LOGGER.debug("Creating bike for provided model '{}'", bike);
        final Bike createdBike = bikeService.create(new BikeDto(bike.getName(), bike.getModel(), bike.getPurchasePrice(), bike.getSerialNumber()));
        final ViewBikeModel result = new ViewBikeModel(createdBike.getId(), createdBike.getName(), createdBike.getModel(), createdBike.getPurchasePrice(), createdBike.getSerialNumber());
        LOGGER.debug("Successfully created bike '{}' for provided model", result, bike);
        return result;
    }

    @Override
    public List<ViewBikeModel> getAll() {
        LOGGER.debug("Retrieving all bikes");
        final List<ViewBikeModel> result = bikeService.getAll()
                .stream()
                .map(bike -> new ViewBikeModel(bike.getId(), bike.getName(), bike.getModel(), bike.getPurchasePrice(), bike.getSerialNumber()))
                .collect(Collectors.toList());
        LOGGER.debug("Successfully retrieved bikes '{}'", result);
        return result;
    }

    @Override
    public ViewBikeModel updateBike(final Long id, final UpdateBikeModel bike) {
        LOGGER.debug("Updating bike for provided model '{}'", bike);
        final Bike updatedBike = bikeService.update(id, new BikeDto(bike.getName(), bike.getModel(), bike.getPurchasePrice(), bike.getSerialNumber()));
        final ViewBikeModel viewBikeModel = new ViewBikeModel(updatedBike.getId(), updatedBike.getName(), updatedBike.getModel(), updatedBike.getPurchasePrice(), updatedBike.getSerialNumber());
        LOGGER.debug("Successfully updated bike '{}' for model '{}'", viewBikeModel, bike);
        return viewBikeModel;
    }

    @Override
    public void remove(final Long id) {
        LOGGER.debug("Removing bike with id '{}'", id);
        bikeService.remove(id);
        LOGGER.debug("Successfully removed bike with id '{}'", id);
    }

    //endregion
}
