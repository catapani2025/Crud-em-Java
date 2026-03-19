package com.example.demo.controller;

import com.example.demo.dto.ItemDTO;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDTO.Response> create(@RequestBody ItemDTO.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO.Response>> findAll(
            @RequestParam(required = false) Long categoriaId) {
        return ResponseEntity.ok(itemService.findAll(categoriaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO.Response> update(@PathVariable Long id,
                                                   @RequestBody ItemDTO.Request request) {
        return ResponseEntity.ok(itemService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}