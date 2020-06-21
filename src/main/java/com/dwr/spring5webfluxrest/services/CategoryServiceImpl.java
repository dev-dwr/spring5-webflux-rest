package com.dwr.spring5webfluxrest.services;

import com.dwr.spring5webfluxrest.domain.Category;
import com.dwr.spring5webfluxrest.repositories.CategoryRepository;
import org.reactivestreams.Publisher;
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

    @Override
    public Mono<Void> saveCategories(Publisher<Category> categoryPublisher) {
        //then() Return a Mono<Void> which only replays complete and error signals from this Mono.
        return categoryRepository.saveAll(categoryPublisher).then();
    }

    @Override
    public Mono<Category> update(String id, Category category) {
        return categoryRepository.findById(id)
                .flatMap(foundCategory->{
                    category.setId(foundCategory.getId());
                    return categoryRepository.save(category);
                })
                .switchIfEmpty(Mono.error(new Exception("Not Found")));
    }

    @Override
    public Mono<Category> patch(String id, Category category) {
        return categoryRepository.findById(id)
                .flatMap(foundCategory->{
                    if(!foundCategory.getDescription().equals(category.getDescription())){
                        foundCategory.setDescription(category.getDescription());
                        return categoryRepository.save(foundCategory);
                    }
                    return Mono.just(foundCategory);
                }).switchIfEmpty(Mono.error(new Exception("not found")));
    }

    @Override
    public Mono<Void> deleteCategoryById(String id) {
        return categoryRepository.deleteById(id);
    }
}
