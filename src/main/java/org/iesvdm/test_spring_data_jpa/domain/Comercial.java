package org.iesvdm.test_spring_data_jpa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Comercial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id--> sera auto_increment / en preostgres serial
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 100)
    private String nombre;

    @Column(length = 100)
    private String apellido1;

    @Column(length = 100)
    private String apellido2;


    private String ciudad;

    @Column(precision = 10, scale = 2) //precision el numero total de digitos posible y scale el nuemro de decimales totales
    private BigDecimal comision;

    //LADO INVERSO, EL QUE NO TIENE FK, EL QUE TIENE mappedBy
    @OneToMany(mappedBy = "comercial")
    @Builder.Default
    @ToString.Exclude
    private Set<Pedido> pedidos = new HashSet<>();
}
