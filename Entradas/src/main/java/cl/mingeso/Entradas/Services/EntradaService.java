package cl.mingeso.Entradas.Services;

import cl.mingeso.Entradas.Entities.EntradaEntity;
import cl.mingeso.Entradas.Repositories.EntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;

    public List<EntradaEntity> getAll(){
        return entradaRepository.findAll();
    }

    public EntradaEntity save(EntradaEntity entradaEntity){
        EntradaEntity nuevaEntrada = entradaRepository.save(entradaEntity);
        return nuevaEntrada;
    }

    public List<EntradaEntity> getByDates(String inicio, String fin){
        List<EntradaEntity> entradas = entradaRepository.findByDates(inicio, fin);
        return entradas;
    }
}
