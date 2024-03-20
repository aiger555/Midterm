package com.example.demo.mapper;

import com.example.demo.DTO.ClientDTO;
import com.example.demo.Mappers.ClientMapper;
import com.example.demo.client.Client;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientMapperTest {

    private final ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void testToDto() {
        // Given
        Client client = new Client();
        client.setId(1L);
        client.setName("John Doe");

        // When
        ClientDTO clientDTO = clientMapper.toDto(client);

        // Then
        assertEquals(client.getId(), clientDTO.getId());
        assertEquals(client.getName(), clientDTO.getName());
    }

    @Test
    public void testToEntity() {
        // Given
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(1L);
        clientDTO.setName("John Doe");

        // When
        Client client = clientMapper.toEntity(clientDTO);

        // Then
        assertEquals(clientDTO.getId(), client.getId());
        assertEquals(clientDTO.getName(), client.getName());
    }
}
