package com.pfcti.springdevapp.jms.receivers;

import com.pfcti.springdevapp.dto.NotificacionDto;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationProcessor {

   @JmsListener(destination = "smsReceiverJms")
    public void processMessage(NotificacionDto notificationDto) {
        //Lógica para envío de sms.
        log.info("sms info:{}", notificationDto);
    }
}
