package mx.edu.utez.yzrf_sergio_serrano_ca2.service.curso;

import mx.edu.utez.yzrf_sergio_serrano_ca2.controller.dto.CursoRequest;
import mx.edu.utez.yzrf_sergio_serrano_ca2.exception.custome.CustomException;
import mx.edu.utez.yzrf_sergio_serrano_ca2.model.Curso;
import mx.edu.utez.yzrf_sergio_serrano_ca2.model.enums.EstadoDeInscripcion;
import mx.edu.utez.yzrf_sergio_serrano_ca2.model.enums.TiposEstadoCurso;
import mx.edu.utez.yzrf_sergio_serrano_ca2.repository.CursoRepository;
import mx.edu.utez.yzrf_sergio_serrano_ca2.repository.InscripcionRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceCurso {
    private final CursoRepository cursoR;
    private final InscripcionRepository inscripcionR;

    public ServiceCurso(CursoRepository cursoR, InscripcionRepository inscripcionR) {
        this.cursoR = cursoR;
        this.inscripcionR = inscripcionR;
    }

    public Curso crearcurso(CursoRequest dto) {
        Curso nuevocurso = new Curso();
        nuevocurso.setNombre(dto.getNombre());
        nuevocurso.setDescripcion(dto.getDescripcion());
        nuevocurso.setFechaIn(dto.getFechaIn());
        nuevocurso.setFechafin(dto.getFechafin());
        nuevocurso.setEstado(TiposEstadoCurso.DISPONIBLE);
        return cursoR.save(nuevocurso);
    }
    public Curso cambiarEStado(Long idCurso){
        Curso curso = cursoR.findById(idCurso).orElseThrow(() -> new CustomException("El curso no existe"));
        if (curso.getEstado() == TiposEstadoCurso.DISPONIBLE){
            curso.setEstado(TiposEstadoCurso.EN_CURSO);
        }else if(curso.getEstado() == TiposEstadoCurso.EN_CURSO){
            curso.setEstado(TiposEstadoCurso.FINALIZADO);
        }
        return  cursoR.save(curso);
    }


}
