package com.iot.data_server;

import com.iot.data_server.services.SensorDataService;
import com.iot.data_server.services.UDPServer;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
@EnableIntegration
@AllArgsConstructor
public class DataServerApplication {

    private final SensorDataService sensorDataService;


    public static void main(String[] args) {
        SpringApplication.run(DataServerApplication.class, args);
    }

    @Bean
    public UnicastReceivingChannelAdapter udpIn() {
        UnicastReceivingChannelAdapter adapter = new UnicastReceivingChannelAdapter(8080);
        adapter.setOutputChannelName("udpChannel");
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "udpOut")
    public UnicastSendingMessageHandler handlerOut() {
        return new UnicastSendingMessageHandler("192.168.1.111", 60380);
    }

    @Bean
    public MessageChannel udpChannel() {
        DirectChannel channel = new DirectChannel();
        channel.setFailover(false);
        channel.subscribe(new UDPServer(sensorDataService));
        return channel;
    }

    @Bean
    public MessageChannel udpOut() {
        DirectChannel channel = new DirectChannel();
        channel.setFailover(false);
        channel.subscribe(new UDPServer(sensorDataService));
        return channel;
    }
}
