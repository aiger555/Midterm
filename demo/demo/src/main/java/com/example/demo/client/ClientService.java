package com.example.demo.client;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Get all clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Get client by ID
    public Client getClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + id));
    }

    // Create a new client
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    // Update an existing client
    public Client updateClient(Long id, Client newClient) {
        Client existingClient = getClientById(id);
        existingClient.setName(newClient.getName());
        existingClient.setPh_number(newClient.getPh_number());
        existingClient.setDate(newClient.getDate());
        return clientRepository.save(existingClient);
    }

    // Partially update an existing client
    public Client patchClient(Long id, Client client) {
        Client existingClient = getClientById(id);
        if (client.getName() != null) {
            existingClient.setName(client.getName());
        }
        if (client.getPh_number() != null) {
            existingClient.setPh_number(client.getPh_number());
        }
        if (client.getDate() != null) {
            existingClient.setDate(client.getDate());
        }
        return clientRepository.save(existingClient);
    }

    // Delete a client by ID
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
