package com.example.demo.dto;

import com.example.demo.model.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemDTO {

    @Data
    public static class Request {

        @NotBlank(message = "Nome é obrigatório")
        private String nome;

        @NotNull(message = "Preço é obrigatório")
        private BigDecimal preco;

        private Long categoriaId;
    }

    @Data
    public static class Response {

        private Long id;
        private String nome;
        private BigDecimal preco;
        private CategoryDTO.Response categoria;

        public static Response from(Item item) {
            if (item == null) return null;
            Response r = new Response();
            r.setId(item.getId());
            r.setNome(item.getNome());
            r.setPreco(item.getPreco());
            if (item.getCategoria() != null) {
                r.setCategoria(CategoryDTO.Response.from(item.getCategoria()));
            }
            return r;
        }

        public static List<Response> from(List<Item> items) {
            if (items == null) return List.of();
            return items.stream().map(Response::from).toList();
        }
    }
}