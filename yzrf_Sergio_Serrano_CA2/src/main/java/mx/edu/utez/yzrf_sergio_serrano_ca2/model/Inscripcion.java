package mx.edu.utez.yzrf_sergio_serrano_ca2.model;

import jakarta.persistence.*;
import lombok.Data;
import mx.edu.utez.yzrf_sergio_serrano_ca2.model.enums.EstadoDeInscripcion;

import java.time.LocalDate;

@Entity
@Table(name = "inscripcion")
@Data
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombrePar;
    private Integer edadPar;
    private String Correo;
    private LocalDate fechaIns;

    @Enumerated(value = EnumType.STRING)
    private EstadoDeInscripcion estado;

    @Column(nullable = true)
    private Double cal;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;


}
