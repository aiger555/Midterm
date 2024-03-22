package com.example.demo.controller;

import com.example.demo.client.Client;
import com.example.demo.client.ClientService;
import com.example.demo.Mappers.ClientMapper;
import com.example.demo.DTO.ClientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc(addFilters = false) // Disable CSRF protection
public class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientController clientController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ClientController clientController = new ClientController();
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

//    @Test
//    public void testGetAllClients() throws Exception {
//        // Mocking service response
//        List<Client> clients = Arrays.asList(new Client(), new Client());
//        when(clientService.getAllClients()).thenReturn(clients);
//
//        // Mocking mapper response
//        List<ClientDTO> clientDTOs = Arrays.asList(new ClientDTO(), new ClientDTO());
//        when(clientMapper.toDtoList(clients)).thenReturn(clientDTOs);
//
//        mockMvc.perform(get("/api/v1/clients"))
//                .andExpect(status().isOk());
//    }


//    @Test
//    void testGetClientById() throws Exception {
//        Long id = 1L;
//        Client client = new Client();
//        client.setId(id);
//        ClientDTO clientDTO = new ClientDTO();
//        clientDTO.setId(id);
//
//        when(clientService.getClientById(id)).thenReturn(client);
//        when(clientMapper.toDto(client)).thenReturn(clientDTO);
//
//        mockMvc.perform(get("/api/v1/clients/{id}", id)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$.id").value(id));
//    }

    @Test
    public void createClient_WhenInvalidData_ReturnsBadRequest() throws Exception {
        // Arrange
        ClientDTO invalidClientDto = new ClientDTO();
        invalidClientDto.setName(""); // Name is required, so set it to empty string

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(invalidClientDto)))
                .andExpect(status().isBadRequest());
    }

    // Utility method to convert object to JSON string
    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
