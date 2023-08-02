package cl.mingeso.Resumenes.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntradaModel {
    private String fecha;
    private Integer recibo;
    private Integer monto;
}
