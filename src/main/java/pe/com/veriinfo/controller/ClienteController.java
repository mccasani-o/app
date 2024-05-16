package pe.com.veriinfo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.veriinfo.model.ClienteCreadoResponse;
import pe.com.veriinfo.model.ClienteRequest;
import pe.com.veriinfo.model.ClienteResponse;
import pe.com.veriinfo.service.ClienteService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<ClienteResponse> listarCliente() {
        return this.clienteService.listaCliente();
    }

    @GetMapping("/{codigo}")
    public ClienteResponse buscarXcodigo(@PathVariable String codigo) {
        return this.clienteService.buscarXcodigo(codigo);
    }

    @PostMapping
    public ResponseEntity<ClienteCreadoResponse> crearCliente(@RequestBody @Valid ClienteRequest clienteRequest) {
        return new ResponseEntity(this.clienteService.crearCliente(clienteRequest), HttpStatus.CREATED);
    }
}
