package mx.edu.utez.yzrf_sergio_serrano_ca2.repository;

import mx.edu.utez.yzrf_sergio_serrano_ca2.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
