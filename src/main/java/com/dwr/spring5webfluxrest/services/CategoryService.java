package com.dwr.spring5webfluxrest.services;

import com.dwr.spring5webfluxrest.domain.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {
    Flux<Category> listAllCategories();
    Mono<Category> getCategoryById(String id);
}
