package com.yeranosyans.api.rest.controllers.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    //region Logger instance
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckController.class);
    //endregion

    //region API
    @RequestMapping("/health")
    public String getHealthStatus() {
        LOGGER.debug("Processing health check request");
        return "ALIVE";
    }
    //endregion
}
