package com.fq.fqsensors.temperature.monitoring.api.model;


public record SensorAlertInput(
        Double maxTemperature,
        Double minTemperature
) {}
