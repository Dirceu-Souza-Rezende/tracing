package br.com.poc.tracing.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "registration")
    private String registration;

    public Student(String name) {
        this.name = name;
        this.registration = LocalDateTime.now().toString();
    }

    public String getName() {
        return name;
    }

    public String getRegistration() {
        return registration;
    }
}
