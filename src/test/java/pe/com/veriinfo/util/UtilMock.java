package pe.com.veriinfo.util;

import pe.com.veriinfo.entity.ClienteEntity;
import pe.com.veriinfo.model.ClienteRequest;
import pe.com.veriinfo.model.ClienteResponse;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public final class UtilMock {

    private UtilMock(){}
    public static List<ClienteResponse> listarClienteMock() {
        return Arrays.asList(ClienteResponse.builder().codigo("01")
                        .nombres("Mauricio")
                        .apellidos("Ccasani")
                        .tipoDocumento("DNI")
                        .numeroDocumento("0986546")
                        .fechaCreacion(LocalDateTime.now())
                        .build(),
                ClienteResponse.builder().codigo("02")
                        .nombres("Juan")
                        .apellidos("Ccasani")
                        .tipoDocumento("DNI")
                        .numeroDocumento("908766")
                        .fechaCreacion(LocalDateTime.now())
                        .build());
    }

    public static List<ClienteEntity> listarClienteEntityMock() {
        return Arrays.asList(ClienteEntity.builder().codigo("01")
                        .nombres("Mauricio")
                        .apellidos("Ccasani")
                        .tipoDocumento("DNI")
                        .numeroDocumento("0986546")
                        .fechaCreacion(LocalDateTime.now())
                        .build(),
                ClienteEntity.builder().codigo("02")
                        .nombres("Juan")
                        .apellidos("Ccasani")
                        .tipoDocumento("DNI")
                        .numeroDocumento("908766")
                        .fechaCreacion(LocalDateTime.now())
                        .build());
    }

    public static ClienteRequest crearClienteMock() {
        return ClienteRequest.builder()
                .nombres("Mauricio")
                .apellidos("Ccasani")
                .tipoDocumento("DNI")
                .numeroDocumento("0986546")
                .build();
    }
}
