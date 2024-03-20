package com.example.demo.Mappers;

import com.example.demo.DTO.ClientDTO;
import com.example.demo.client.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Mapper
@Component
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO toDto(Client client);

    Client toEntity(ClientDTO clientDTO);

    List<ClientDTO> toDtoList(List<Client> clients);
}
