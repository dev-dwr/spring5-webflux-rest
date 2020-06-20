package com.dwr.spring5webfluxrest.controllers;

import com.dwr.spring5webfluxrest.domain.Category;
import com.dwr.spring5webfluxrest.repositories.CategoryRepository;
import com.dwr.spring5webfluxrest.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public Flux<Category> listAllCategories(){
        return categoryService.listAllCategories();
    }

    @GetMapping("/{id}")
    public Mono<Category> getById(@PathVariable String id){
        return categoryService.getCategoryById(id);
    }

}
