package com.iot.data_server.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
//@ToString
public class DataEntity {
    @Id
    @Column(name = "id")
    private Long id;

    Date sentAt;
    private float x_accel;
    private float y_accel;
    private float z_accel;
    private float x_gyro;
    private float y_gyro;
    private float z_gyro;
    private float tempr;

    @Override
    public String toString() {
//        final StringBuilder sb = new StringBuilder("DataEntity{");
        final StringBuilder sb = new StringBuilder();
//        sb.append("id=").append(String.format("%4d", id));
//        sb.append(", sentAt=").append(sentAt);

        sb.append("acceleration: ");
        sb.append("x=");
        if(x_accel >= 0.0)
            sb.append(" ");
        sb.append(String.format("%2.4f", x_accel));

        sb.append(", y=");
        if(y_accel >= 0.0)
            sb.append(" ");
        sb.append(String.format("%2.4f", y_accel));

        sb.append(", z=");
        if(z_accel >= 0.0)
            sb.append(" ");
        sb.append(String.format("%2.4f", z_accel));

        sb.append(" / rotation: ");
        sb.append("x=");
        if(x_gyro >= 0.0)
            sb.append(" ");
        sb.append(String.format("%2.4f", x_gyro));

        sb.append(", y=");
        if(y_gyro >= 0.0)
            sb.append(" ");
        sb.append(String.format("%2.4f", y_gyro));

        sb.append(", z=");
        if(z_gyro >= 0.0)
            sb.append(" ");
        sb.append(String.format("%2.4f", z_gyro));

        sb.append(", tempr=");
        if(tempr >= 0.0)
            sb.append(" ");
        sb.append(String.format("%2.4f", tempr));
        
        sb.append('}');
        return sb.toString();
    }
}
