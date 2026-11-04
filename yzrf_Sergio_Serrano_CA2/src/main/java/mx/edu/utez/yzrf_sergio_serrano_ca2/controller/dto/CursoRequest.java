package mx.edu.utez.yzrf_sergio_serrano_ca2.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import mx.edu.utez.yzrf_sergio_serrano_ca2.model.enums.TiposEstadoCurso;

import java.time.LocalDate;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CursoRequest {
    @NotNull(message = "el nombre del curso no puede estar vacio")
    private String nombre;

    private String descripcion;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaIn;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechafin;

}
