package org.iesvdm.test_spring_data_jpa.repo;

import org.iesvdm.test_spring_data_jpa.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    record IdDTO(Long id){};

    List<IdDTO> findDistinctByPedidos_IdIsNotNull();

    record IdNomApell1 (Long id, String nombre, String apellido1){};

    List<IdNomApell1> findByApellido2IsNotNullOrderByApellido1AscApellido2AscNombreAsc();

    @Query("select c.id, c.nombre,c.apellido1 from Cliente c " +
            "where c.apellido2 is not null " +
            "order by c.apellido1, c.apellido2,c.nombre")
    List<IdNomApell1> obtenerIdNomApelli1();
}
