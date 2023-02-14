package com.iot.data_server.repositories;

import com.iot.data_server.entities.DataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDataRepository
        extends JpaRepository<DataEntity, Long> {
    @Override
    Page<DataEntity> findAll(Pageable pageable);

}
