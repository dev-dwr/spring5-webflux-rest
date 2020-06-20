package com.dwr.spring5webfluxrest.controllers;

import com.dwr.spring5webfluxrest.domain.Category;
import com.dwr.spring5webfluxrest.domain.Vendor;
import com.dwr.spring5webfluxrest.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
class CategoryControllerTest {
    @Mock
    CategoryService categoryService;

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
}