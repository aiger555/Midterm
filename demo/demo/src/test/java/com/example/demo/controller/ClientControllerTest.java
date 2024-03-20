package com.example.demo.controller;

import com.example.demo.client.Client;
import com.example.demo.client.ClientController;
import com.example.demo.client.ClientService;
import com.example.demo.Mappers.ClientMapper;
import com.example.demo.DTO.ClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @Mock
    private ClientMapper clientMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllClients() throws Exception {
        // Mocking service response
        List<Client> clients = Arrays.asList(new Client(), new Client());
        when(clientService.getAllClients()).thenReturn(clients);

        // Mocking mapper response
        List<ClientDTO> clientDTOs = Arrays.asList(new ClientDTO(), new ClientDTO());
        when(clientMapper.toDtoList(clients)).thenReturn(clientDTOs);

        // Sending GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists());
    }

    // Add more test methods for other endpoints...
}
