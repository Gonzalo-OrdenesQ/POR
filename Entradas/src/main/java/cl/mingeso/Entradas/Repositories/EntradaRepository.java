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

    @Query(value = "SELECT * FROM entradas e WHERE (DATE(e.fecha) BETWEEN :inicio AND :fin) ORDER BY DATE(e.fecha) ASC;", nativeQuery = true)
    List<EntradaEntity> findByDates(@Param("inicio") String inicio, @Param("fin") String fin);
}
