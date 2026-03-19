package com.example.demo.dto;

import com.example.demo.model.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {

    @Data
    public static class Request {

        @NotBlank(message = "Nome é obrigatório")
        private String nome;
    }

    @Data
    public static class Response {

        private Long id;
        private String nome;

        public static Response from(Category category) {
            if (category == null) return null;
            Response r = new Response();
            r.setId(category.getId());
            r.setNome(category.getNome());
            return r;
        }

        public static List<Response> from(List<Category> categories) {
            if (categories == null) return List.of();
            return categories.stream().map(Response::from).toList();
        }
    }
}