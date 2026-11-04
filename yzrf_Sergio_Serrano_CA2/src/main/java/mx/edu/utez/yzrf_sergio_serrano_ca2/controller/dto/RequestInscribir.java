package mx.edu.utez.yzrf_sergio_serrano_ca2.controller.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RequestInscribir {
    @NotNull(message = "No puede estar vacio el campo id")
    private Long idCurso;
    @NotNull(message = "No puede estar vacio el nombre del participante")
    private String nombrePar;
    @Min(value = 1,message = "La edad debe ser positivia/real ")
    private Integer edadPar;
    @Email
    private String correoM;


}
