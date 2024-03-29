package com.example.demo.controller;

import com.example.demo.DTO.ClientDTO;
import com.example.demo.Mappers.ClientMapper;
import com.example.demo.client.Client;
import com.example.demo.client.ClientService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@Validated
public class ClientController {

    private ClientService clientService;

    private ClientMapper clientMapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clientDTOs = new ArrayList<>();
        List<Client> clients = clientService.getAllClients();
        for (Client client : clients) {
            clientDTOs.add(clientMapper.toDto(client));
        }
        return new ResponseEntity<>(clientDTOs, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        ClientDTO clientDto = null;
        try {
            Client client = clientService.getClientById(id);
            if (client == null) {
                throw new EntityNotFoundException("Client not found with id: " + id);
            }
            clientDto = clientMapper.toDto(client);
            return new ResponseEntity<>(clientDto, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(clientDto, HttpStatus.NOT_FOUND);
        }
         catch (Exception ex) {
            return new ResponseEntity<>(clientDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDto) {
        try {
            Client client = clientMapper.toEntity(clientDto);
            Client newClient = clientService.createClient(client);
            ClientDTO newClientDto = clientMapper.toDto(newClient);
            return new ResponseEntity<>(newClientDto, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(clientDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDto) {
        try {
            Client client = clientMapper.toEntity(clientDto);
            Client updatedClient = clientService.updateClient(id, client);
            ClientDTO updatedClientDto = clientMapper.toDto(updatedClient);
            return new ResponseEntity<>(updatedClientDto, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(clientDto, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(clientDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
