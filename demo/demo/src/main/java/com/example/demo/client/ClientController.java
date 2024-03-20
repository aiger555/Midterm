package com.example.demo.client;

import com.example.demo.DTO.ClientDTO;
import com.example.demo.Mappers.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;


    private ClientMapper clientMapper;

    // Get all clients
    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        List<ClientDTO> clientDtos = clientMapper.toDtoList(clients);
        return new ResponseEntity<>(clientDtos, HttpStatus.OK);
    }

    // Get client by ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        ClientDTO clientDto = clientMapper.toDto(client);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    // Create a new client
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        Client newClient = clientService.createClient(client);
        ClientDTO newClientDto = clientMapper.toDto(newClient);
        return new ResponseEntity<>(newClientDto, HttpStatus.CREATED);
    }

    // Update an existing client
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        Client updatedClient = clientService.updateClient(id, client);
        ClientDTO updatedClientDto = clientMapper.toDto(updatedClient);
        return new ResponseEntity<>(updatedClientDto, HttpStatus.OK);
    }

    // Delete a client by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
