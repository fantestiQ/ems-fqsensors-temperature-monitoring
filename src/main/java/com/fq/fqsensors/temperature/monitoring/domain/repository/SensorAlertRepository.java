package com.fq.fqsensors.temperature.monitoring.domain.repository;

import com.fq.fqsensors.temperature.monitoring.domain.model.SensorAlert;
import com.fq.fqsensors.temperature.monitoring.domain.model.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorAlertRepository extends JpaRepository<SensorAlert, SensorId> {
}
