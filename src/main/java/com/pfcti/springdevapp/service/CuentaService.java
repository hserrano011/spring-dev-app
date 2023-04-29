package com.pfcti.springdevapp.service;

import com.pfcti.springdevapp.criteria.CuentaSpecification;
import com.pfcti.springdevapp.dto.ClienteDto;
import com.pfcti.springdevapp.dto.CuentaDto;
import com.pfcti.springdevapp.dto.NotificacionDto;
import com.pfcti.springdevapp.jms.publishers.NotificationPubSubSender;
import com.pfcti.springdevapp.jms.senders.NotificationSender;
import com.pfcti.springdevapp.model.Cliente;
import com.pfcti.springdevapp.model.Cuenta;
import com.pfcti.springdevapp.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CuentaService {
    private CuentaRepository cuentaRepository;
    private CuentaSpecification cuentaSpecification;
    private NotificationSender notificationSender;
    private ClienteService clienteService;
    private NotificationPubSubSender notificationPubSubSender;


    private CuentaDto fromCuentaToCuentaDto(Cuenta cuenta) {
        CuentaDto cuentaDto = new CuentaDto();
        BeanUtils.copyProperties(cuenta, cuentaDto);
        return cuentaDto;
    }

    public List<CuentaDto> busquedaDinamicamentePorCriterios(CuentaDto cuentaDtoFilter){
        return cuentaRepository.findAll(cuentaSpecification.buildFilter(cuentaDtoFilter))
                .stream()
                .map(this::fromCuentaToCuentaDto)
                .collect(Collectors.toList());
    }


    public void insertarCuenta (CuentaDto cuentaDto, int clienteId){
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cuenta.setCliente(cliente);
        cuenta.setTipo(cuentaDto.getTipo());
        cuenta.setNumero(cuentaDto.getNumero());
        cuenta.setEstado(cuentaDto.getEstado());
        cuentaRepository.save(cuenta);

    }

    private Cuenta fromDtoToCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();
        BeanUtils.copyProperties(cuentaDto, cuenta);
        Cliente cliente = new Cliente();
        cliente.setId(cuentaDto.getClienteId());
        cuenta.setCliente(cliente);
        return cuenta;
    }

    public void creacionDeCuenta(CuentaDto cuentaDto){
        Cuenta cuenta = fromDtoToCuenta(cuentaDto);
        cuentaRepository.save(cuenta);
        //log.info("Cuenta: {} ", cuenta);
    }

    public void desactivarCuenta(CuentaDto cuentaDto){
        Cuenta cuenta =
                cuentaRepository.findById(cuentaDto.getId()).orElseThrow(()->
                {throw new RuntimeException("No existe");});
        cuenta.setId((cuentaDto.getId()));
        cuenta.setEstado(false);
        cuentaRepository.save(cuenta);
    }

    public List<CuentaDto>buscarCuentas (int id){
        List<CuentaDto> cuentaDtos = new ArrayList<>();
        List<Cuenta> cuentas =  cuentaRepository.findCuentaByCliente_Id(id);
        cuentas.forEach(cuenta -> { cuentaDtos.add(fromCuentaToCuentaDto(cuenta)); });
    return cuentaDtos;
    }

    public void sendNotification(CuentaDto cuentaDto) {
        ClienteDto clienteDto = clienteService.obtenerCliente(cuentaDto.getClienteId());
        NotificacionDto notificacionDto = new NotificacionDto();
        notificacionDto.setPhoneNumber(clienteDto.getTelefono());
        notificacionDto.setMailBody(getMailBody(cuentaDto, clienteDto));
        this.notificationSender.sendMail(notificacionDto);
    }

    private static String getMailBody(CuentaDto cuentaDto, ClienteDto clienteDto) {
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("Estimado ");
        bodyBuilder.append(clienteDto.getNombre());
        bodyBuilder.append(" ");
        bodyBuilder.append(clienteDto.getApellidos());
        bodyBuilder.append(" tu cuenta número ");
        bodyBuilder.append(cuentaDto.getNumero());
        bodyBuilder.append(" se ha creado con éxito.");
        return bodyBuilder.toString();

    }

    public void creacionDeCuentaYNotificacion(CuentaDto cuentaDto) {
        creacionDeCuenta(cuentaDto);
        sendNotification(cuentaDto);
    }

    public void sendCreateAccountNotification (CuentaDto cuentaDto) {
        log.info("Empezando envío de notificaciones");
        Message<CuentaDto> message = MessageBuilder.withPayload(cuentaDto).build();
        Message<String> result = notificationPubSubSender.sendNotification(message);
        log.info("Resultado envío notificación: {}", result.getPayload());
    }

    public void creacionDeCuentaYPubSub(CuentaDto cuentaDto) {
        creacionDeCuenta(cuentaDto);
        sendCreateAccountNotification(cuentaDto);
    }

}





