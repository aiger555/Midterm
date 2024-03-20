package com.example.demo.DTO;
import java.time.LocalDate;


public class ClientDTO {

    private Long id;
    private String name;
    private String phoneNumber;
    private LocalDate date;

    public ClientDTO() {
    }

    public ClientDTO(Long id, String name, String phoneNumber, LocalDate date) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.date = date;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
