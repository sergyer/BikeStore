package com.yeranosyans.api.rest.controllers.bike;

import com.yeranosyans.api.facade.bike.BikeControllerFacade;
import com.yeranosyans.api.facade.bike.model.CreateBikeModel;
import com.yeranosyans.api.facade.bike.model.UpdateBikeModel;
import com.yeranosyans.api.facade.bike.model.ViewBikeModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/server/api/v1/bikes")
@Api(description = "Bike registration system api")
public class BikeController {

    //region Dependencies
    @Autowired
    private BikeControllerFacade bikeControllerFacade;
    //endregion


    //region API
    @GetMapping
    @ApiOperation(value = "Get all bikes", response = ViewBikeModel.class, responseContainer = "List")
    public List<ViewBikeModel> getBikes() {
        return bikeControllerFacade.getAll();
    }

    @ApiOperation(value = "Get bike by id", response = ViewBikeModel.class)
    @GetMapping("/{id}")
    public ViewBikeModel getBike(@PathVariable final Long id) {
        return bikeControllerFacade.getBike(id);
    }

    @ApiOperation(value = "Create bike")
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> createBike(@RequestBody final CreateBikeModel bike) {
        final ViewBikeModel viewBikeModel = bikeControllerFacade.createBike(bike);
        return ResponseEntity.created(URI.create(viewBikeModel.getId().toString())).build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update bike by id", response = ViewBikeModel.class)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ViewBikeModel updateBike(@PathVariable final Long id, @RequestBody final UpdateBikeModel bike) {
        return bikeControllerFacade.updateBike(id, bike);
    }

    @ApiOperation(value = "Delete bike by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBike(@PathVariable final Long id) {
        bikeControllerFacade.remove(id);
    }
    //endregion

    //region Private utility methods
    @ExceptionHandler
    private ResponseEntity<String> handleException(final RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    //endregion

}
