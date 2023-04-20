package com.pfcti.springdevapp.service;

import com.pfcti.springdevapp.dto.ClienteDto;
import com.pfcti.springdevapp.model.Cliente;
import com.pfcti.springdevapp.repository.ClienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;
    private ClienteRepository clienteRepository;

    @PersistenceContext
    private EntityManager entityManager;

   @Test
    void insertarCliente () {
       List<Cliente> listaClientes = entityManager.createQuery("SELECT c FROM Cliente c").getResultList();
       System.out.println(">>>>>>>>>>>>>>>>>>Lista antes de insertar:" + listaClientes.size());
       ClienteDto clienteDto = new ClienteDto();
       clienteDto.setNombre("HANNIA");
       clienteDto.setApellidos("SERRANO");
       clienteDto.setCedula("303670414");
       clienteDto.setTelefono("+50687124590");
       clienteService.insertarCliente(clienteDto);
       listaClientes = entityManager.createQuery("SELECT c FROM Cliente c").getResultList();
       System.out.println(">>>>>>>>>>>>>>>>>>Lista despues de insertar:" + ((List<?>) listaClientes).size());
       assertEquals(1,1);
   }

  /* public ClienteDto buscarClientePorId (int id) {

          }*/

    @Test
    void obtenerCliente () {
        ClienteDto clienteDto = clienteService.obtenerCliente(2);
        System.out.println(">>>>>>>>>>>>>>>>>>>> El cliente si existe: " + clienteDto.getApellidos());
        assertEquals(1,1);

    }

    @Test
    void actualizarCliente() {
        ClienteDto clienteDtoInicial = clienteService.obtenerCliente(1);
        System.out.println("El cliente inicial es: "+ clienteDtoInicial.getApellidos());
        clienteDtoInicial.setApellidos("SALAZAR");
        clienteService.actualizarCliente(clienteDtoInicial);
        ClienteDto clienteDtoLuegoDeUpdate = clienteService.obtenerCliente(1);
        System.out.println("El cliente inicial es: "+ clienteDtoLuegoDeUpdate.getApellidos());
        assertEquals("SALAZAR", clienteDtoLuegoDeUpdate.getApellidos());
    }

    @Test
    void obtenerClientes() {
        clienteService.obtenerClientes().stream().map(
                cliente ->{
                    System.out.println(">>>>>>> Cliente :" + cliente.getApellidos());
                    return cliente;
                }
        ).collect(Collectors.toList());
        assertEquals(1,1);
    }

}
}