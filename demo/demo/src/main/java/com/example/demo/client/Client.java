package com.example.demo.client;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer ph_number;
    private LocalDate ddate;

    public Client() {
    }

    public Client(Long id, String name, Integer ph_number, LocalDate ddate) {
        this.id = id;
        this.name = name;
        this.ph_number = ph_number;
        this.ddate = ddate;
    }

    public Client(String name, Integer ph_number, LocalDate ddate) {
        this.name = name;
        this.ph_number = ph_number;
        this.ddate = ddate;
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
        return ddate;
    }

    public void setDate(LocalDate date) {
        this.ddate = ddate;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ph_number=" + ph_number +
                ", ddate=" + ddate +
                '}';
    }



}
