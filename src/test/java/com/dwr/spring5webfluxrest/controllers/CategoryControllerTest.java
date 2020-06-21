package com.dwr.spring5webfluxrest.controllers;

import com.dwr.spring5webfluxrest.domain.Category;
import com.dwr.spring5webfluxrest.domain.Vendor;
import com.dwr.spring5webfluxrest.repositories.CategoryRepository;
import com.dwr.spring5webfluxrest.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
class CategoryControllerTest {
    @Mock
    CategoryService categoryService;

    @Mock
    CategoryRepository repository;

    @InjectMocks
    CategoryController categoryController;

    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    void listAllCategories() {
        given(categoryService.listAllCategories())
                .willReturn(Flux.just(Category.builder().description("Test1").build(),
                        Category.builder().description("Test2").build()));

        webTestClient.get().uri(CategoryController.BASE_URL)
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);

    }

    @Test
    void getById() {
        given(categoryService.getCategoryById(anyString()))
                .willReturn(Mono.just(Category.builder().description("Test").build()));

        webTestClient.get()
                .uri(CategoryController.BASE_URL + "/1")
                .exchange()
                .expectBody(Category.class);
    }
    @Test
    void createCategoryTest(){
        Mono<Category> categoryToSave = Mono.just(Category.builder().description("test").build());
        given(categoryService.saveCategories(any(Publisher.class)))
                .willReturn(Mono.just(Category.builder().description("123").build()));

        webTestClient.post()
                .uri(CategoryController.BASE_URL)
                .body(categoryToSave, Category.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }
    @Test
    void updateCategoryTest(){
        given(categoryService.update(anyString(),any(Category.class)))
                .willReturn(Mono.just(Category.builder().description("aaa").build()));
        //we want to update this obj
        Mono<Category> categoryToUpdate = Mono.just(Category.builder().description("test").build());

        webTestClient.put()
                .uri(CategoryController.BASE_URL + "/1")
                .body(categoryToUpdate, Category.class)
                .exchange()
                .expectStatus()
                .isOk();

    }
    @Test
    void patchCategoryWithChangesTest(){
        given(categoryService.patch(anyString(),any(Category.class)))
                .willReturn(Mono.just(Category.builder().description("aaa").build()));
        //we want to update this obj
        Mono<Category> categoryToUpdate = Mono.just(Category.builder().description("test").build());

        webTestClient.patch()
                .uri(CategoryController.BASE_URL + "/231")
                .body(categoryToUpdate, Category.class)
                .exchange()
                .expectStatus()
                .isOk();
    }
    @Test
    void patchCategoryWithNoChanges(){
        given(categoryService.patch(anyString(),any(Category.class)))
                .willReturn(Mono.just(Category.builder().description("aaa").build()));

        Mono<Category> categoryToUpdate = Mono.just(Category.builder().description("aaa").build());
        webTestClient.patch()
                .uri(CategoryController.BASE_URL + "/231")
                .body(categoryToUpdate, Category.class)
                .exchange()
                .expectStatus()
                .isOk();
        verify(categoryService).patch(anyString(), any());
    }
    @Test
    void deleteCategoryTest(){
        webTestClient.delete()
                .uri(CategoryController.BASE_URL + "/id")
                .exchange()
                .expectStatus()
                .isOk();
    }
}