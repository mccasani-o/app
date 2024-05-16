package pe.com.veriinfo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import pe.com.veriinfo.entity.ClienteEntity;
import pe.com.veriinfo.model.ClienteCreadoResponse;
import pe.com.veriinfo.model.ClienteRequest;
import pe.com.veriinfo.model.ClienteResponse;
import pe.com.veriinfo.model.CustomException;
import pe.com.veriinfo.repository.ClienteRepository;
import pe.com.veriinfo.util.UtilMock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private ClienteEntity clienteEntity;


    @BeforeEach
    void setUp() {
         clienteEntity = ClienteEntity.builder()
                 .codigo("01")
                .nombres("Mauricio")
                .apellidos("Ccasani")
                .tipoDocumento("DNI")
                .numeroDocumento("0986546")
                .fechaCreacion(LocalDateTime.now())
                .build();

    }

    @Test
    void listaCliente() {
        given(this.clienteRepository.findAll()).willReturn(UtilMock.listarClienteEntityMock());

        List<ClienteResponse> clienteResponses = this.clienteService.listaCliente();
        assertThat(clienteResponses).isNotNull();
        assertThat(clienteResponses.get(0).getCodigo()).isEqualTo("01");

    }

    @Test
    void crearCliente() {
        ClienteRequest clienteRequest=   ClienteRequest.builder()
                .nombres("Mauricio")
                .apellidos("Ccasani")
                .tipoDocumento("DNI")
                .numeroDocumento("0986546")
                .build();

        given(this.clienteRepository.save(any())).willReturn(this.clienteEntity);


        ClienteCreadoResponse clienteCreadoResponse=this.clienteService.crearCliente(clienteRequest);
        assertThat(clienteCreadoResponse).isNotNull();
        assertThat(clienteCreadoResponse.getMensaje()).isEqualTo("Cliente creado");


    }

    @Test
    void buscarXcodigo() {
        given(this.clienteRepository.findById(any())).willReturn(Optional.of(this.clienteEntity));

        ClienteResponse clienteResponse = this.clienteService.buscarXcodigo("01");
        assertThat(clienteResponse).isNotNull();
        assertThat(clienteResponse.getCodigo()).isEqualTo("01");

    }

    @Test
    void idNoEncontradoTest() {
        given(this.clienteRepository.findById(any())).willReturn(Optional.empty());
        Assertions.assertThrows(CustomException.class, ()->this.clienteService.buscarXcodigo("0023"));
    }
}