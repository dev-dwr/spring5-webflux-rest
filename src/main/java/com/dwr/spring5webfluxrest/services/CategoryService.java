package com.dwr.spring5webfluxrest.services;

import com.dwr.spring5webfluxrest.domain.Category;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {
    Flux<Category> listAllCategories();
    Mono<Category> getCategoryById(String id);
    Mono<Void> saveCategories(Publisher<Category> categoryPublisher);
    Mono<Category> update(String id, Category category);
    Mono<Category> patch(String id, Category category);
    Mono<Void> deleteCategoryById(String id);
}
