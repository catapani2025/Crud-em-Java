package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    private String descricao;  // corrigido: sem "c" extra

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<Item> itens = new ArrayList<>();  // inicialize para evitar NullPointer

}