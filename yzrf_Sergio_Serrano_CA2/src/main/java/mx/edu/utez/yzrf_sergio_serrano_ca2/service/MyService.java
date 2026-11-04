package mx.edu.utez.yzrf_sergio_serrano_ca2.service;

import mx.edu.utez.yzrf_sergio_serrano_ca2.controller.dto.PonerCalReq;
import mx.edu.utez.yzrf_sergio_serrano_ca2.controller.dto.RequestInscribir;
import mx.edu.utez.yzrf_sergio_serrano_ca2.exception.custome.CustomException;
import mx.edu.utez.yzrf_sergio_serrano_ca2.model.Curso;
import mx.edu.utez.yzrf_sergio_serrano_ca2.model.Inscripcion;
import mx.edu.utez.yzrf_sergio_serrano_ca2.model.enums.EstadoDeInscripcion;
import mx.edu.utez.yzrf_sergio_serrano_ca2.model.enums.TiposEstadoCurso;
import mx.edu.utez.yzrf_sergio_serrano_ca2.repository.CursoRepository;
import mx.edu.utez.yzrf_sergio_serrano_ca2.repository.InscripcionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class MyService {
    private final CursoRepository cursoR;
    private final InscripcionRepository inscripcionR;

    public MyService(CursoRepository cursoR, InscripcionRepository inscripcionR) {
        this.cursoR = cursoR;
        this.inscripcionR = inscripcionR;
    }
    @Transactional
    public Inscripcion inscribir(Long idCurso, RequestInscribir payload){
        Curso cursor = cursoR.findById(idCurso).orElseThrow(() -> new CustomException("El curso no existe"));
        if (!(cursor.getEstado() == TiposEstadoCurso.DISPONIBLE)){
            throw new CustomException("El Curso no esta disponible");
        }
        Inscripcion nuevaInscripcion = new Inscripcion();
        LocalDate dia = LocalDate.now();
        nuevaInscripcion.setNombrePar(payload.getNombrePar());
        nuevaInscripcion.setCorreo(payload.getCorreoM());
        nuevaInscripcion.setEdadPar(payload.getEdadPar());
        nuevaInscripcion.setEstado(EstadoDeInscripcion.ACTIVA);
        nuevaInscripcion.setFechaIns(dia);
        nuevaInscripcion.setCurso(cursor);

        return inscripcionR.save(nuevaInscripcion);
    }
    @Transactional
    public Inscripcion cancelar(Long idInscripcion){
        Inscripcion inscripcion = inscripcionR.findById(idInscripcion).orElseThrow(() -> new CustomException("La inscripcion no existe"));
        if (!(inscripcion.getEstado() == EstadoDeInscripcion.ACTIVA)){
            throw new CustomException("La inscripcion no puede ser cancelada o ya fue cancelada");
        }
        Curso cursor = cursoR.findById(inscripcion.getCurso().getId()).orElseThrow(() -> new CustomException("El curso no existe"));
        if (!(cursor.getEstado() == TiposEstadoCurso.EN_CURSO)){
            throw new CustomException("El Curso no esta disponible");
        }
        inscripcion.setEstado(EstadoDeInscripcion.CANCELADA);
        return inscripcionR.save(inscripcion) ;

    }
    @Transactional
    public Inscripcion ponercal(PonerCalReq dto){
        Inscripcion inscripcion = inscripcionR.findById(dto.getId()).orElseThrow(() -> new CustomException("La inscripcion no existe"));
        if (!(inscripcion.getEstado() == EstadoDeInscripcion.ACTIVA)){
            throw new CustomException("La inscripcion no esta activa");
        }
        Curso cursor = cursoR.findById(inscripcion.getCurso().getId()).orElseThrow(() -> new CustomException("El curso no existe"));
        if (!(cursor.getEstado() == TiposEstadoCurso.EN_CURSO) || !(cursor.getEstado() == TiposEstadoCurso.DISPONIBLE)){
            throw new CustomException("El Curso no esta disponible");
        }
        if (!(dto.getCal() >= 0 && dto.getCal() <=10)){
            throw new CustomException("La calificacion debe estar entre 0 y 10");
        }
        inscripcion.setCal(dto.getCal());
        inscripcion.setEstado(EstadoDeInscripcion.COMPLETADA);
        return  inscripcionR.save(inscripcion);
    }
}
