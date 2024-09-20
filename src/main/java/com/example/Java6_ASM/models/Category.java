package com.example.Java6_ASM.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category extends BaseModel{

    @Column(nullable = false, unique = true)
    String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    List<Product> products;

}
