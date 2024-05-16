package pe.com.veriinfo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pe.com.veriinfo.model.ClienteCreadoResponse;
import pe.com.veriinfo.repository.ClienteRepository;
import pe.com.veriinfo.service.ClienteService;
import pe.com.veriinfo.util.UtilMock;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;


    @Test
    void listarClienteTest() throws Exception {

        given(this.clienteService.listaCliente()).willReturn(UtilMock.listarClienteMock());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.[0].codigo").isNotEmpty())
                .andExpect(jsonPath("$.[0].codigo").value("01"));


        verify(this.clienteService).listaCliente();

    }

    @Test
    void buscarXcodigoTest() throws Exception {
        given(this.clienteService.buscarXcodigo(any())).willReturn(UtilMock.listarClienteMock().get(0));


        this.mockMvc.perform(MockMvcRequestBuilders.get("/clientes/{codigo}", 01)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").isNotEmpty())
                .andExpect(jsonPath("$.codigo").value(01));

        verify(this.clienteService).buscarXcodigo(any());


    }

    @Test
    void crearClienteTest() throws Exception {
        //doNothing().when(this.clienteService).crearCliente(any());

        given(this.clienteService.crearCliente(any())).willReturn(ClienteCreadoResponse.builder().mensaje("OK").fecha(LocalDateTime.now()).build());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .content(this.mapper.writeValueAsString(UtilMock.crearClienteMock()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.mensaje").value("OK"));

        verify(clienteService).crearCliente(any());


    }


}