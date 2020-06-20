package com.dwr.spring5webfluxrest.services;

import com.dwr.spring5webfluxrest.domain.Category;
import com.dwr.spring5webfluxrest.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Flux<Category> listAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<Category> getCategoryById(String id) {
        return categoryRepository.findById(id);
    }
}
