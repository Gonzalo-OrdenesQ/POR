package cl.mingeso.Resumenes.Entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "resumenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumenEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saldoFinal;
    private String fechaInicio;
    private String fechaFin;
}
