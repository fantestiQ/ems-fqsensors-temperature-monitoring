package com.fq.fqsensors.temperature.monitoring.api.controller;

import com.fq.fqsensors.temperature.monitoring.api.model.SensorAlertInput;
import com.fq.fqsensors.temperature.monitoring.api.model.SensorAlertOutput;
import com.fq.fqsensors.temperature.monitoring.domain.model.SensorAlert;
import com.fq.fqsensors.temperature.monitoring.domain.model.SensorId;
import com.fq.fqsensors.temperature.monitoring.domain.repository.SensorAlertRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/sensors/{sensorId}/alert")
@RequiredArgsConstructor
public class SensorAlertController {

    private final SensorAlertRepository repository;

    @GetMapping
    public ResponseEntity<SensorAlertOutput> search(@PathVariable TSID sensorId){
      SensorAlert alert = repository.findById(new SensorId(sensorId))
              .orElseThrow( () ->  new  ResponseStatusException(HttpStatus.NOT_FOUND));
      return ResponseEntity.ok(SensorAlertOutput.create(alert));
    }

    @PutMapping
    public ResponseEntity<SensorAlertOutput> update(@PathVariable TSID sensorId, @RequestBody SensorAlertInput input){
        Optional<SensorAlert> alert = repository.findById(new SensorId(sensorId));

        SensorAlert presentAlert;

        if(alert.isPresent()){
           presentAlert = alert.get();
           presentAlert.setMaxTemperature(input.maxTemperature());
           presentAlert.setMinTemperature(input.minTemperature());
       }else {
            presentAlert = SensorAlert.builder()
                   .id(new SensorId(sensorId))
                   .minTemperature(input.minTemperature())
                   .maxTemperature(input.maxTemperature())
                   .build();
       }

        SensorAlertOutput output = SensorAlertOutput
                .create(repository.saveAndFlush(presentAlert));


        return ResponseEntity.ok(output);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable TSID sensorId){
        SensorAlert sensor = repository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.delete(sensor);
    }
}
