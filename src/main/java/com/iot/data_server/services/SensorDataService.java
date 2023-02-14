package com.iot.data_server.services;

import com.iot.data_server.entities.DataEntity;
import com.iot.data_server.repositories.SensorDataRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SensorDataService {
    private final SensorDataRepository sensorDataRepository;

    public void saveData(DataEntity data) {
        sensorDataRepository.save(data);
    }

    public List<DataEntity> ListAll() {
        return sensorDataRepository.findAll();
    }

    public void clearData() {
        sensorDataRepository.deleteAll();
    }
}
