package com.example.demo.Mappers;
import com.example.demo.DTO.ClientDTO;
import com.example.demo.client.Client;
import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ClientMapper {

    private final ModelMapper modelMapper;
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    public ClientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClientDTO toDto(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }

    public Client toEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    public List<ClientDTO> toDtoList(List<Client> clients) {
        return clients.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
