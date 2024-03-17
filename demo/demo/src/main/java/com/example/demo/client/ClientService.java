package com.example.demo.client;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class ClientService {
    public List<Client> getClients(){
        return List.of(
                new Client(1L,
                        "Madina",
                        04343434,
                        LocalDate.of(2024, Month.JANUARY, 8)
                )
        );
    }
}
