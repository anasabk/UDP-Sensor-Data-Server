package com.iot.data_server.services;

import com.iot.data_server.entities.DataEntity;
import com.iot.data_server.repositories.SensorDataRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.MILLIS;

@Service
@RequiredArgsConstructor
public class UDPServer implements MessageHandler {
    private int i = 0;
    private LocalTime time = LocalTime.now();

    private final SensorDataService sensorDataService;

    @ServiceActivator
    public void handleMessage(Message message) {
        String input = new String((byte[]) message.getPayload());
        String[] split = input.split(",");
        DataEntity data = new DataEntity(
                Long.parseLong(split[1]),
                LocalTime.parse(split[0]),
                Float.parseFloat(split[2]),
                Float.parseFloat(split[3]),
                Float.parseFloat(split[4]),
                Float.parseFloat(split[5]),
                Float.parseFloat(split[6]),
                Float.parseFloat(split[7]),
                Float.parseFloat(split[8])
        );

//        System.out.print(data.toString() + i + "\n");

        sensorDataService.saveData(data);

        i++;
        if(i == 100) {
            i = 0;
            System.out.print(MILLIS.between(time, LocalTime.now()) + " milliseconds\n");
            time = LocalTime.now();
        }

//        try {
//            TimeUnit.MILLISECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        UnicastSendingMessageHandler handler =
//                new UnicastSendingMessageHandler(
//                        Objects.requireNonNull(message.getHeaders().get("ip_address")).toString(),
//                        (int) message.getHeaders().get("ip_port"));
//        String payload = "OK";
//        handler.handleMessage(MessageBuilder.withPayload(payload).build());
    }
}