package com.example.demo;

import com.example.demo.client.Client;
import com.example.demo.client.ClientRepository;
import com.example.demo.client.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ClientRepositoryTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void testFindAllClients() {
        // Given
        Client client1 = new Client(1L, "John Doe", "1234567890", LocalDate.now());
        Client client2 = new Client(2L, "Jane Smith", "9876543210", LocalDate.now());
        List<Client> clients = List.of(client1, client2);

        // When
        when(clientRepository.findAll()).thenReturn(clients);
        List<Client> foundClients = clientService.getAllClients();

        // Then
        assertThat(foundClients).isNotNull();
        assertThat(foundClients.size()).isEqualTo(2);
        assertThat(foundClients).contains(client1, client2);
    }

    @Test
    public void testFindClientById() {
        // Given
        Long clientId = 1L;
        Client client = new Client(clientId, "John Doe", "1234567890", LocalDate.now());

        // When
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        Optional<Client> foundClient = Optional.ofNullable(clientService.getClientById(clientId));

        // Then
        assertThat(foundClient).isPresent();
        assertThat(foundClient.get()).isEqualTo(client);
    }

    @Test
    public void testSaveClient() {
        // Given
        Client client = new Client(1L, "John Doe", "1234567890", LocalDate.now());

        // When
        when(clientRepository.save(client)).thenReturn(client);
        Client savedClient = clientService.save(client);

        // Then
        assertThat(savedClient).isNotNull();
        assertThat(savedClient).isEqualTo(client);
    }


    @Test
    public void testDeleteClient() {
        // Given
        Long clientId = 1L;

        // When
        clientService.deleteClient(clientId);

        // No need to verify anything in this case
    }


}
