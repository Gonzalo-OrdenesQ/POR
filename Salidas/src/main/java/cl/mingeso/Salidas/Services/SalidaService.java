package cl.mingeso.Salidas.Services;

import cl.mingeso.Salidas.Entities.SalidaEntity;
import cl.mingeso.Salidas.Repositories.SalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SalidaService {

    @Autowired
    private SalidaRepository salidaRepository;

    public List<SalidaEntity> getAll(){
        return salidaRepository.findAll();
    }

    public SalidaEntity save(SalidaEntity salidaEntity){
        SalidaEntity nuevaSalida = salidaRepository.save(salidaEntity);
        return nuevaSalida;
    }

    public List<SalidaEntity> getByDates(String inicio, String fin){
        List<SalidaEntity> salidas = salidaRepository.findByDates(inicio, fin);
        return salidas;
    }
}
