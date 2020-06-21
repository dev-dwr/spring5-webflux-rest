package com.dwr.spring5webfluxrest.controllers;

import com.dwr.spring5webfluxrest.domain.Category;
import com.dwr.spring5webfluxrest.repositories.CategoryRepository;
import com.dwr.spring5webfluxrest.services.CategoryService;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {
    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Flux<Category> listAllCategories() {
        return categoryService.listAllCategories();
    }

    @GetMapping("/{id}")
    public Mono<Category> getById(@PathVariable String id) {
        return categoryService.getCategoryById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<Void> create(@RequestBody Publisher<Category> categoryStream) {
        return categoryService.saveCategories(categoryStream).then();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Mono<Category> updateCategory(@PathVariable String id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public Mono<Category> patchCategory(@PathVariable String id, @RequestBody Category category) {
        return categoryService.patch(id, category);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Mono<Void> deleteCategory(@PathVariable String id){
        return categoryService.deleteCategoryById(id);
    }

}
