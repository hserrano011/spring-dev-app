package com.pfcti.springdevapp.jms.senders;

import com.pfcti.springdevapp.dto.NotificacionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationSender {

    @Autowired
    private JmsTemplate jmsTemplate;


    public void sendMail (NotificacionDto notificacionDto) {
        jmsTemplate.convertAndSend("smsReceiverJms", notificacionDto);
    }
}
