package com.yeranosyans.bike.api;

import com.yeranosyans.bike.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bikes")
public class BikeController {

    //region Dependencies
    @Autowired
    private BikeService bikeService;
    //endregion


    //region API


    //endregion
}
