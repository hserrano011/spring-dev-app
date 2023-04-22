package com.pfcti.springdevapp.criteria;

import com.pfcti.springdevapp.dto.ClienteDto;
import com.pfcti.springdevapp.dto.CuentaDto;
import com.pfcti.springdevapp.model.Cliente;
import com.pfcti.springdevapp.model.Cuenta;
import org.springframework.data.jpa.domain.Specification;

import java.text.MessageFormat;
import java.util.Locale;

public class CuentaSpecification {

    public <T> Specification<T> equals (String fieldName, String fieldValue)
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

    public <T> Specification<T> isTrue(String fieldName, Boolean fieldValue) {
        return fieldValue == null ? null :
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.isTrue(root.get(fieldName));
    }


    private Specification<Cuenta> tipoCriteria (CuentaDto cuentaDto){
        return equals ("tipo", cuentaDto.getTipo() );
    }

    private Specification<Cuenta> numeroCriteria (CuentaDto cuentaDto){
        return like ("numero", cuentaDto.getNumero() );
    }

    private Specification<Cuenta> estadoCriteria(CuentaDto cuentaDto) {
        return isTrue("estado", cuentaDto.getEstado());
    }


    public Specification<Cuenta> buildFilter (CuentaDto cuentaDto){
        System.out.println("Busqueda por criterios: " + cuentaDto);
        return Specification
                .where(tipoCriteria(cuentaDto)
                .and(numeroCriteria(cuentaDto))
                        .and(estadoCriteria(cuentaDto)));
    }

}
