package com.fq.fqsensors.temperature.monitoring.api.model;

import com.fq.fqsensors.temperature.monitoring.domain.model.SensorMonitoring;
import io.hypersistence.tsid.TSID;

import java.time.OffsetDateTime;

public record SensorMonitoringOutput(
        TSID id,
        Double lastTemperature,
        OffsetDateTime updateAt,
        Boolean enabled
) {
    public static SensorMonitoringOutput create(SensorMonitoring input){
        return new SensorMonitoringOutput(input.getId().getValue(), input.getLastTemperature(),
                input.getUpdatedAt(),input.getEnebled());
    }
}
