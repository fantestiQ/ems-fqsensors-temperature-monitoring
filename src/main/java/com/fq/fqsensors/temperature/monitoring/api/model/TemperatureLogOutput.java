package com.fq.fqsensors.temperature.monitoring.api.model;

import com.fq.fqsensors.temperature.monitoring.domain.model.TemperatureLog;
import io.hypersistence.tsid.TSID;
import lombok.Builder;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public record TemperatureLogOutput (
    UUID id,
    TSID sensorId,
    OffsetDateTime registeredAt,
    Double value
    ){
    public static TemperatureLogOutput create(TemperatureLog input){
        return TemperatureLogOutput.builder()
                .id(input.getId().getValue())
                .sensorId(input.getSensorId().getValue())
                .registeredAt(input.getRegisteredAt())
                .value(input.getValue())
                .build();
    }
}
