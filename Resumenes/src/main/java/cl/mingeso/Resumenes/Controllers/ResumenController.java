package cl.mingeso.Resumenes.Controllers;

import cl.mingeso.Resumenes.Services.ResumenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/resumenes")
public class ResumenController {

    @Autowired
    private ResumenService resumenService;

    @GetMapping("/dates/{fecha_inicio}/{fecha_fin}")
    public ResponseEntity<List<Object>[]> getResumenByFechas(@PathVariable("fecha_inicio") String inicio, @PathVariable("fecha_fin") String fin) throws ParseException {
        List<Object> objetos[] = resumenService.getList(inicio, fin);
        if(objetos[0].isEmpty() || objetos[1].isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(objetos);
    }
}
