package cl.mingeso.Salidas.Repositories;

import cl.mingeso.Salidas.Entities.SalidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SalidaRepository extends JpaRepository<SalidaEntity, Integer> {

    @Query(value = "SELECT * FROM SalidaEntity s WHERE (DATE(s.date) BETWEEN :inicio AND :fin ORDER BY DATE(s.date) ASC", nativeQuery = true)
    List<SalidaEntity> findByDates(@Param("inicio") String inicio, @Param("fin") String fin);
}
