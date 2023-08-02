package cl.mingeso.Resumenes.Repositories;

import cl.mingeso.Resumenes.Entities.ResumenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumenRepository extends JpaRepository<ResumenEntity, Integer> {

}
