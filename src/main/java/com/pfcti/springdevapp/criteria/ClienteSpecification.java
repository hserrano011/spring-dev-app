package com.pfcti.springdevapp.criteria;

import ch.qos.logback.core.net.server.Client;
import com.pfcti.springdevapp.dto.ClienteDto;
import com.pfcti.springdevapp.model.Cliente;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

@Component
public class ClienteSpecification {
    public <T>Specification<T> equals (String fieldName, String fieldValue)
    {
        return fieldValue == null ? null:
                ((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get(fieldName),fieldValue));
    }

    public static <T> Specification <T> like (String fieldName, String fieldValue) {
        if (fieldValue != null) {
            String upperCaseValue = MessageFormat.format("%{0}%", fieldValue.trim()
                    .toUpperCase(Locale.ROOT).replaceAll("", "%"));
            return ((root, query,criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get(fieldName)),upperCaseValue));
        }else{
            return null;

        }
    }

    private Specification<Cliente> apellidoCriteria (ClienteDto clienteDto){
        return like ("apellidos", clienteDto.getApellidos() );
    }

    private Specification<Cliente> nombreCriteria (ClienteDto clienteDto){
        return like ("nombre", clienteDto.getNombre() );
    }

    private Specification<Cliente> cedulaCriteria (ClienteDto clienteDto){
        return equals ("cedula", clienteDto.getCedula() );
    }

    private Specification<Cliente> telefonoCriteria (ClienteDto clienteDto){
        return equals ("telefono", clienteDto.getTelefono() );
    }

    public Specification<Cliente> buildFilter (ClienteDto clienteDto){
        System.out.println("Busqueda por criterios: " + clienteDto);
        return Specification
                .where(apellidoCriteria(clienteDto))
                .and(nombreCriteria(clienteDto))
                .and(cedulaCriteria(clienteDto))
                .and(telefonoCriteria(clienteDto));
    }
}
