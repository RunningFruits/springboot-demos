package com.demo.mqtt.producer.handler;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;


@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttMessageGateway {

    void sendMessage(Message<?> message);
}
