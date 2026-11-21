package com.fq.fqsensors.temperature.monitoring.domain.repository;

import com.fq.fqsensors.temperature.monitoring.domain.model.SensorId;
import com.fq.fqsensors.temperature.monitoring.domain.model.SensorMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorMonitoringRepository extends JpaRepository<SensorMonitoring, SensorId> {
}
