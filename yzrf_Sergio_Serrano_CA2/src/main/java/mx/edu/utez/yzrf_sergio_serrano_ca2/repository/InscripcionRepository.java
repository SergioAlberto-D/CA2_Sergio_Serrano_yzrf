package mx.edu.utez.yzrf_sergio_serrano_ca2.repository;

import mx.edu.utez.yzrf_sergio_serrano_ca2.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
}
