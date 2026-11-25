package com.fq.fqsensors.temperature.monitoring.api.controller;

import com.fq.fqsensors.temperature.monitoring.api.model.SensorMonitoringOutput;
import com.fq.fqsensors.temperature.monitoring.domain.model.SensorId;
import com.fq.fqsensors.temperature.monitoring.domain.model.SensorMonitoring;
import com.fq.fqsensors.temperature.monitoring.domain.repository.SensorMonitoringRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;

@RestController
@RequestMapping("/api/sensors/{sensorId}/monitoring")
@RequiredArgsConstructor
public class SensorMonitoringController {

    private final SensorMonitoringRepository repository;

    @GetMapping
    public SensorMonitoringOutput getDetail(@PathVariable TSID sensorId){
        SensorMonitoring sensorMonitoring = getSensorMonitoring(sensorId);

        return SensorMonitoringOutput.create(sensorMonitoring);
    }

    private SensorMonitoring getSensorMonitoring(TSID sensorId) {
        return repository.findById(new SensorId(sensorId))
                .orElse(SensorMonitoring.builder()
                        .id(new SensorId(sensorId))
                        .enebled(false)
                        .lastTemperature(null)
                        .updatedAt(null)
                        .build());
    }

    @PutMapping("/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable TSID sensorId){
        SensorMonitoring sensorMonitoring = getSensorMonitoring(sensorId);
        if (Boolean.TRUE.equals(sensorMonitoring.getEnebled())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT);
        }
        sensorMonitoring.setEnebled(true);
        repository.saveAndFlush(sensorMonitoring);
    }

    @DeleteMapping("/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SneakyThrows
    public void disable(@PathVariable TSID sensorId){
        SensorMonitoring sensorMonitoring = getSensorMonitoring(sensorId);
        if (Boolean.FALSE.equals(sensorMonitoring.getEnebled())){
            Thread.sleep(Duration.ofSeconds(10));
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT);
        }
        sensorMonitoring.setEnebled(false);
        repository.saveAndFlush(sensorMonitoring);
    }
}
