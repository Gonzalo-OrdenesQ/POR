package cl.mingeso.Salidas.Controllers;

import cl.mingeso.Salidas.Entities.SalidaEntity;
import cl.mingeso.Salidas.Services.SalidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("salidas")
public class SalidaController {

    @Autowired
    private SalidaService salidaService;

    @GetMapping
    public ResponseEntity<List<SalidaEntity>> getAll(){
        List<SalidaEntity> salidas = salidaService.getAll();
        if (salidas.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(salidas);
    }

    @PostMapping
    public ResponseEntity<SalidaEntity> save(SalidaEntity salidaEntity){
        SalidaEntity nuevaSalida = salidaService.save(salidaEntity);
        if (nuevaSalida == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(nuevaSalida);
    }

    @GetMapping("/dates")
    public ResponseEntity<List<SalidaEntity>> getByDate(@RequestParam Date inicio, @RequestParam Date fin){
        List<SalidaEntity> salidas = salidaService.getByDates(inicio, fin);
        if (salidas.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(salidas);
    }
}
