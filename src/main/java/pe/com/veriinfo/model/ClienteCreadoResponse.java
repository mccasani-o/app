package pe.com.veriinfo.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ClienteCreadoResponse {
    private String mensaje;
    private LocalDateTime fecha;
}
