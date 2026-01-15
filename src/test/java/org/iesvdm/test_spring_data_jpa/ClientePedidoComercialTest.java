package org.iesvdm.test_spring_data_jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.iesvdm.test_spring_data_jpa.domain.Cliente;
import org.iesvdm.test_spring_data_jpa.domain.Comercial;
import org.iesvdm.test_spring_data_jpa.domain.Pedido;
import org.iesvdm.test_spring_data_jpa.repo.ClienteRepository;
import org.iesvdm.test_spring_data_jpa.repo.ComercialRepository;
import org.iesvdm.test_spring_data_jpa.repo.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ClientePedidoComercialTest {
    @Autowired
    EntityManager em;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ComercialRepository comercialRepository;
    /*
    *Consultas sobre una tabla
    Devuelve un listado con todos los pedidos que se han realizado.
*   Los pedidos deben estar ordenados por la fecha de realización,
*   mostrando en primer lugar los pedidos más recientes.
    * */
    @Test
    void test1(){
        List<Pedido> pedidos = pedidoRepository.findAllByOrderByFechaHoraDesc();

        System.out.println(pedidos);
    }

    /*
    * Devuelve todos los datos de los dos pedidos de mayor valor.
    * */

    @Test
    void test2(){
        List<Pedido> pedidos = pedidoRepository.findTop2ByOrderByCantidadDesc();

        pedidos.forEach(System.out::println);
    }


    /*
    * Devuelve un listado con los identificadores de los clientes
    * que han realizado algún pedido.
    * Tenga en cuenta que no debe mostrar identificadores que estén repetidos.
    * */

    @Test
    void test3(){

        List<ClienteRepository.IdDTO> ids = clienteRepository.findDistinctByPedidos_IdIsNotNull();

        ids.forEach(System.out::println);
    }

    /*
    * Devuelve un listado de todos los pedidos que se
    * realizaron durante el año 2017,
    * cuya cantidad total sea superior a 500€.
    * */
    @Test
    void test4(){
        List<Pedido> pedidos = pedidoRepository.findByFechaHoraBetweenAndCantidadGreaterThan(
                LocalDateTime.of(2017,1,1,0,0),
                LocalDateTime.of(2017,12,31,23,59,59),
                500.0);
        pedidos.forEach(System.out::println);
    }

    /*
    * Devuelve un listado con el nombre y
    * los apellidos de los comerciales que
    * tienen una comisión entre 0.05 y 0.11.
    * */

    @Test
    void test5(){
        List<ComercialRepository.NomApellDTOP> comercials = comercialRepository.findByComisionBetween(
                new BigDecimal("0.05"),
                new BigDecimal("0.11"));
        comercials.forEach(System.out::println);
    }

    /*
    *Devuelve el valor
    * de la comisión de mayor valor que existe en la tabla comercial.
    * */

    @Test
    void test6(){
        BigDecimal comision = comercialRepository.obtenerMayorComision();
        System.out.println(comision);
    }

    /*
    * Devuelve el identificador, nombre y primer apellido
    * de aquellos clientes cuyo segundo apellido no es NULL.
    *  El listado deberá estar ordenado alfabéticamente
    * por apellidos y nombre.
    * */

    @Test
    void test7(){
        List<ClienteRepository.IdNomApell1> list = clienteRepository.obtenerIdNomApelli1();
        list.forEach(System.out::println);

        list = clienteRepository.findByApellido2IsNotNullOrderByApellido1AscApellido2AscNombreAsc();
        list.forEach(System.out::println);

        list = em.createQuery("""
            select c.id, c.nombre,c.apellido1 
            from Cliente c order by c.apellido1, c.apellido2,c.nombre
            """,ClienteRepository.IdNomApell1.class).getResultList();

        list.forEach(System.out::println);
    }
}
