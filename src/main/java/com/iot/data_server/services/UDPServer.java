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
import java.util.Date;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.lang.Exception;

import static java.time.temporal.ChronoUnit.MILLIS;

@Service
@RequiredArgsConstructor
public class UDPServer implements MessageHandler {
    private int i = 0;
    private LocalTime time = LocalTime.now();

    private final SensorDataService sensorDataService;
    
    public boolean strcmp(String arg1, String arg2, int len) {
    	for(int i = 0; i < len; i++) {
    		if(arg1.charAt(i) != arg2.charAt(i))
    			return false;
    	}
    	
    	return true;
    }

    @ServiceActivator
    public void handleMessage(Message message) {
        String input = new String((byte[]) message.getPayload());
        
        if(strcmp(input, "Greetings", 9)) {
        	System.out.print("match");
        	UnicastSendingMessageHandler handler = new UnicastSendingMessageHandler(
				Objects.requireNonNull(message.getHeaders().get("ip_address")).toString(), 
				(int) message.getHeaders().get("ip_port")
			);
			
			//UnicastSendingMessageHandler handler =
			//	  new UnicastSendingMessageHandler("192.168.1.107", 62333);

			String payload = "GO";
			handler.handleMessage(MessageBuilder.withPayload(payload).build());
		    
        } else {
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss:SSS");
			
        	String[] split = input.split(",");
		    
		    DataEntity data = null;
		    
		    try {
		    	data = new DataEntity(
					Long.parseLong(split[1]),
					sdf.parse(split[0]),
			        Float.parseFloat(split[2]),
			        Float.parseFloat(split[3]),
			        Float.parseFloat(split[4]),
			        Float.parseFloat(split[5]),
			        Float.parseFloat(split[6]),
			        Float.parseFloat(split[7]),
			        Float.parseFloat(split[8])
				);
		    } catch(Exception e) {}
		    
		    sensorDataService.saveData(data);

		    i++;
		    if(i == 10) {
		        i = 0;
		        System.out.print(data + "\n");
		    }
        }
    }
}
