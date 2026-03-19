package com.example.demo.service;

import com.example.demo.dto.ItemDTO;
import com.example.demo.model.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public List<ItemDTO.Response> findAll(Long categoriaId) {
        if (categoriaId != null) {
            return ItemDTO.Response.from(itemRepository.findByCategoriaId(categoriaId));
        }
        return ItemDTO.Response.from(itemRepository.findAll());
    }

    public ItemDTO.Response findById(Long id) {
        return ItemDTO.Response.from(itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado")));
    }

    public ItemDTO.Response create(ItemDTO.Request request) {
        if (request.getCategoriaId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria é obrigatória");
        }
        var categoria = categoryRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada"));

        Item item = new Item();
        item.setNome(request.getNome());
        item.setPreco(request.getPreco());
        item.setCategoria(categoria);

        return ItemDTO.Response.from(itemRepository.save(item));
    }

    public ItemDTO.Response update(Long id, ItemDTO.Request request) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));

        item.setNome(request.getNome());
        item.setPreco(request.getPreco());

        if (request.getCategoriaId() != null) {
            var categoria = categoryRepository.findById(request.getCategoriaId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada"));
            item.setCategoria(categoria);
        }

        return ItemDTO.Response.from(itemRepository.save(item));
    }

    public void delete(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado");
        }
        itemRepository.deleteById(id);
    }
}