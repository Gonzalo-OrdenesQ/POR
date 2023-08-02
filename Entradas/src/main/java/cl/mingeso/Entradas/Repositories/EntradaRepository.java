package cl.mingeso.Entradas.Repositories;

import cl.mingeso.Entradas.Entities.EntradaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EntradaRepository extends JpaRepository<EntradaEntity,Integer> {

    @Query(value = "SELECT * FROM EntradaEntity e WHERE (DATE(e.date) BETWEEN :inicio AND :fin ORDENR BY DATE(e.date) ASC ", nativeQuery = true)
    List<EntradaEntity> findByDates(@Param("inicio") String inicio, @Param("fin") String dificultad);
}
