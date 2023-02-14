package com.iot.data_server.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DataEntity {
    @Id
    @Column(name = "id")
    private Long id;

    LocalTime sentAt;
    private float x_accel;
    private float y_accel;
    private float z_accel;
    private float x_gyro;
    private float y_gyro;
    private float z_gyro;
    private float tempr;
}
