package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    public List<CategoryDTO.Response> findAll() {
        return CategoryDTO.Response.from(categoryRepository.findAll());
    }

    public CategoryDTO.Response findById(Long id) {
        return CategoryDTO.Response.from(categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada")));
    }

    public CategoryDTO.Response create(CategoryDTO.Request request) {
        Category category = new Category();
        category.setNome(request.getNome());
        return CategoryDTO.Response.from(categoryRepository.save(category));
    }

    public CategoryDTO.Response update(Long id, CategoryDTO.Request request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
        category.setNome(request.getNome());
        return CategoryDTO.Response.from(categoryRepository.save(category));
    }

    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
        }
        if (itemRepository.existsByCategoriaId(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Não é possível deletar categoria com itens vinculados");
        }
        categoryRepository.deleteById(id);
    }
}