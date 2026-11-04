package mx.edu.utez.yzrf_sergio_serrano_ca2.model;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;
import mx.edu.utez.yzrf_sergio_serrano_ca2.model.enums.TiposEstadoCurso;

import java.time.LocalDate;

@Entity
@Table(name = "curso")
@Data
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private LocalDate fechaIn;
    private LocalDate fechafin;

    @Enumerated(value = EnumType.STRING)
    private TiposEstadoCurso estado;
}
