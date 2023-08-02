package cl.mingeso.Entradas.Controllers;

import cl.mingeso.Entradas.Entities.EntradaEntity;
import cl.mingeso.Entradas.Services.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/entradas")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    @GetMapping
    public ResponseEntity<List<EntradaEntity>> getAll(){
        List<EntradaEntity> entradas = entradaService.getAll();
        if(entradas.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(entradas);
    }

    @PostMapping
    public ResponseEntity<EntradaEntity> save(@RequestBody EntradaEntity entradaEntity){
        EntradaEntity nuevaEntrada = entradaService.save(entradaEntity);
        if(nuevaEntrada == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(nuevaEntrada);
    }

    @GetMapping("/dates/{fecha_inicio}/{fecha_fin}")
    public ResponseEntity<List<EntradaEntity>> getByDate(@PathVariable("fecha_inicio") String inicio, @PathVariable("fecha_fin") String fin){
        List<EntradaEntity> entradas = entradaService.getByDates(inicio,fin);
        if (entradas.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(entradas);
    }
}
