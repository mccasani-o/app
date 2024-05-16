package pe.com.veriinfo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_clientes")
public class ClienteEntity {
    @Id
    @Column(unique=true)
    private String codigo;

    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    @Column(unique=true)
    private String numeroDocumento;
    private LocalDateTime fechaCreacion;
}
