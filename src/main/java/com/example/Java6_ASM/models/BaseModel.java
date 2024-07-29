package com.example.Java6_ASM.models;

import javax.persistence.*;

import lombok.Data;

import java.util.UUID;

@MappedSuperclass
@Data
public class BaseModel {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();
}
