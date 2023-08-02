package cl.mingeso.Resumenes.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalidaModel {
    private String fecha;
    private String tipo;
    private Integer numero;
    private String motivo;
}
