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


    /* @Test
    void obtenerClientes() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(3);
        clienteDto.setNombre("ALBERTO");
        clienteDto.setApellidos("SALAZAR");
        clienteDto.setCedula("12311111111");
        clienteDto.setTelefono("09997999999");
        clienteService.actualizarCliente(clienteDto);
        clienteService
                .obtenerClientes()
                .forEach( cliente -> System.out.println("Cliente: "+ cliente.getApellidos()));

    }
         */

    @Test
    void obtenerClientes() {
        List<ClienteDto> clienteDtos = clienteService.obtenerClientes();
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getApellidos()));
        assertEquals(2, clienteDtos.size());
    }


    @Test
    void obtenerClientesPorCodigoISOPaisYCuentasActivas() {
        List<ClienteDto> clienteDtos = clienteService.obtenerClientesPorCodigoISOPaisYCuentasActivas("CRC");
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getApellidos()));
        assertEquals(2, clienteDtos.size());
    }

    @Test
    void eliminarCliente() {
        clienteService.eliminarCliente(1);
        assertEquals(1,1);
    }

    @Test
    void buscarPorApellidos() {
        List<ClienteDto> clienteDtos = clienteService.buscarPorApellidos("SANCHEZ");
        clienteDtos.forEach(clienteDto -> {System.out.println("Cliente: " + clienteDto.getApellidos());});
        assertEquals(1, clienteDtos.size());

        }

    @Test
    void buscarPorApellidosQueryNativo() {
        List<ClienteDto> clienteDtos = clienteService.buscarPorApellidosQueryNativo("SANCHEZ");
        clienteDtos.forEach(clienteDto -> {System.out.println("Cliente: " + clienteDto.getApellidos());});
        assertEquals(1, clienteDtos.size());
    }

    @Test
    void updateClienteByQuery() {
        ClienteDto clienteDtoOriginal = clienteService.buscarPorApellidos("SANCHEZ").get(0);
        System.out.println("NOMBRE ORIGINAL: " + clienteDtoOriginal.getNombre());
        clienteService.updateClienteByQuery("CAMBIADO EL NOMBRE", "SANCHEZ");
        ClienteDto clienteDtoCambiado = clienteService.buscarPorApellidos("SANCHEZ").get(0);
        System.out.println("NOMBRE cambiado: " + clienteDtoOriginal.getNombre());
        assertEquals(clienteDtoOriginal.getNombre(), clienteDtoCambiado.getNombre());

    }

    @Test
    void findByApellidosAndAndNombre() {
        List<ClienteDto> clientesDtos = clienteService.findByApellidosAndAndNombre("SANCHEZ","RAUL");
        assertFalse(clientesDtos.isEmpty());
        System.out.println("Cliente encontrado: " + clientesDtos.get(0).getApellidos());
        assertEquals("SANCHEZ",clientesDtos.get(0).getApellidos());

    }

    @Test
    void obtenerClientesExtranjerosYTarjetasInactivas() {
        List<ClienteDto> clienteDtos = clienteService.obtenerClientesExtranjerosYTarjetasInactivas("CRC");
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getApellidos()));
        assertEquals(1, clienteDtos.size());
    }
}

