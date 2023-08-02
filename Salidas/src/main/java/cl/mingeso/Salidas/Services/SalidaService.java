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

    public List<SalidaEntity> getByDates(Date inicio, Date fin){
        String patron = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patron);

        String strInicio = simpleDateFormat.format(inicio);
        String strFin = simpleDateFormat.format(fin);

        List<SalidaEntity> salidas = salidaRepository.findByDates(strInicio, strFin);
        return salidas;
    }
}
