package pe.com.veriinfo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pe.com.veriinfo.entity.ClienteEntity;
import pe.com.veriinfo.model.ClienteCreadoResponse;
import pe.com.veriinfo.model.ClienteRequest;
import pe.com.veriinfo.model.ClienteResponse;
import pe.com.veriinfo.model.CustomException;
import pe.com.veriinfo.repository.ClienteRepository;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<ClienteResponse> listaCliente() {
        return this.clienteRepository.findAll()
                .stream()
                .map(this::toClienteResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteCreadoResponse crearCliente(ClienteRequest clienteRequest) {
        this.clienteRepository.save(this.toClienteEntity(clienteRequest));
        return ClienteCreadoResponse.builder()
                .mensaje("Cliente creado")
                .fecha(LocalDateTime.now())
                .build();
    }

    @Override
    public ClienteResponse buscarXcodigo(String codigo) {
        Optional<ClienteEntity> clienteEntity= this.clienteRepository.findById(codigo);
        if(clienteEntity.isPresent()){
            return this.toClienteResponse(clienteEntity.get());
        }

        throw  new CustomException("0002", "El cliente no se encuentra en DB", HttpStatus.NOT_FOUND);
    }

    private ClienteResponse toClienteResponse(ClienteEntity clienteEntity) {
        return ClienteResponse.builder()
                .codigo(clienteEntity.getCodigo())
                .nombres(clienteEntity.getNombres())
                .apellidos(clienteEntity.getApellidos())
                .tipoDocumento(clienteEntity.getTipoDocumento())
                .numeroDocumento(clienteEntity.getNumeroDocumento())
                .fechaCreacion(clienteEntity.getFechaCreacion())
                .build();
    }

    private ClienteEntity toClienteEntity(ClienteRequest clienteRequest) {

        return ClienteEntity.builder()
                .codigo(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes()))
                .nombres(clienteRequest.getNombres())
                .apellidos(clienteRequest.getApellidos())
                .tipoDocumento(clienteRequest.getTipoDocumento())
                .numeroDocumento(clienteRequest.getNumeroDocumento())
                .fechaCreacion(this.crearFechaDefault())
                .build();
    }

}
