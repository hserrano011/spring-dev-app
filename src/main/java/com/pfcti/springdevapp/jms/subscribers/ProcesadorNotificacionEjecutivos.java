package com.pfcti.springdevapp.jms.subscribers;

import com.pfcti.springdevapp.dto.CuentaDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;

@Component
@Slf4j
@AllArgsConstructor
public class ProcesadorNotificacionEjecutivos {

    @ServiceActivator(inputChannel = "pubSubNotification")
    public Message<String> consumirMensajeParaClientes(Message<CuentaDto> message) {
        CuentaDto cuentaDto = message.getPayload();
        log.info("ProcesadorNotificacionEjecutivos -> Enviando notificación a los ejecutivos con la siguiente información : {}", cuentaDto);
        return MessageBuilder.withPayload("Mensaje recibido por ProcesadorNotificacionEjecutivos").build();
    }
}
