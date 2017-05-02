package com.github.peckb1.interview.api.health;

import com.codahale.metrics.health.HealthCheck;

public class MySqlHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {

        // TODO
        return Result.unhealthy("NOT YET IMPLEMENTED");

    }

}
