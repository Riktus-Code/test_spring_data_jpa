package org.iesvdm.test_spring_data_jpa.repo;

import org.iesvdm.test_spring_data_jpa.domain.Comercial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ComercialRepository extends JpaRepository<Comercial,Long> {


    record NomApellDTOP(String nombre, String apellido1, String apellido2){};

    List<NomApellDTOP> findByComisionBetween(BigDecimal inicio, BigDecimal fin);
    //JPQL
    @Query("select max(c.comision) from Comercial c")
    BigDecimal obtenerMayorComision();

    //NATIVO SQL
    @Query(value = "select max(c.comision) from comercial c", nativeQuery = true)
    BigDecimal obtenerMayorComisionNaivo();
}
