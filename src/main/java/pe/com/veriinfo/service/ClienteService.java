package pe.com.veriinfo.service;

import pe.com.veriinfo.model.ClienteCreadoResponse;
import pe.com.veriinfo.model.ClienteRequest;
import pe.com.veriinfo.model.ClienteResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface ClienteService {

    List<ClienteResponse> listaCliente();

    ClienteCreadoResponse crearCliente(ClienteRequest clienteRequest);

    ClienteResponse buscarXcodigo(String codigo);

    default LocalDateTime crearFechaDefault(){
        return LocalDateTime.now();
    }


}
