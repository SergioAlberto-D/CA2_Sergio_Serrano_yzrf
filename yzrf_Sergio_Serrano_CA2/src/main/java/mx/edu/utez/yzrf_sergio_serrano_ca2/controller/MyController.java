package mx.edu.utez.yzrf_sergio_serrano_ca2.controller;

import jakarta.validation.Valid;
import mx.edu.utez.yzrf_sergio_serrano_ca2.controller.dto.CursoRequest;
import mx.edu.utez.yzrf_sergio_serrano_ca2.controller.dto.PonerCalReq;
import mx.edu.utez.yzrf_sergio_serrano_ca2.controller.dto.RequestInscribir;
import mx.edu.utez.yzrf_sergio_serrano_ca2.model.Curso;
import mx.edu.utez.yzrf_sergio_serrano_ca2.model.Inscripcion;
import mx.edu.utez.yzrf_sergio_serrano_ca2.service.MyService;
import mx.edu.utez.yzrf_sergio_serrano_ca2.service.curso.ServiceCurso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlador")
@CrossOrigin("*")
public class MyController {
    private final MyService servicio;
    private final ServiceCurso cursoSe;

    public MyController(MyService servicio, ServiceCurso cursoSe) {
        this.servicio = servicio;
        this.cursoSe = cursoSe;
    }
    @PostMapping("/{id}")
    public ResponseEntity<Inscripcion> crear(@PathVariable Long id,@RequestBody @Valid RequestInscribir payload){
        return ResponseEntity.status(201).body(servicio.inscribir(id,payload));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Inscripcion> cancelar(@PathVariable Long id){
        return ResponseEntity.status(201).body(servicio.cancelar(id));
    }
    @PostMapping("/curso/crear")
    public ResponseEntity<Curso> crearCurso(@RequestBody CursoRequest payload){
        return  ResponseEntity.status(201).body(cursoSe.crearcurso(payload));
    }
    @PatchMapping("/curso/cambiar/{id}")
    public ResponseEntity<Curso> cambiar(@PathVariable Long id){
        return ResponseEntity.status(201).body(cursoSe.cambiarEStado(id));
    }
    @PatchMapping("/ponerCal")
    public ResponseEntity<Inscripcion> poner (@RequestBody PonerCalReq payload){
        return  ResponseEntity.status(201).body(servicio.ponercal(payload));
    }
}
