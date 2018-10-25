package com.yeranosyans.api.facade.bike;

import com.yeranosyans.api.facade.bike.model.CreateBikeModel;
import com.yeranosyans.api.facade.bike.model.UpdateBikeModel;
import com.yeranosyans.api.facade.bike.model.ViewBikeModel;

import java.util.List;

public interface BikeControllerFacade {

    ViewBikeModel getBike(final Long id);

    ViewBikeModel createBike(final CreateBikeModel bike);

    ViewBikeModel updateBike(final Long id, UpdateBikeModel bike);

    List<ViewBikeModel> getAll();

    void remove(Long id);

}
