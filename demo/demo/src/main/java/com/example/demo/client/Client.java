package com.example.demo.client;

import java.time.LocalDate;

public class Client {
    private Long id;
    private String name;
    private Integer ph_number;
    private LocalDate date;

    public Client() {
    }

    public Client(Long id, String name, Integer ph_number, LocalDate date) {
        this.id = id;
        this.name = name;
        this.ph_number = ph_number;
        this.date = date;
    }

    public Client(String name, Integer ph_number, LocalDate date) {
        this.name = name;
        this.ph_number = ph_number;
        this.date = date;
    }

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

    public Integer getPh_number() {
        return ph_number;
    }

    public void setPh_number(Integer ph_number) {
        this.ph_number = ph_number;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ph_number=" + ph_number +
                ", date=" + date +
                '}';
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }



}
