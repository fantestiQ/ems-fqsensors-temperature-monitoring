package com.fq.fqsensors.temperature.monitoring.api.model;

import com.fq.fqsensors.temperature.monitoring.domain.model.SensorAlert;
import io.hypersistence.tsid.TSID;
import lombok.Builder;

@Builder
public record SensorAlertOutput(
        TSID id,
        Double maxTemperature,
        Double minTemperature
)  {
    public static SensorAlertOutput create(SensorAlert alert){
        return SensorAlertOutput.builder()
                .id(alert.getId().getValue())
                .maxTemperature(alert.getMaxTemperature())
                .minTemperature(alert.getMinTemperature())
                .build();
    }
}
