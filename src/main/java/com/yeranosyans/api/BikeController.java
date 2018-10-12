package com.yeranosyans.api;

import com.yeranosyans.model.Bike;
import com.yeranosyans.model.dto.BikeDto;
import com.yeranosyans.service.BikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bikes")
@Api(description = "Bike registration system api")
public class BikeController {

    //region Dependencies
    @Autowired
    private BikeService bikeService;
    //endregion


    //region API
    @GetMapping
    @ApiOperation(value = "Get all bikes", response = Bike.class, responseContainer = "List")
    public List<Bike> getBikes() {
        return bikeService.getAll();
    }

    @ApiOperation(value = "Get bike by id", response = Bike.class)
    @GetMapping("/{id}")
    public Bike getBike(@PathVariable final Long id) {
        return bikeService.getById(id);
    }

    @ApiOperation(value = "Create bike")
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> createBike(@RequestBody final BikeDto bikeDto) {
        final Bike bike = bikeService.create(bikeDto);
        return ResponseEntity.created(URI.create(bike.getId().toString())).build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update bike by id", response = Bike.class)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Bike updateBike(@PathVariable final Long id, @RequestBody final BikeDto bikeDto) {
        return bikeService.update(id, bikeDto);
    }

    @ApiOperation(value = "Delete bike by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBike(@PathVariable final Long id) {
        bikeService.remove(id);
    }
    //endregion
}
